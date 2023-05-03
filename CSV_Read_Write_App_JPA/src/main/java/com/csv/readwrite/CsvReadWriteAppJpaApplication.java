package com.csv.readwrite;

import com.csv.readwrite.entity.Employee;
import com.csv.readwrite.route.CSVRouteJPA;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CsvReadWriteAppJpaApplication{

	public static void main(String[] args) {
		SpringApplication.run(CsvReadWriteAppJpaApplication.class, args);
	}

}
