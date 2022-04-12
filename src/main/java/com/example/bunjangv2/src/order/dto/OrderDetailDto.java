package com.example.bunjangv2.src.order.dto;

import com.example.bunjangv2.entity.Order;
import com.example.bunjangv2.entity.Product;
import com.example.bunjangv2.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class OrderDetailDto {


    private Long orderId;
    private Long productId;
    private String title;
    private int price;

    private String orderStatus;
    private String tradingMethod;
    private String buyer;
    private String seller;
    private String shippingFee;
    private int totalPrice;
    private int year;
    private int month;
    private int day;
    private int time;

    private String address;
    private String directAddress;
    private String buyerPhone;
    private String sellerPhone;

    public OrderDetailDto(Order order, User user, User seller, Product product) {

        this.orderId = order.getId();
        this.productId = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.orderStatus = order.getOrderStatus();
        this.tradingMethod = order.getTradingMethod();
        this.buyer = user.getName();
        this.seller = seller.getName();
        this.shippingFee =product.getShippingFee();
        this.totalPrice = order.getTotalPrice();
        LocalDateTime createdDate = order.getCreatedDate();
        this.year = createdDate.getYear();
        this.month = createdDate.getMonth().getValue();
        this.day = createdDate.getDayOfMonth();
        this.time = createdDate.getHour();
        this.address = order.getAddress();
        this.directAddress = product.getDirectAddress();
        this.buyerPhone = user.getPhone();
        this.sellerPhone = seller.getPhone();
    }
}
