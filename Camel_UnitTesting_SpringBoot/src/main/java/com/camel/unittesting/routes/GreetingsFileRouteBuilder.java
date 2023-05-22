package com.camel.unittesting.routes;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class GreetingsFileRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:start")
                .routeId("greetings-route")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        String msg = exchange.getIn().getBody(String.class);
//                        System.out.println("Message : "+msg);
                        exchange.getIn().setBody(msg);
                    }
                })
                .log(LoggingLevel.ERROR, "Body : ${body}")
                .to("file:src/main/resources/output");

    }
}
