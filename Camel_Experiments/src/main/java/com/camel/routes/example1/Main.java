package com.camel.routes.example1;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class Main {

	public static void main(String[] args) {

		FileTransferRoute fileTransferRoute = new FileTransferRoute();
		CamelContext camelContext = new DefaultCamelContext();

		try {
			camelContext.addRoutes(fileTransferRoute);
			camelContext.start();
			Thread.sleep(1 * 60 * 1000);
			camelContext.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
