package com.camel.routes.example1;

import org.apache.camel.builder.RouteBuilder;

public class FileTransferRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("file:inputContent?noop=true")
		.to("file:outputContent");
	}

}
