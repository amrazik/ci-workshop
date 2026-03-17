package com.signavio.ci;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MainTest {
    @ParameterizedTest(name = "Testing number: {0}")
    @MethodSource("numbers")
    void testingNumbers(int number) throws InterruptedException {
        Thread.sleep(100);
        assertTrue(number == number);
    }

    @ParameterizedTest(name = "Testing other number: {0}")
    @MethodSource("numbers")
    void testingNumbers2(int number) throws InterruptedException {
        Thread.sleep(100);
        assertTrue(number == number);
    }

    public static Stream<Arguments> numbers() {
        return IntStream.range(0, 1_000).mapToObj(Arguments::of);
    }
}
