package com.csv.readwrite;

import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApplicationUnitTestSupport extends CamelTestSupport {

    @Autowired
    protected Environment environment;

    @BeforeAll
    public void init(){
        var propertiesComponent = context.getPropertiesComponent();
        propertiesComponent.setLocation("classpath:application-test.properties");
    }

    @Test
    public void verifySetup() {
        Assertions.assertNotNull(environment);
        Assertions.assertNotNull(context);
        Assertions.assertNotNull(template);
        Assertions.assertNotNull(consumer);
    }

}
