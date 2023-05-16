package com.splitter.routes;

import com.splitter.entities.Employee;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CSVRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:src/main/resources?fileName=empData.csv&noop=true")
                .split(body().tokenize(","))
//                .unmarshal(new BindyCsvDataFormat(Employee.class))
//                .process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//                        List<Employee> employees = exchange.getIn().getBody(List.class);
//                        System.out.println("employees : "+employees);
//                        exchange.getIn().setBody(employees);
//                    }
//                })
                .log("Body ${body}");
    }
}
