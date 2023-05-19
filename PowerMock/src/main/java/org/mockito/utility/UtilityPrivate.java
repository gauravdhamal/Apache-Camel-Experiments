package org.mockito.utility;

public class UtilityPrivate {

    private String privateMethod(String message){
        System.out.println("Inside private method.");
        return message;
    }

    public String callPrivateMethod(String message){
        System.out.println("Private method called.");
        return this.privateMethod(message);
    }

}
