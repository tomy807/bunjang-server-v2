package com.example.bunjangv2.exception;


import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {

    //이미 존재하는 이메일로 회원가입 시도
    @ExceptionHandler(AlreadyExistEmailException.class)
    public ExceptionResponse alreadyExistEmailException(AlreadyExistEmailException e) {
        return new ExceptionResponse(false, 2017, e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    //판매중이 아닌 상품을 구매하려고 시도
    @ExceptionHandler(NotSellingProduct.class)
    public ExceptionResponse notSellingProduct(NotSellingProduct e) {
        return new ExceptionResponse(false, 2018, e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    //판매중이 아닌 상품을 구매하려고 시도
    @ExceptionHandler(NotEnoughMoney.class)
    public ExceptionResponse notEnoughMoney(NotEnoughMoney e) {
        return new ExceptionResponse(false, 2019, e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    //판매중이 아닌 상품을 구매하려고 시도
    @ExceptionHandler(LimitAddressCount.class)
    public ExceptionResponse limitAddressCount(LimitAddressCount e) {
        return new ExceptionResponse(false, 2020, e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ParameterException.class)
    public ExceptionResponse parameterException(ParameterException e) {
        return new ExceptionResponse(false, 2021, e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    //Validation 예외처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity postValidation(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("isSuccess", "false");
        e.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ExceptionResponse loginException(IllegalArgumentException e) {
        return new ExceptionResponse(false, 2018, e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SignatureException.class)
    public ResponseEntity<ErrorCode> handleSignatureJwtException() {
        ErrorCode errorCode = ErrorCode.SignatureException;
        return new ResponseEntity<>(errorCode, errorCode.getHttpStatus());
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ExceptionResponse entityNotFoundException(EntityNotFoundException e) {
        return new ExceptionResponse(false, 2020, e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
