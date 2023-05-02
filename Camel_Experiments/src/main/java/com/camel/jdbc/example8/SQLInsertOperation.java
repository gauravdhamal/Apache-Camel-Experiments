package com.camel.jdbc.example8;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

import com.mysql.cj.jdbc.MysqlDataSource;

public class SQLInsertOperation {

	public static void main(String[] args) throws Exception {

		MysqlDataSource mysqlDataSource = new MysqlDataSource();
		mysqlDataSource.setUrl("jdbc:mysql://localhost:3306/camel_db");
		mysqlDataSource.setUser("root");
		mysqlDataSource.setPassword("root");

		SimpleRegistry simpleRegistry = new SimpleRegistry();
		simpleRegistry.put("dataSource", mysqlDataSource);

		CamelContext camelContext = new DefaultCamelContext(simpleRegistry);

		camelContext.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("direct:insert").process(new Processor() {
					public void process(Exchange exchange) throws Exception {
						String query = "insert into customer(id) values(6)";
						exchange.getIn().setBody(query);
					}
				}).to("jdbc:dataSource");
			}
		});

		camelContext.start();

	}

}
