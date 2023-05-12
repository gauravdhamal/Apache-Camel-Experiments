package com.csv.seda.entity;

import org.apache.camel.Exchange;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeBean {
    @Bean
    public void imBean(){
        System.out.println("Im bean...");
    }
}
