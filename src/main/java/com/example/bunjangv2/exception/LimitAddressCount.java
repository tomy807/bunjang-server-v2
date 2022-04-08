package com.example.bunjangv2.exception;

public class LimitAddressCount extends RuntimeException {
    private static final String MESSAGE = "주소는 3개까지만 저장할수 있습니다.";
    public LimitAddressCount () {
        super(MESSAGE);
    }
}