package com.camel.hello.example2;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class HelloWorld {

	public static void main(String[] args) {

		CamelContext camelContext = new DefaultCamelContext();

		try {
			camelContext.addRoutes(new HelloWorldRoute());
			camelContext.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
