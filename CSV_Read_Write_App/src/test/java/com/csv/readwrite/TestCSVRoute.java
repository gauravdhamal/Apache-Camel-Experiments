package com.csv.readwrite;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

public class TestCSVRoute extends CamelTestSupport {
    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:src/main/resources?fileName=empData.csv&noop=true").to("mock:result");
            }
        };
    }

    @Test
    public void testCSVRoute() throws Exception {
        getMockEndpoint("mock:result").expectedMessageCount(1);

        MockEndpoint.assertIsSatisfied(context);
    }
}
