package com.csv.readwrite.routes;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.csv.readwrite.dto.Employee;
import com.csv.readwrite.service.EmployeeServiceImpl;

@Component
public class CSVRoute extends RouteBuilder {

	@Autowired
	public EmployeeServiceImpl employeeServiceImpl;

	@Override
	public void configure() throws Exception {
		from("file:src/main/resources?fileName=employeesdata.csv&noop=true")
				.unmarshal(new BindyCsvDataFormat(Employee.class)).process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						List<Employee> employees = exchange.getIn().getBody(List.class);
						for (Employee emp : employees) {
							emp = employeeServiceImpl.saveEmp(emp);
							System.out.println(emp);
						}
					}
				});
	}

}
