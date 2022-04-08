package com.example.bunjangv2.entity;


import com.example.bunjangv2.src.product.dto.ProductDto;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "products")
@DynamicInsert
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(nullable = false, length = 10, columnDefinition = "varchar(10) DEFAULT 'USED'")
    private String productStatus;

    @Column(nullable = false, length = 20, columnDefinition = "varchar(20) default 'NONEXCHANGEABLE'")
    private String exchangePossible;

    @Column(nullable = false, columnDefinition = "integer DEFAULT 1")
    private Integer price;

    @Column(nullable = false, length = 10, columnDefinition = "varchar(10) DEFAULT 'EXCLUDE'")
    private String shippingFee;

    @Column(columnDefinition = "TEXT")
    private String introduction;

    @Column(nullable = false, columnDefinition = "integer DEFAULT 1")
    private Integer quantity;

    @Column(nullable = false, length = 10, columnDefinition = "varchar(10) DEFAULT 'SELLING'")
    private String sellStatus;

    @Column(nullable = false, length = 10, columnDefinition = "varchar(10) DEFAULT 'SECURE'")
    private String securePayment;

    @Column(length = 30, columnDefinition = "varchar(30) DEFAULT '지역 정보 없음'")
    private String directAddress;

//    public Product createProduct(ProductDto productDto,User user) {
//        this.user = user;
//        this.categoryLarge = productDto.getCategoryLarge();
//        this.categoryMiddle = categoryMiddle;
//        this.categorySmall = categorySmall;
//        this.title = title;
//        this.productStatus = productStatus;
//        this.exchangePossible = exchangePossible;
//        this.price = price;
//        this.shippingFee = shippingFee;
//        this.introduction = introduction;
//        this.quantity = quantity;
//        this.sellStatus = sellStatus;
//        this.securePayment = securePayment;
//    }


//    public Product(Long id, User user, CategoryLarge categoryLarge, CategoryMiddle categoryMiddle, CategorySmall categorySmall, String title, String productStatus, String exchangePossible, Integer price, String shippingFee, String introduction, Integer quantity, String sellStatus, String securePayment) {
//        this.id = id;
//        this.user = user;
//        this.categoryLarge = categoryLarge;
//        this.categoryMiddle = categoryMiddle;
//        this.categorySmall = categorySmall;
//        this.title = title;
//        this.productStatus = productStatus;
//        this.exchangePossible = exchangePossible;
//        this.price = price;
//        this.shippingFee = shippingFee;
//        this.introduction = introduction;
//        this.quantity = quantity;
//        this.sellStatus = sellStatus;
//        this.securePayment = securePayment;
//    }
}
