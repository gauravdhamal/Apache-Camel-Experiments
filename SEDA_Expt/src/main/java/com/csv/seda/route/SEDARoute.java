package com.csv.seda.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class SEDARoute extends RouteBuilder {

    @Override
    public void configure(){
        from("timer:test?period=5000")
                .to("seda:route1", "seda:route2", "seda:route3");

        from("seda:route1")
                .log("route1");

        from("seda:route2")
                .log("route2");

        from("seda:route3")
                .log("route3");

    }

}
