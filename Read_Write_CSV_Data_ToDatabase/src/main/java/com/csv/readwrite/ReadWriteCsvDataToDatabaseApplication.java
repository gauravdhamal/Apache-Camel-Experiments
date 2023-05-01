package com.csv.readwrite;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.csv.readwrite.routes.CSVRoute;

@SpringBootApplication
public class ReadWriteCsvDataToDatabaseApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ReadWriteCsvDataToDatabaseApplication.class, args);

		CamelContext camelContext = new DefaultCamelContext();

		camelContext.addRoutes(new CSVRoute());

		camelContext.start();

		Thread.sleep(5000);

		camelContext.stop();
	}

}
