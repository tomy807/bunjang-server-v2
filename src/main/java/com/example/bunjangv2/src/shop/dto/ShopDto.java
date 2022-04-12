package com.example.bunjangv2.src.shop.dto;

import com.example.bunjangv2.entity.Product;
import com.example.bunjangv2.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopDto {

    private String shopName;

    private String userName;

    private String introduction;

    private Long openDay;

    private Integer productCount;

    private List<ProductByShop> products;

    @Data
    @AllArgsConstructor
    public static class ProductByShop {

        private Long productIdx;

        private String title;

        private int price;

        private String securePayment;

        public ProductByShop(Product product) {
            this.productIdx = product.getId();
            this.title = product.getTitle();
            this.price = product.getPrice();
            this.securePayment = product.getSecurePayment();
        }

    }

    @Data
    @AllArgsConstructor
    public static class Follow {

        private Long userIdx;

        private String shopName;

        private Integer productCount;

        private Integer followerCount;

        private List<ProductByShop> products;

        public Follow(User user) {
            this.userIdx = user.getId();
            this.shopName = user.getShopName();
            this.productCount = (int) user.getProducts().stream().filter(product -> product.getSellStatus().equals("SELLING")).count();
            this.followerCount = user.getFollowers().size();
            this.products = user.getProducts().stream().map(ShopDto.ProductByShop::new).collect(Collectors.toList());
        }
    }



    public ShopDto(User user, List<Product> products) {
        this.shopName = user.getShopName();
        this.userName = user.getName();
        this.introduction = user.getIntroduction();
        this.openDay=Duration.between(LocalDateTime.now(), user.getCreatedDate()).toDays();
        this.productCount = products.size();
        this.products=products.stream().map(ProductByShop::new).collect(Collectors.toList());

    }

}
