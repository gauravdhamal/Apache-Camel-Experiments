package com.csv.seda.route;

import com.csv.seda.entity.Employee;
import com.csv.seda.processor.EmplyeeProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.support.DefaultMessage;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.apache.camel.LoggingLevel.ERROR;

//@Component
public class CSVRouteToSEDA extends RouteBuilder {

    @Override
    public void configure(){
//        from("file:src/main/resources?fileName=empData.csv&noop=true")
//                .unmarshal(new BindyCsvDataFormat(Employee.class))
//                .process(new EmplyeeProcessor())
//                .to("seda:getMessages");
//
//        from("seda:getMessages")
//                .to("direct:cmoplexProcess");
//
//        from("direct:cmoplexProcess")
//                .log(ERROR, "${body}");

        from("timer:ping?period=200")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Message message = new DefaultMessage(exchange);
                        message.setBody(new Date());
                        exchange.setMessage(message);
                    }
                })
                .to("seda:getMsg?multipleConsumers=true");

        from("seda:getMsg?multipleConsumers=true")
                .to("direct:employee");

        from("direct:employee")
                .process(exchange -> Thread.sleep(2000))
                .log(ERROR, "&{body}");
    }

}
