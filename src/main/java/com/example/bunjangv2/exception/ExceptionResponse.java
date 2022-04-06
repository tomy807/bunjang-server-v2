package com.example.bunjangv2.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
public class ExceptionResponse {
    private Boolean isSuccess;
    private Integer code;
    private String message;
    private HttpStatus httpStatus;
}
