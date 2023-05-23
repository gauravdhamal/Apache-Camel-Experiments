package com.camel.unittesting;

import com.camel.unittesting.routes.RouteWithBean;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

public class TestRouteWithBean extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RouteWithBean();
//                new RouteBuilder() {
//            @Override
//            public void configure() throws Exception {
//                from("direct:start").to("mock:result");
//            }
//        };
    }

    @Test
    public void testRouteWithBean() throws InterruptedException {
        getMockEndpoint("mock:result").expectedMessageCount(11);
        getMockEndpoint("mock:result").expectedBodiesReceived("Hello World Updated");

        template.sendBody("direct:start", "Hello World");

        assertMockEndpointsSatisfied();
    }
}
