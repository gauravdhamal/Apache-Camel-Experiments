package com.csv.readwrite.route;

import com.csv.readwrite.entity.Employee;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CSVRouteJPA extends RouteBuilder {

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
                        exchange.getIn().setBody(employee);
                    }
                })
//                .log("msg : ${body}")
                .to("jpa:Employee")
                .log("Emp added to db");

        from("jpa://com.csv.readwrite.entity.Employee?consumeDelete=false")
                .setHeader("CamelJpaNamedQuery", constant("Employee.findAll"))
                .log("Body : ${body}");
    }

    // To ignore the first line.
    // .split().tokenize("\n", 1, true)

//        from("direct:insertEmployee")
//                .to("jpa:Employee")
//                .log("User inserted to db.");
}
