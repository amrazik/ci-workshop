package com.signavio.ci;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {
    @ParameterizedTest
    @MethodSource("numbers")
    void tests(int number) throws InterruptedException {
        Thread.sleep(100);
        assertTrue(number == number);
    }

    public static Stream<Arguments> numbers() {
        return IntStream.range(0, 1_000)
            .mapToObj(Arguments::of);
    }
}
