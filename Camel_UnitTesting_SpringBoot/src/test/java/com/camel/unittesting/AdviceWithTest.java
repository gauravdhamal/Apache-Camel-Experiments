package com.camel.unittesting;

import com.camel.unittesting.routes.AdviceWithRoute;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.SimpleBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import java.beans.SimpleBeanInfo;

public class AdviceWithTest extends CamelTestSupport {
    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new AdviceWithRoute();
    }

    @Override
    public boolean isUseAdviceWith() {
        return true;
    }

    // Working
    @Test
    public void testingAdviceWith() throws Exception {
        AdviceWith.adviceWith("greeting",context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                weaveAddLast().to("mock:result");
            }
        });

        context.start();

        System.out.println("context.getRouteDefinitions() : "+ context.getRouteDefinitions());

        MockEndpoint mockEndpoint = getMockEndpoint("mock:result");
        mockEndpoint.expectedBodiesReceived("Hello sire");
        mockEndpoint.expectedMessageCount(1);

        template.sendBody("direct:start", "Hello sire");

        mockEndpoint.assertIsSatisfied();
    }

    // Working
    @Test
    public void testAdviceWithWeaveFirst() throws Exception {
        AdviceWith.adviceWith("greeting", context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                weaveAddFirst().to("direct:beforeStart");
                weaveAddLast().to("mock:result");
                weaveByToUri("direct:stop").replace().to("log:select?showAll=true&multiline=true");
            }
        });

        context.start();

        System.out.println("context.getRouteDefinitions() : "+ context.getRouteDefinitions());

        System.out.println("\nContext : "+context);
        System.out.println("Template : "+template + "\n");

        /**
         * Route(greeting)[From[direct:start] -> [To[direct:beforeStart], To[log:select?showAll=true&multiline=true], Log[Body of direct:start : ${body}], To[mock:result]]],
         * Route(route1)[From[direct:beforeStart] -> [Log[Body of direct:beforeStart : ${body}]]],
         * Route(direct:first)[From[direct:first] -> [Log[Not in use...]]]]
         */

        MockEndpoint mockEndpoint = getMockEndpoint("mock:result");
        mockEndpoint.expectedBodiesReceived("Hello");
        mockEndpoint.expectedMessageCount(1);

        template.sendBody("direct:start", "Hello");

        mockEndpoint.assertIsSatisfied();
    }

    // Not working.
    @Test
    public void testDirectFirst() throws Exception {
        AdviceWith.adviceWith("direct:first", context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                replaceFromWith("mock:startFirst");
            }
        });

        System.out.println("context.getRouteDefinitions() : "+ context.getRouteDefinitions());

        var mockEndpoint = getMockEndpoint("mock:startFirst");
        mockEndpoint.returnReplyBody(new SimpleBuilder("Hi"));
        mockEndpoint.expectedBodiesReceived("Hi");
        mockEndpoint.expectedMessageCount(1);

        template.sendBody("direct:startFirst", "Hi");

        mockEndpoint.assertIsSatisfied();
    }
}
