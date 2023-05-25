package com.camel.unittesting;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IsMockEndpoints extends CamelTestSupport {
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start").to("direct:foo").to("log:foo").to("mock:result");

                from("direct:foo").transform(constant("Bye World"));
            }
        };
    }

    @Override
    public boolean isUseAdviceWith() {
        return super.isUseAdviceWith();
    }

    @Override
    public String isMockEndpoints() {
        return "*";
    }

    @Test
    public void testAllMocks() throws InterruptedException {
        getMockEndpoint("mock:direct:start").expectedBodiesReceived("Hello World");
        getMockEndpoint("mock:direct:foo").expectedBodiesReceived("Hello World");
        getMockEndpoint("mock:log:foo").expectedBodiesReceived("Bye World");
        getMockEndpoint("mock:result").expectedBodiesReceived("Bye World");

        template.sendBody("direct:start", "Hello World");

        assertMockEndpointsSatisfied();

        Assertions.assertNotNull(context.hasEndpoint("direct:start"));
        Assertions.assertNotNull(context.hasEndpoint("direct:foo"));
        Assertions.assertNotNull(context.hasEndpoint("log:foo"));
        Assertions.assertNotNull(context.hasEndpoint("mock:result"));

        Assertions.assertNotNull(context.hasEndpoint("mock:direct:start"));
        Assertions.assertNotNull(context.hasEndpoint("mock:direct:foo"));
        Assertions.assertNotNull(context.hasEndpoint("mock:log:foo"));

//        AdviceWith.adviceWith()
    }
}
