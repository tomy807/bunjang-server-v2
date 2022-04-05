package com.example.bunjangv2.src.user.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResDto {

    private String token;
    private Long userIdx;
}
