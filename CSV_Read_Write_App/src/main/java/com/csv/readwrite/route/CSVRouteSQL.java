package com.csv.readwrite.route;

import com.csv.readwrite.entity.Employee;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CSVRouteSQL extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:src/main/resources?fileName=empData.csv&noop=true")
                .unmarshal(new BindyCsvDataFormat(Employee.class))
                .split(body())
                .streaming()
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Employee employee = exchange.getIn().getBody(Employee.class);
                        Map<String, Object> employeeMap = new HashMap<>();
                        employeeMap.put("id", employee.getId());
                        employeeMap.put("name", employee.getName());
                        employeeMap.put("address", employee.getAddress());
                        employeeMap.put("role", employee.getRole());
                        exchange.getIn().setBody(employeeMap);
                    }
                })
                .log("Body : ${body}")
                .to("sql:INSERT INTO employee(id,name,address,role) VALUES(:#id, :#name, :#address, :#role)")
                .log("Data inserted to database success.");
    }
}
