package com.csv.readwrite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CsvReadWriteAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvReadWriteAppApplication.class, args);

		/**
		 * If we are calling this exlpicitly then we are getting
		 * error of DataSource not defined.
		 */
//		CamelContext camelContext = new DefaultCamelContext();
//		camelContext.addRoutes(new CSVRoute());
//
//		camelContext.start();
//
//		Thread.sleep(5000);
//
//		camelContext.stop();
	}

}
