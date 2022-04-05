package com.example.bunjangv2.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_large_id")
    private CategoryLarge categoryLarge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_middle_id")
    private CategoryMiddle categoryMiddle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_small_id")
    private CategorySmall categorySmall;

    @Column(nullable = false, length = 40)
    private String title;

    @Column(nullable = false, length = 10,columnDefinition = "varchar(10) DEFAULT 'USED'")
    private String productStatus;

    @Column(nullable = false, length = 20)
    private String exchangePossible;

    @Column(nullable = false,columnDefinition = "integer DEFAULT 1")
    private Integer price;

    @Column(nullable = false, length = 10,columnDefinition = "varchar(10) DEFAULT 'NONEXCHANGEABLE'")
    private String shippingFee;

    @Column(columnDefinition = "TEXT")
    private String introduction;

    @Column(nullable = false,columnDefinition = "integer DEFAULT 0")
    private Integer quantity;

    @Column(nullable = false, length = 10,columnDefinition = "varchar(10) DEFAULT 'SELLING'")
    private String sellStatus;

    @Column(nullable = false, length = 10, columnDefinition = "varchar(10) DEFAULT 'SECURE'")
    private String securePayment;


}
