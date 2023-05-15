package com.aggregator.routes;

import com.aggregator.aggregation.DateAggregator;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
public class CamelRoute extends RouteBuilder {
    final String CORRELATION_ID = "correlationId";

    @Override
    public void configure() throws Exception {

        Random random = new Random();

        from("timer:insurance?period=1000")
                .process(exchange -> {
                    Message message = exchange.getMessage();
                    message.setHeader(CORRELATION_ID, random.nextInt(4));
                    message.setBody(new Date() + "");
                })
                .aggregate(header(CORRELATION_ID), new DateAggregator())
                .completionSize(3)
//                .to("log:select?showAll=true&multiline=true");
                .log(LoggingLevel.ERROR, "Header.id : ${header."+CORRELATION_ID+"}, Body : "+"${body}");
    }
}
