import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

public class BasicRouteJUnitMockTest extends CamelTestSupport {
    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:greeting")
                        .to("mock:greetingResult");
            }
        };
    }

    @Test
    public void testValidMocks() throws InterruptedException {
        MockEndpoint mockEndpoint = getMockEndpoint("mock:greetingResult");
        mockEndpoint.expectedMessageCount(2);

//        System.out.println("Sending 1st input1");
        template.sendBody("direct:greeting", "Team");

//        System.out.println("Sending 1st input2");
        template.sendBody("direct:greeting", "Me");

        mockEndpoint.assertIsSatisfied();
    }

}
