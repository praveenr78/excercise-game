package com.excercise.game;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FizzBuzzGeneratorTest {

    @Autowired
    private FizzBuzzGenerator fizzBuzzGenerator;

    @Test
    public void testDivisibleBy3_Success() {
       List<String> result = fizzBuzzGenerator.generate(3);
        assertThat(result).isEqualTo(Arrays.asList("1", "2", "Fizz"));    }
    @Test
    public void testWhenNoMax_IsEmpty() {
        List<String> result = fizzBuzzGenerator.generate(0);
        assertThat(result).isEmpty();    }
    @Test
    public void testDivisibleAndContains3_Success() {
        List<String> result = fizzBuzzGenerator.generate(13);
        assertThat(result).isEqualTo(Arrays.asList("1",
                "2",
                "Fizz",
                "4",
                "Buzz",
                "Fizz",
                "7",
                "8",
                "Fizz",
                "Buzz",
                "11",
                "Fizz",
                "Fizz"));
    }

    @Test
    public void testDivisibleBy5_Success() {
        List<String> result = fizzBuzzGenerator.generate(5);
        assertThat(result).isEqualTo(Arrays.asList("1", "2", "Fizz", "4", "Buzz"));
    }

    @Test
    public void testDivisibleAndContains5_Success() {
        List<String> result = fizzBuzzGenerator.generate(15);
        assertThat(result).isEqualTo(Arrays.asList("1",
                "2",
                "Fizz",
                "4",
                "Buzz",
                "Fizz",
                "7",
                "8",
                "Fizz",
                "Buzz",
                "11",
                "Fizz",
                "Fizz",
                "14",
                "FizzBuzz"));
    }

    @Test
    public void testDivisibleAndContains3or5_Success() {
        List<String> result = fizzBuzzGenerator.generate(15);
        assertThat(result).isEqualTo(Arrays.asList("1",
                "2",
                "Fizz",
                "4",
                "Buzz",
                "Fizz",
                "7",
                "8",
                "Fizz",
                "Buzz",
                "11",
                "Fizz",
                "Fizz",
                "14",
                "FizzBuzz"));
    }


}
