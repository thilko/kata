package com.thilko.kata;

import static org.hamcrest.CoreMatchers.is;
import org.junit.Assert;
import static org.junit.Assert.assertThat;
import org.junit.Ignore;
import org.junit.Test;

public class FizzBuzzTest {

    @Test
    public void fizzBuzz_returns_1_for_1() {
        String result = fizzBuzz(1);

        assertThat(result, is("1"));
    }

    @Test
    public void fizzBuzz_returns_fizz_for_3() {
        String result = fizzBuzz(3);

        assertThat(result, is("fizz"));
    }

    @Test
    public void fizzBuzz_returns_fizz_for_6() {
        String result = fizzBuzz(6);

        assertThat(result, is("fizz"));
    }

    @Test
    public void fizzBuzz_returns_buzz_for_5() {
        String result = fizzBuzz(5);

        assertThat(result, is("buzz"));
    }

    @Test
    public void fizzBuzz_returns_fizzbuzz_for_15() {
        String result = fizzBuzz(15);

        assertThat(result, is("fizzbuzz"));
    }

    @Test
    @Ignore
    public void fizzBuzz_returns_1_2_fizz_4_buzz_for_1to5() {
        String result = AbstractFizzBuzzFactoryServiceImpl.create().doFizz(1, 5);

        assertThat(result, is("1, 2, fizz, 4, buzz"));
    }

    private String fizzBuzz(int number) {
        if (isFizzBuzz(number)) {
            return "fizzbuzz";
        }

        if (isBuzz(number)) {
            return "buzz";
        }

        if (isFizz(number)) {
            return "fizz";
        }

        return String.valueOf(number);
    }

    private boolean isFizzBuzz(int number) {
        return isFizz(number) && isBuzz(number);
    }

    private boolean isFizz(int number) {
        return number % 3 == 0;
    }

    private boolean isBuzz(int number) {
        return number % 5 == 0;
    }


    private static class AbstractFizzBuzzFactoryServiceImpl {
        public static AbstractFizzBuzzFactoryServiceImpl create() {
            return null;
        }

        public String doFizz(int i, int i1) {
            return null;
        }
    }
}
