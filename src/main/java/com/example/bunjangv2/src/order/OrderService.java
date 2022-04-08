package com.example.bunjangv2.src.order;

import com.example.bunjangv2.entity.Order;
import com.example.bunjangv2.entity.Product;
import com.example.bunjangv2.entity.User;
import com.example.bunjangv2.exception.NotEnoughMoney;
import com.example.bunjangv2.exception.NotSellingProduct;
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
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createOrder(OrderDto orderDto, User user) {
        Product product = productRepository.findById(
                orderDto.getProductId()).orElseThrow(() -> new EntityNotFoundException("해당 상품을 찾을수 없습니다."));
        if (!Objects.equals(product.getSellStatus(), "SELLING")) {
            throw new NotSellingProduct();
        }
        int totalPrice = product.getPrice() - orderDto.getPoint();
        int userPoint = user.getPoint();
        int productQuantity = product.getQuantity();
        if (userPoint < totalPrice) {
            throw new NotEnoughMoney();
        }
        Order order = new Order(user, product, orderDto, totalPrice);
        if (product.getQuantity() == 1) {
            product.setSellStatus("RESERVED");
        }
        product.setQuantity(productQuantity - 1);
        user.setPoint(userPoint - totalPrice);
        orderRepository.save(order);
        userRepository.save(user);
        productRepository.save(product);
    }
}
