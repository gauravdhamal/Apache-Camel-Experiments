package com.camel.producerconsumerprocessorexchange.example5;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class ProducerAndConsumerAndExchange {

	public static void main(String[] args) throws Exception {

		CamelContext camelContext = new DefaultCamelContext();

		camelContext.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("direct:start").process(new Processor() {
					public void process(Exchange exchange) throws Exception {
						String message = exchange.getIn().getBody(String.class);
						message += " -By GD.";
						exchange.getIn().setBody(message);
					}
				}).to("seda:end");
			}
		});

		camelContext.start();

		ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
		producerTemplate.sendBody("direct:start", "Hello Everyone.");

		ConsumerTemplate consumerTemplate = camelContext.createConsumerTemplate();
		String message = consumerTemplate.receiveBody("seda:end", String.class);
		System.out.println("Message : " + message);

	}

}
