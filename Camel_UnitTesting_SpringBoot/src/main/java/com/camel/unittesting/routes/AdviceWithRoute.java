package com.camel.unittesting.routes;

import org.apache.camel.builder.RouteBuilder;

public class AdviceWithRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:start").id("greeting")
                .to("direct:stop")
                .log("Body of direct:start : ${body}");

        from("direct:beforeStart")
                .log("Body of direct:beforeStart : ${body}");

        from("direct:first").id("direct:first")
                .log("Not in use...");
    }
}
