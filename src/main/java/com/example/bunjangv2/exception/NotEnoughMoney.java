package com.example.bunjangv2.exception;

public class NotEnoughMoney extends RuntimeException {
    private static final String MESSAGE = "판매하고있는 상품이 아닙니다.";
    public NotEnoughMoney () {
        super(MESSAGE);
    }
}
