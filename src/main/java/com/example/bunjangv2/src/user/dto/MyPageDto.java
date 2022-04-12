package com.example.bunjangv2.src.user.dto;

import com.example.bunjangv2.entity.Order;
import com.example.bunjangv2.entity.Product;
import com.example.bunjangv2.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
public class MyPageDto {

    @AllArgsConstructor
    @Data
    public static class UserInfo{

        private String shopName;

        private Integer favoriteCount;

        private Integer followerCount;

        private Integer followingCount;

        public UserInfo(User user) {
            this.shopName = user.getShopName();
            this.favoriteCount = user.getFavorites().size();
            this.followerCount = user.getFollowers().size();
            this.followingCount = user.getFollowings().size();
        }
    }
    @AllArgsConstructor
    @Data
    public static class MyProducts {

        private Integer productCount;

        private String sellStatus;

        private List<MyProduct> products;

        public MyProducts(List<Product> products,String status) {
            this.productCount = products.size();
            this.sellStatus = status;
            this.products = products.stream().map(MyProduct::new).collect(Collectors.toList());
        }

        @AllArgsConstructor
        @Data
        private static class MyProduct {

            private Long productIdx;

            private String title;

            private Integer price;

            private String securePayment;

            private String sellStatus;

            private LocalDateTime createdAt;

            public MyProduct(Product product) {
                this.productIdx = product.getId();
                this.title = product.getTitle();
                this.price = product.getPrice();
                this.securePayment = product.getSecurePayment();
                this.sellStatus = product.getSellStatus();
                this.createdAt = product.getCreatedDate();
            }
        }
    }

    @AllArgsConstructor
    @Data
    public static class MyFollower {
        private Long userIdx;
        private String shopName;
        private Integer productCount;
        private Integer followerCount;

        public MyFollower(User user) {
            this.userIdx = user.getId();
            this.shopName = user.getShopName();
            this.productCount = user.getProducts().size();
            this.followerCount = user.getFollowers().size();
        }
    }

    @AllArgsConstructor
    @Data
    public static class MyFollowing {
        private Long userIdx;
        private String shopName;
        private Integer productCount;
        private Integer followerCount;
        private List<FollowingProduct> products;

        public MyFollowing(User user) {
            this.userIdx = user.getId();
            this.shopName = user.getShopName();
            this.productCount = user.getProducts().size();
            this.followerCount = user.getFollowers().size();
            this.products=user.getProducts().stream().map(FollowingProduct::new).collect(Collectors.toList());
        }
        @AllArgsConstructor
        @Data
        private static class FollowingProduct {

            private Long productIdx;

            private Integer price;

            public FollowingProduct(Product product) {
                this.productIdx = product.getId();
                this.price = product.getPrice();
            }
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class MySellOrder {

        private Long orderIdx;

        private String orderStatus;

        private String title;

        private Integer price;

        private String shopName;

        private String securePayment;

        private LocalDateTime orderDay;

        public MySellOrder(Order order) {
            this.orderIdx = order.getId();
            this.orderStatus = order.getOrderStatus();
            this.title = order.getProduct().getTitle();
            this.price = order.getProduct().getPrice();
            this.shopName = order.getUser().getShopName();
            this.securePayment = order.getProduct().getSecurePayment();
            this.orderDay = order.getCreatedDate();
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class MyPurchaseOrder {

        private Long orderIdx;

        private String orderStatus;

        private String title;

        private Integer price;

        private String shopName;

        private String securePayment;

        private LocalDateTime orderDay;

        public MyPurchaseOrder(Order order) {
            this.orderIdx = order.getId();
            this.orderStatus = order.getOrderStatus();
            this.title = order.getProduct().getTitle();
            this.price = order.getProduct().getPrice();
            this.shopName = order.getProduct().getUser().getShopName();
            this.securePayment = order.getProduct().getSecurePayment();
            this.orderDay = order.getCreatedDate();
        }
    }
}
