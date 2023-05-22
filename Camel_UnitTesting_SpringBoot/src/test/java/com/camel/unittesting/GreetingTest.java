package com.camel.unittesting;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.MockEndpoints;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@CamelSpringBootTest
@MockEndpoints("file:src/main/resources/output")
public class GreetingTest {

    @Autowired
    private ProducerTemplate producerTemplate;

    @EndpointInject("file:src/main/resources/output")
    private MockEndpoint mockEndpoint;

    @Test
    public void whenSendBody_ThenGreetingsReceivedSuccessfully() throws InterruptedException {
        mockEndpoint.expectedBodiesReceived("Hello from CamelUnitTestingSpringBootApplication!");
        producerTemplate.sendBody("direct:start", "Hello from CamelUnitTestingSpringBootApplication!");
        mockEndpoint.assertIsSatisfied();
    }

}
