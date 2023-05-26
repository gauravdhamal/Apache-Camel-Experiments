package com.mockito.service;

public class PrivateMethodClass {
    private String privateMethod(String message) {
        System.out.println("privateMethod");
        return message;
    }

    public String callPrivateMethod(String message) {
        System.out.println("callPrivateMethod");
        return this.privateMethod(message);
    }
}
