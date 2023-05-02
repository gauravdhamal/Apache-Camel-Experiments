package com.csv.main;

import java.util.List;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.SimpleRegistry;

import com.csv.dto.Employee;
import com.csv.routes.CsvRouteBuilder;
import com.mysql.cj.jdbc.MysqlDataSource;

public class Main {

	public static void main(String[] args) throws Exception {

		MysqlDataSource mysqlDataSource = new MysqlDataSource();
		mysqlDataSource.setURL("jdbc:mysql://localhost:3306/camel_db");
		mysqlDataSource.setUser("root");
		mysqlDataSource.setPassword("root");

		SimpleRegistry simpleRegistry = new SimpleRegistry();
		simpleRegistry.put("dataSource", (Map<Class<?>, Object>) mysqlDataSource);

		CamelContext camelContext = new DefaultCamelContext(simpleRegistry);

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
