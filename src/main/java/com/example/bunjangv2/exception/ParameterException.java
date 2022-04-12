package com.example.bunjangv2.exception;

public class ParameterException extends RuntimeException {
    public ParameterException (String MESSAGE) {
        super(MESSAGE + " RequestParam 를 입력해주세요.");
    }
}
