package com.mockito;

import org.assertj.core.api.BDDAssertions;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ArgumentMathcerExamples {

    // Using argument matcher
    @Test
    public void testList_Argument_Mathcers(){
        List<String> mockList = Mockito.mock(ArrayList.class);

        Mockito.when(mockList.get(Mockito.anyInt())).thenReturn("Mockito");

        Assertions.assertEquals("Mockito", mockList.get(0));

        Assertions.assertEquals("Mockito", mockList.get(2));

        Assertions.assertEquals("Mockito", mockList.get(1));
    }

    // Throwing an exception using argument matcher
    @Test
    public void testList_ThrowsException(){
        List<String> list = Mockito.mock(ArrayList.class);

        Mockito.when(list.get(Mockito.anyInt())).thenThrow(new RuntimeException());

        Assertions.assertThrows(RuntimeException.class, () -> {
            list.get(0);
        });
    }

    @Test
    public void testList_UsingBDD(){
        List<String> list = Mockito.mock(ArrayList.class);

        BDDMockito.given(list.get(Mockito.anyInt())).willReturn("Mockito");

        String s1 = list.get(0);

        MatcherAssert.assertThat(s1, Matchers.is("Mockito"));
    }
}
