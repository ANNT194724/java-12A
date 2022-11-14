package com.example.bmi.exeception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String s) {
        super(s);
    }
}
