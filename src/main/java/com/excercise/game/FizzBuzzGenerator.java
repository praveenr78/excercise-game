package com.excercise.game;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <p> Generate fizz buzz game output</p>
 */
@AllArgsConstructor
@Component
@Slf4j
public class FizzBuzzGenerator {

    private static final int MIN = 1;
    private static final int DEFAULT_MAX = 100;

    private final Predicate<Integer> divisibleBy3 = val -> val % 3 == 0;
    private final Predicate<Integer> contains3 = val -> String.valueOf(val).contains("3");
    private final Predicate<Integer> divisibleBy5 = val -> val % 5 == 0;
    private final Predicate<Integer> contains5 = val -> String.valueOf(val).contains("5");

    /**
     * @param maxValue the max value for fizz buzz game.
     * @return List of fizz buzz game values.
     */
    public List<String> generate(Integer maxValue) {
        return IntStream.rangeClosed(MIN, Optional.ofNullable(maxValue).orElse(DEFAULT_MAX))
                .mapToObj(num -> fizzOrBuzz(num)).collect(Collectors.toList());
    }

    /**
     * <p>For each value evaluate fizz buzz</p>
     *
     * @param  val input value
     * @return
     */
    private String fizzOrBuzz(Integer val) {

        if (divisibleBy3.or(contains3).and(divisibleBy5.or(contains5)).test(val)) {
            return "FizzBuzz";
        } else if (divisibleBy3.or(contains3).test(val)) {
            return "Fizz";
        } else if (divisibleBy5.or(contains5).test(val)) {
            return "Buzz";
        }
        return String.valueOf(val);
    }

}
