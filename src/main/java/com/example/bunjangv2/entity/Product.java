package com.example.bunjangv2.entity;


import com.example.bunjangv2.src.product.dto.ProductDto;
import com.example.bunjangv2.src.product.dto.ProductPostDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product")
@DynamicInsert
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_large_id")
    private CategoryLarge categoryLarge;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_middle_id")
    private CategoryMiddle categoryMiddle;

    @JsonIgnore
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

    public static Product createProduct(ProductPostDto productDto, CategoryLarge categoryLarge, CategoryMiddle categoryMiddle, CategorySmall categorySmall, User user, String directAddress) {
        Product product = new Product();
        product.setUser(user);
        product.setCategoryLarge(categoryLarge);
        product.setCategoryMiddle(categoryMiddle);
        product.setCategorySmall(categorySmall);
        product.setTitle(productDto.getTitle());
        product.setProductStatus(productDto.getProductStatus());
        product.setExchangePossible(productDto.getExchangePossible());
        product.setPrice(productDto.getPrice());
        product.setIntroduction(productDto.getIntroduction());
        product.setQuantity(productDto.getQuantity());
        product.setSecurePayment(productDto.getSecurePayment());
        product.setDirectAddress(directAddress);
        return product;
    }

    public Product (ProductDto productDto, CategoryLarge categoryLarge, CategoryMiddle categoryMiddle, CategorySmall categorySmall, User user,String directAddress) {

        this.user = user;
        this.setUser(user);
        this.categoryLarge = categoryLarge;
        this.categoryMiddle = categoryMiddle;
        this.categorySmall = categorySmall;
        this.title = productDto.getTitle();
        this.productStatus = productDto.getProductStatus();
        this.exchangePossible = productDto.getExchangePossible();
        this.price = productDto.getPrice();
        this.shippingFee = productDto.getShippingFee();
        this.introduction = productDto.getIntroduction();
        this.quantity = productDto.getQuantity();
        this.sellStatus = productDto.getSellStatus();
        this.securePayment = productDto.getSecurePayment();
        this.directAddress = directAddress;
    }

    public void setUser(User user) {
        this.user = user;
        user.getProducts().add(this);
    }
}
