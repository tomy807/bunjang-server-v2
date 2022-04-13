package com.example.bunjangv2.src.order;

import com.example.bunjangv2.entity.Order;
import com.example.bunjangv2.entity.Product;
import com.example.bunjangv2.entity.User;
import com.example.bunjangv2.exception.NotEnoughMoney;
import com.example.bunjangv2.exception.NotSellingProduct;
import com.example.bunjangv2.src.order.dto.OrderDetailDto;
import com.example.bunjangv2.src.order.dto.OrderDto;
import com.example.bunjangv2.src.product.ProductRepository;
import com.example.bunjangv2.src.product.ProductService;
import com.example.bunjangv2.src.product.dto.ProductDto;
import com.example.bunjangv2.src.user.UserRepository;
import com.example.bunjangv2.src.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public void createOrder(OrderDto orderDto, User user) {
        Product product = productRepository.findById(
                orderDto.getProductId()).orElseThrow(() -> new EntityNotFoundException("해당 상품을 찾을수 없습니다."));
        user = userRepository.findById(user.getId()).get();
        if (!Objects.equals(product.getSellStatus(), "SELLING")) {
            throw new NotSellingProduct();
        }
        String address = "";
        if (orderDto.getTradingMethod().equals("DIRECT")) {
            address = product.getDirectAddress();
        } else if (orderDto.getTradingMethod().equals("DELIVERY")) {
            address = user.getAddresses().stream().filter(mainAddress->mainAddress.getAddressType().equals("DELIVERY"))
                    .filter(mainAddress -> mainAddress.getMain().equals("MAIN"))
                    .findFirst().orElseThrow(EntityNotFoundException::new).getAddress();
        }

        User seller = product.getUser();
        int productPrice = product.getPrice();
        int totalPrice = productPrice - orderDto.getPoint();
        int buyUserPoint = user.getPoint();
        int sellerPoint = seller.getPoint();
        int productQuantity = product.getQuantity();
        if (buyUserPoint < totalPrice) {
            throw new NotEnoughMoney();
        }
        Order order = new Order(user, product, orderDto, totalPrice, address);
        if (product.getQuantity() == 1) {
            product.setSellStatus("RESERVED");
        }
        product.setQuantity(productQuantity - 1);
        user.setPoint(buyUserPoint - totalPrice);
        seller.setPoint(sellerPoint + productPrice);
        orderRepository.save(order);
        userRepository.save(user);
        userRepository.save(seller);
        productRepository.save(product);
    }

    public void cancelOrder(User user, Long orderIdx) {
        user = userRepository.findById(user.getId()).get();
        Order order = orderRepository.findById(orderIdx).orElseThrow(() -> new EntityNotFoundException("주문한 상품을 찾을수 없습니다."));
        User seller = userRepository.findById(order.getUser().getId()).get();
        int totalPrice = order.getTotalPrice();

        order.setOrderStatus("CANCELED");

        Product product = order.getProduct();
        product.setSellStatus("SELLING");
        product.setQuantity(product.getQuantity() + 1);

        user.setPoint(user.getPoint() + totalPrice);
        seller.setPoint(seller.getPoint() - totalPrice);

        orderRepository.save(order);
        userRepository.save(user);
        userRepository.save(seller);
        productRepository.save(product);
    }

    public OrderDetailDto getOrderDetail(Long orderIdx, User user) {
        user = userRepository.findById(user.getId()).get();
        Order order = orderRepository.findById(orderIdx).orElseThrow(() -> new EntityNotFoundException("주문한 상품을 찾을수 없습니다."));
        Product product = order.getProduct();
        User seller = product.getUser();
        return new OrderDetailDto(order, user, seller, product);

    }

    public void confirmOrder(Long orderIdx, User user) {
        user = userRepository.findById(user.getId()).get();
        Order order = user.getOrders().stream().filter(confirmOrder -> confirmOrder.getId().equals(orderIdx)).findFirst().orElseThrow(() -> new EntityNotFoundException("주문한 상품을 찾을수 없습니다."));
        Product product = order.getProduct();
        if (product.getQuantity() == 0 && product.getSellStatus().equals("RESERVED")) {
            product.setSellStatus("SOLDOUT");
        }
//        Order order = orderRepository.findById(orderIdx).orElseThrow(() -> new EntityNotFoundException("주문한 상품을 찾을수 없습니다."));
        order.setOrderStatus("COMPLETED");
        userRepository.save(user);
    }
}
