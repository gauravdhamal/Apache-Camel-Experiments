package com.camel.jdbc.example8;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

import com.mysql.cj.jdbc.MysqlDataSource;

public class SQLOperation {

	public static void main(String[] args) throws Exception {

		MysqlDataSource mysqlDataSource = new MysqlDataSource();
		mysqlDataSource.setURL("jdbc:mysql://localhost:3306/camel_db");
		mysqlDataSource.setUser("root");
		mysqlDataSource.setPassword("root");

		SimpleRegistry simpleRegistry = new SimpleRegistry();
		simpleRegistry.put("dataSource", mysqlDataSource);

		CamelContext camelContext = new DefaultCamelContext(simpleRegistry);

		camelContext.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("direct:start").to("jdbc:dataSource").bean(new ResultHandler(), "printResult");
			}
		});

		camelContext.start();

		ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
		producerTemplate.sendBody("direct:start", "select * from customer");

	}

}
