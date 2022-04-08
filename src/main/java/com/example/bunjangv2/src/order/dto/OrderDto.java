package com.example.bunjangv2.src.order.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class OrderDto {

    @NotEmpty(message = "주문할 상품을 선택해주세요")
    private Long productId;
    @NotEmpty(message = "거래 방식을 선택해주세요")
    private String tradingMethod;
    @NotEmpty(message = "구매 방식을 선택해주세요")
    private String payMethod;
    @NotEmpty(message = "사용할 포인트를 입력해주세요")
    private Integer point;

}
