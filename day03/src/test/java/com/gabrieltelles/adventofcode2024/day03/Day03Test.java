package com.gabrieltelles.adventofcode2024.day03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.gabrieltelles.adventofcode2024.day03.Day03.MUL_PATTERN;

class Day03Test {

    static int numberOfMatches(String input) {
        var matcher = MUL_PATTERN.matcher(input);
        int matches = 0;
        while (matcher.find()) {
            matches++;
        }
        return matches;
    }

    @ParameterizedTest
    @MethodSource
    void shouldRecognizeSinglePattern(String input, boolean isPatternPresent) {
        int actualMatches = numberOfMatches(input);

        Assertions.assertEquals(isPatternPresent, actualMatches > 0);
    }

    private static Stream<Arguments> shouldRecognizeSinglePattern() {
        return Stream.of(
                Arguments.of("", false),
                Arguments.of("unrelated", false),
                Arguments.of("mul()", false),
                Arguments.of("mul(1,1)", true),
                Arguments.of("mul(1,12)", true),
                Arguments.of("mul(1,123)", true),
                Arguments.of("mul(12,1)", true),
                Arguments.of("mul(12,12)", true),
                Arguments.of("mul(12,123)", true),
                Arguments.of("mul(123,1)", true),
                Arguments.of("mul(123,12)", true),
                Arguments.of("mul(123,123)", true),
                Arguments.of("mul(1234,1)", false),
                Arguments.of("mul(1,1234)", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldRecognizeMultiplePatterns(String input, int numberOfMatches) {
        int actualMatches = numberOfMatches(input);

        Assertions.assertEquals(numberOfMatches, actualMatches);
    }

    private static Stream<Arguments> shouldRecognizeMultiplePatterns() {
        return Stream.of(
                Arguments.of("", 0),
                Arguments.of("unrelated", 0),
                Arguments.of("mul()", 0),
                Arguments.of("mul(1,1)", 1),
                Arguments.of("mul(123,123)", 1),
                Arguments.of("mul(1,1)mul(1234,1)", 1),
                Arguments.of("mul(1,1)mul(1,1)", 2),
                Arguments.of("mul(1,1) mul(1,1)", 2),
                Arguments.of("AAAmul(1,1)AAAmul(1,1)AAA", 2),
                Arguments.of("AAAmul(1,1)AAAmul(1,12)AAAmul(1,123)", 3)
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldExecuteMul(String input, int expectedMultiplication) {
        int actualMultiplication = Day03.executeMultiplication(input);

        Assertions.assertEquals(expectedMultiplication, actualMultiplication);
    }

    private static Stream<Arguments> shouldExecuteMul() {
        return Stream.of(
                Arguments.of("mul(0,1)", 0),
                Arguments.of("mul(1,1)", 1),
                Arguments.of("mul(2,3)", 6),
                Arguments.of("mul(100,3)", 300),
                Arguments.of("mul(20,20)", 400),
                Arguments.of("mul(100,100)", 10000)
        );
    }

}