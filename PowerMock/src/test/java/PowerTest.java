import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.utility.UtilityPrivate;
import org.mockito.utility.UtilityStatic;
import org.mockito.utility.UtilityFinal;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.junit.Assert;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "org.mockito.utility.*")
public class PowerTest {

    @Test
    public void testStaticMethod_WithPowerMockito(){
        String call = " Hi there, I'm using PowerMock with Mockito ";
        String expectedCall = " Call Expectation for you. ";

        PowerMockito.mockStatic(UtilityStatic.class);
        PowerMockito.when(UtilityStatic.staticMethod(call)).thenReturn(expectedCall);

        String actualCall = UtilityStatic.staticMethod(call);
        Assert.assertEquals(expectedCall, actualCall);
    }

    @Test
    public void testFinalMethod_WithPowerMockito() throws Exception {
        String message = " PowerMock with Mockito and JUnit ";
        String returnMsg = " PowerMock with Mockito and JUnit  FINAL1";

        UtilityFinal utilityFinalMock = PowerMockito.mock(UtilityFinal.class);
        // It is used to make sure that every time we are creating new instance of UtilityClassWith... it will return the same instance we mocked.
        PowerMockito.whenNew(UtilityFinal.class).withNoArguments().thenReturn(utilityFinalMock);

        UtilityFinal utilityFinalNew = new UtilityFinal();
        PowerMockito.verifyNew(UtilityFinal.class).withNoArguments();

        PowerMockito.when(utilityFinalNew.finalMethod(message)).thenReturn(returnMsg);
        String message2 = utilityFinalNew.finalMethod(message);
        Mockito.verify(utilityFinalNew).finalMethod(message);
        Assert.assertEquals(returnMsg, message2);
    }

    @Test
    public void testFinalMethod_AnotherWay(){
        String msg = "Im final";

        UtilityFinal utilityFinalMock = PowerMockito.mock(UtilityFinal.class);
        PowerMockito.when(utilityFinalMock.finalMethod(msg)).thenReturn(msg);

        String msg1 = utilityFinalMock.finalMethod(msg);
        Mockito.verify(utilityFinalMock).finalMethod(msg);
        Assert.assertEquals(msg, msg1);
    }

    @Test
    public void testPrivateMethod() throws Exception {
        String message = "actualMsg";
        String expMsg = "expectedMsg";

        UtilityPrivate utilityPrivateMock = PowerMockito.spy(new UtilityPrivate());
        PowerMockito.doReturn(expMsg).when(utilityPrivateMock, "privateMethod", message);

        String actualMsg = utilityPrivateMock.callPrivateMethod(message);
        Mockito.verify(utilityPrivateMock).callPrivateMethod(message);
        Assert.assertEquals(expMsg , actualMsg);
    }
}