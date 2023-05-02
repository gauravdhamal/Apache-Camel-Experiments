package com.csv.routes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;

import com.csv.dto.Employee;

public class CsvRouteBuilder extends RouteBuilder {

	static List<Employee> employees = new ArrayList<Employee>();

	@Override
	public void configure() throws Exception {
		from("file:src/main/resources?fileName=employeesdata.csv&noop=true")
				.unmarshal(new BindyCsvDataFormat(Employee.class)).process(new Processor() {
					public void process(Exchange exchange) throws Exception {
						List<Employee> employees = exchange.getIn().getBody(List.class);
						Map<String, Object> empMap = new HashMap<String, Object>();
						for (Employee emp : employees) {
							empMap.put("id", emp.getId());
							empMap.put("name", emp.getName());
							empMap.put("address", emp.getAddress());
							empMap.put("role", emp.getRole());
						}
						exchange.getIn().setBody(empMap);
					}
				}).to("sql:INSERT INTO employee(id,name,address,role) VALUES(:#id, :#name, :#address, :#role)");
	}

	public static List<Employee> getEmps() {
		return employees;
	}
}
