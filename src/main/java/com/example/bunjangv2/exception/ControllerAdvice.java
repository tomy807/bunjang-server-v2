package com.example.bunjangv2.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {

    //이미 존재하는 이메일로 회원가입 시도
    @ExceptionHandler(AlreadyExistEmailException.class)
    public ExceptionResponse alreadyExistEmailException(AlreadyExistEmailException e) {
        return new ExceptionResponse(false, 2017, e.getMessage());
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
        return new ExceptionResponse(false, 2018, e.getMessage());
    }

}