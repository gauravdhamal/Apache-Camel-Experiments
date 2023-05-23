package com.camel.unittesting.beans;

public class SimpleProcessor{
    public String process(String body){
        return body+" Updated";
    }
}
