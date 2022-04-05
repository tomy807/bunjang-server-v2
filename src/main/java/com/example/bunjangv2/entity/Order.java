package com.example.bunjangv2.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue
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


}
