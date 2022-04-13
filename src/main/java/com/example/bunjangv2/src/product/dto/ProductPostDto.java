package com.example.bunjangv2.src.product.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPostDto {


    @NotEmpty(message = "상품명을 2글자 이상 입력해주세요")
    private String title;

    private Long categoryLarge;
    private Long categoryMiddle;
    private Long categorySmall;

    @NotNull(message = "100원 이상 입력해주세요")
    @Min(100)
    private Integer price;

    @NotEmpty(message = "설명을 입력해주세요")
    private String introduction;

    @Min(1)
    private int quantity;

    private String productStatus;

    private String exchangePossible;

    private String securePayment;

}
