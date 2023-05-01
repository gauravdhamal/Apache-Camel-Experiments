package com.csv.main;

import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import com.csv.dto.Employee;
import com.csv.routes.CsvRouteBuilder;

public class Main {

	public static void main(String[] args) throws Exception {

		CamelContext camelContext = new DefaultCamelContext();

		camelContext.addRoutes(new CsvRouteBuilder());

		camelContext.start();

		Thread.sleep(5000);

		List<Employee> employees = CsvRouteBuilder.getEmps();

		for (Employee emp : employees) {
			System.out.println(emp);
		}

		camelContext.stop();

	}
}
