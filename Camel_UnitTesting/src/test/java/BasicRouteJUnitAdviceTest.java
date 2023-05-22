import org.apache.camel.LoggingLevel;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;
import org.mockito.routes.BasicRoute;

import static org.apache.camel.builder.AdviceWith.adviceWith;
import org.apache.camel.builder.AdviceWithRouteBuilder;

public class BasicRouteJUnitAdviceTest extends CamelTestSupport {
    @Override
    public boolean isUseAdviceWith() {
        return true;
    }

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new BasicRoute();
    }

    @Test
    public void testMockEndpoints() throws Exception {
        RouteDefinition routeDefinition = context.getRouteDefinition("greeting");

        adviceWith(routeDefinition, context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                weaveAddFirst().log(LoggingLevel.ERROR, "Body before all (weaveAddFirst()) : ${body}");
                weaveAddLast().to("mock:finishGreeting");
            }
        });

        context.start();

        MockEndpoint mockEndpoint = getMockEndpoint("mock:finishGreeting");
        mockEndpoint.expectedMessageCount(1);

        template.sendBody("direct:greeting", "Team");

        mockEndpoint.assertIsSatisfied();
    }
}
