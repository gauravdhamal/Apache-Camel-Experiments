import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.utility.Utility;
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

        PowerMockito.mockStatic(Utility.class);

        PowerMockito.when(Utility.staticMethod(call)).thenReturn(expectedCall);

        String actualCall = Utility.staticMethod(call);

        Assert.assertEquals(expectedCall, actualCall);
    }


}