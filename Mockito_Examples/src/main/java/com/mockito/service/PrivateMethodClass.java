package com.mockito.service;

public class PrivateMethodClass {
    private String privateMethod(String message) {
        return message;
    }

    public String callPrivateMethod(String message) {
        return this.privateMethod(message);
    }
}
