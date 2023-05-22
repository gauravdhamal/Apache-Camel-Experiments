import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;
import org.mockito.routes.BasicRoute;

public class BasicRouteTest extends CamelTestSupport {
    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new BasicRoute();
    }

    @Test
    public void testValidMocks(){
        System.out.println("Sending 1st input1");
        template.sendBody("direct:greeting", "Team");

        System.out.println("Sending 1st input2");
        template.sendBody("direct:greeting", "Me");
    }
}
