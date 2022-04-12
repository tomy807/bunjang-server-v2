package com.example.bunjangv2.src.follow.dto;

import com.example.bunjangv2.entity.Product;
import com.example.bunjangv2.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
public class FollowDto {

    private Long userIdx;
    private String  shopName;
    private Integer productCount;
    private Integer followerCount;
    private List<FollowUserProductDto> products;

    @Data
    @AllArgsConstructor
    private static class FollowUserProductDto {

        private Long productIdx;

        private Integer price;

        public FollowUserProductDto(Product product) {
            this.productIdx = product.getId();
            this.price = product.getPrice();
        }
    }

    public FollowDto(User user, int followerCount, List<Product> products) {
        this.userIdx = user.getId();
        this.shopName = user.getShopName();
        this.productCount = user.getProducts().size();
        this.followerCount = followerCount;
        this.products=products.stream().map(FollowUserProductDto::new).collect(Collectors.toList());
    }
}
