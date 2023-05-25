package com.mockito.service;

public class FinalMethodClass {
    public final String finalMethod(String message){
        System.out.println("Final method called.");
        return message;
    }
}
