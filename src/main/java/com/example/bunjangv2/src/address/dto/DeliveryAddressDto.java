package com.example.bunjangv2.src.address.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@NoArgsConstructor
public class DeliveryAddressDto {

    @NotEmpty(message = "주소를 입력해주세요")
    private String address;
    @NotEmpty(message = "상세주소를 입력해주세요")
    private String detailAddress;
    @NotEmpty(message = "기본주소 설정을 입력해주세요")
    private String main;

}
