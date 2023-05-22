package com.camel.unittesting;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

public class SimpleMockTest extends CamelTestSupport {
    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("leadtsoquote.scheduler").to("mock:result");
            }
        };
    }

    @Test
    public void testMock() throws InterruptedException {
        getMockEndpoint("mock:result").expectedBodiesReceived("Hello World");

        template.sendBody("leadtsoquote.scheduler", "Hello World");

        assertMockEndpointsSatisfied();
    }
}
