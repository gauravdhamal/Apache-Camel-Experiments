package com.mockito;

import com.mockito.service.FinalMethodClass;
import com.mockito.service.PrivateMethodClass;
import com.mockito.service.StaticMethodClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MockitoTestWithoutPowerMock {

    @Test
    public void testStaticMethod() {
        String call = "Hi World";
        String expectedCall = "Hello";

        Mockito.mockStatic(StaticMethodClass.class);
        Mockito.when(StaticMethodClass.staticMethod(call)).thenReturn(expectedCall);

        String actualCall = StaticMethodClass.staticMethod(call);

        Assertions.assertEquals(expectedCall, actualCall);
    }

    @Test
    public void testFinalMethod() {
        String message = "Hi";
        String expectedMsg = "Hello";

        FinalMethodClass finalMethodClassMock = Mockito.mock(FinalMethodClass.class);

        Mockito.when(finalMethodClassMock.finalMethod(message)).thenReturn(expectedMsg);

        String actualMsg = finalMethodClassMock.finalMethod(message);

        Mockito.verify(finalMethodClassMock).finalMethod(message);
        Assertions.assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void testPrivateMethod() {
        String message = "actualMsg";
        String expectedMsg = "expectedMsg";

        PrivateMethodClass privateMethodClassMock = Mockito.spy(new PrivateMethodClass());
//        Mockito.doReturn(expMsg).when(privateMethodClassMock, "privateMethod", message);
        Mockito.when(privateMethodClassMock.callPrivateMethod(message)).thenReturn(expectedMsg);

        String actualMsg = privateMethodClassMock.callPrivateMethod(message);

        Mockito.verify(privateMethodClassMock).callPrivateMethod(message);
        Assertions.assertEquals(expectedMsg, actualMsg);
    }

}
