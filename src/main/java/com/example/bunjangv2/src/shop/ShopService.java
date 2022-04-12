package com.example.bunjangv2.src.shop;

import com.example.bunjangv2.entity.Follow;
import com.example.bunjangv2.entity.Product;
import com.example.bunjangv2.entity.User;
import com.example.bunjangv2.src.product.ProductRepository;
import com.example.bunjangv2.src.shop.dto.ShopDto;
import com.example.bunjangv2.src.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ShopDto getShop(Long shopIdx) {

        User user = userRepository.findById(shopIdx).orElseThrow(EntityNotFoundException::new);
        List<Product> products = user.getProducts().stream()
                .filter(product -> product.getSellStatus().equals("SELLING"))
                .collect(Collectors.toList());
        return new ShopDto(user, products);
    }

    public List<ShopDto.ProductByShop> getProducts(Long shopIdx) {
        User user = userRepository.findById(shopIdx).orElseThrow(EntityNotFoundException::new);
        List<Product> products = user.getProducts().stream()
                .filter(product -> product.getSellStatus().equals("SELLING"))
                .collect(Collectors.toList());
        return products.stream().map(ShopDto.ProductByShop::new).collect(Collectors.toList());

    }

    public List<ShopDto.Following> getFollowings(Long shopIdx) {
        User user = userRepository.findById(shopIdx).orElseThrow(EntityNotFoundException::new);
        List<Follow> followings = user.getFollowings();

        return followings.stream().map(Follow::getToUser).map(ShopDto.Following::new).collect(Collectors.toList());

    }
}
