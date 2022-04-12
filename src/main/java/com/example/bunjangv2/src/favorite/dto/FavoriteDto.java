package com.example.bunjangv2.src.favorite.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class FavoriteDto {

    private Long productIdx;

    private String title;

    private Integer price;

    private String sellStatus;

    private String shopName;

    private String securePayment;

    private LocalDateTime createdAt;
}
