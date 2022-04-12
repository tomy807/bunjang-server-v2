package com.example.bunjangv2.src.shop;

import com.example.bunjangv2.entity.Product;
import com.example.bunjangv2.entity.User;
import com.example.bunjangv2.src.product.ProductRepository;
import com.example.bunjangv2.src.shop.dto.ShopDto;
import com.example.bunjangv2.src.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ShopDto getShop(Long shopIdx) {

        User user = getUser(shopIdx);
        List<Product> products = user.getProducts().stream()
                .filter(product -> product.getSellStatus().equals("SELLING"))
                .collect(Collectors.toList());
        return new ShopDto(user, products);
    }

    public List<ShopDto.ProductByShop> getProducts(Long shopIdx) {
        User user = getUser(shopIdx);
        List<Product> products = user.getProducts().stream()
                .filter(product -> product.getSellStatus().equals("SELLING"))
                .collect(Collectors.toList());
        return products.stream().map(ShopDto.ProductByShop::new).collect(Collectors.toList());

    }

    public List<ShopDto.Follow> getFollowings(Long shopIdx) {
        User user = getUser(shopIdx);
        List<com.example.bunjangv2.entity.Follow> followings = user.getFollowings();

        return followings.stream().map(com.example.bunjangv2.entity.Follow::getToUser).map(ShopDto.Follow::new).collect(Collectors.toList());

    }


    public List<ShopDto.Follow> getFollowers(Long shopIdx) {

        User user = getUser(shopIdx);
        List<com.example.bunjangv2.entity.Follow> followings = user.getFollowers();

        return followings.stream().map(com.example.bunjangv2.entity.Follow::getFromUser).map(ShopDto.Follow::new).collect(Collectors.toList());


    }

    private User getUser(Long shopIdx) {
        return userRepository.findById(shopIdx).orElseThrow(() -> new EntityNotFoundException("해당 유저를 찾을수 없습니다"));
    }

}
