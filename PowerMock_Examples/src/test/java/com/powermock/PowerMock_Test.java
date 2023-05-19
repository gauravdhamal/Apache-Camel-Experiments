package com.powermock;

import com.powermock.entity.Utility;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "com.powermock.entity.*")
public class PowerMock_Test {

    @Test
    public void testStaticMethod_WithPowerMockito(){
        String call = " Hi there, I'm using PowerMock with Mockito ";
        String expectedCall = " Call Expectation for you. ";

        PowerMockito.mockStatic(Utility.class);

        PowerMockito.when(Utility.staticMethod(call)).thenReturn(expectedCall);

        String actualCall = Utility.staticMethod(call);

        Assertions.assertEquals(expectedCall, actualCall);
    }

}
