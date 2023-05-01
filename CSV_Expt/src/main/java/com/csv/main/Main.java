package com.csv.main;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import com.csv.routes.CsvRouteBuilder;

public class Main {

	public static void main(String[] args) throws Exception {

		CamelContext camelContext = new DefaultCamelContext();

		camelContext.addRoutes(new CsvRouteBuilder());

		camelContext.start();

		Thread.sleep(5000);

		camelContext.stop();
	}

}
