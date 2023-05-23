package com.camel.unittesting.routes;

import com.camel.unittesting.beans.SimpleProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RouteWithBean extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:start")
                .bean(SimpleProcessor.class)
                .to("mock:result");
//        from("mock:result")
//                .log(LoggingLevel.ERROR, " Body : ${body}");
    }
}
