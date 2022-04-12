package com.example.bunjangv2.entity;

import com.example.bunjangv2.src.order.dto.OrderDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity(name = "Orders")
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@Table(name = "orders")
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false, length = 10, columnDefinition = "varchar(10) DEFAULT 'DIRECT'")
    private String tradingMethod;

    @Column(nullable = false, length = 10)
    private String payMethod;

    @Column(nullable = false, length = 10, columnDefinition = "varchar(10) DEFAULT 'RESERVED'")
    private String orderStatus;

    @Column(length = 20)
    private String cancelReason;

    @Column(columnDefinition = "int DEFAULT 0")
    private Integer totalPrice;

    @Column(length = 200)
    private String address;

    public Order(User user, Product product, OrderDto orderDto, int totalPrice,String address) {

        this.user = user;
        this.product = product;
        this.tradingMethod = orderDto.getTradingMethod();
        this.payMethod = orderDto.getPayMethod();
        this.totalPrice = totalPrice;
        this.address = address;
    }

    public void setUser(User user) {
        this.user = user;
        user.getOrders().add(this);
    }
}
