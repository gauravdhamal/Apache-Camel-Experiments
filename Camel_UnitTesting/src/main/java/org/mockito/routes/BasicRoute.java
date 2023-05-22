package org.mockito.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class BasicRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:greeting").id("greeting")
                .log(LoggingLevel.ERROR ,"Hello ${body}")
                .choice()
                .when().simple("${body} contains 'Team'")
                    .log(LoggingLevel.ERROR, "I like working with teams.")
                .otherwise()
                    .log(LoggingLevel.ERROR, "Solo fighter.")
                .end()
                .to("direct:finishGreeting");

        from("direct:finishGreeting")
                .log(LoggingLevel.ERROR, "Bye ${body}");
    }
}
