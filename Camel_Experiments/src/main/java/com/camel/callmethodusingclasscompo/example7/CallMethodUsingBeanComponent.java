package com.camel.callmethodusingclasscompo.example7;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

public class CallMethodUsingBeanComponent {

	public static void main(String[] args) throws Exception {

		MyService myService = new MyService();

		SimpleRegistry simpleRegistry = new SimpleRegistry();
		simpleRegistry.put("myService", myService);

		CamelContext camelContext = new DefaultCamelContext(simpleRegistry);

		camelContext.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("direct:start").to("bean:myService?method=doSomething");
			}
		});

		camelContext.start();

		ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
		producerTemplate.sendBody("direct:start", "Hello by bean component.");

	}

}
