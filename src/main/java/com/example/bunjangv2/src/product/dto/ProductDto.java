package com.example.bunjangv2.src.product.dto;


import com.example.bunjangv2.entity.Product;
import com.example.bunjangv2.entity.User;
import com.example.bunjangv2.src.user.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ProductDto {

    private Long productIdx;
    private UserDto user;


    private Long categoryLarge;
    private Long categoryMiddle;
    private Long categorySmall;

    @NotEmpty(message = "상품명을 2글자 이상 입력해주세요")
    private String title;

    private String productStatus;

    private String exchangePossible;

    @NotNull(message = "100원 이상 입력해주세요")
    @Min(100)
    private Integer price;

    private String shippingFee;

    @NotEmpty(message = "설명을 입력해주세요")
    private String introduction;

    @Min(1)
    private int quantity;

    private String sellStatus;

    private String securePayment;

    private String directAddress;



    public ProductDto (Product product) {
        User user = product.getUser();
        this.productIdx = product.getId();
        this.title = product.getTitle();
        this.user = new UserDto(user.getId(), user.getShopName());
        this.categoryLarge = product.getCategoryLarge().getId();
        this.categoryMiddle = product.getCategoryMiddle().getId();
        this.categorySmall = product.getCategorySmall().getId();
        this.price = product.getPrice();
        this.introduction = product.getIntroduction();
        this.shippingFee = product.getShippingFee();
        this.quantity = product.getQuantity();
        this.sellStatus = product.getSellStatus();
        this.productStatus = product.getProductStatus();
        this.exchangePossible = product.getExchangePossible();
        this.securePayment = product.getSecurePayment();
        this.directAddress = product.getDirectAddress();
    }



}
