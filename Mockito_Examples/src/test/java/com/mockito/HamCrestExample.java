package com.mockito;

import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

public class HamCrestExample {

    @Test
    public void testNumbers(){
        List<Integer> num = Arrays.asList(99, 123, 45, 148, 33);

        MatcherAssert.assertThat(num, hasSize(5));
        MatcherAssert.assertThat(num, hasItems(99, 45));
        MatcherAssert.assertThat(num, everyItem(greaterThan(20)));
        MatcherAssert.assertThat(num, everyItem(greaterThanOrEqualTo(33)));
        MatcherAssert.assertThat(num, everyItem(lessThan(150)));

    }

}
