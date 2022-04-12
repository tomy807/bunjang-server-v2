package com.example.bunjangv2.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException (String MESSAGE) {
        super(MESSAGE + " 카테고리를 찾을수 없습니다.");
    }
}