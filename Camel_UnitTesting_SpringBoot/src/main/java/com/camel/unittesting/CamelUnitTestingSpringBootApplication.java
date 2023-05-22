package com.camel.unittesting;

import com.camel.unittesting.routes.GreetingsFileRouteBuilder;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CamelUnitTestingSpringBootApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(CamelUnitTestingSpringBootApplication.class, args);
	}
}
