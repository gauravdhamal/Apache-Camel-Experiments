package com.csv.routes;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;

import com.csv.dto.Employee;

public class CsvRouteBuilder extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		from("file:src/main/resources?fileName=employeesdata.csv&noop=true")
				.unmarshal(new BindyCsvDataFormat(Employee.class)).process(new Processor() {
					public void process(Exchange exchange) throws Exception {
						List<Employee> employees = exchange.getIn().getBody(List.class);
						for (Employee emp : employees) {
							System.out.println(emp);
						}
					}
				});
	}
}
