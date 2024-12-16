package com.gabrieltelles.adventofcode2024.day11;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day11Test {

    private static final String TEST_INPUT_PATH = "src/test/resources/testInput.txt";

    @Test
    void shouldApplyOneBlink() {
        // Arrange
        List<Long> stones = List.of(0L, 1L, 10L, 99L, 999L);
        List<Long> expectedResult = List.of(1L, 2024L, 1L, 0L, 9L, 9L, 2021976L);

        // Act
        stones = Day11.applyBlinks(stones, 1);

        // Assert
        assertEquals(7, stones.size());
        assertTrue(stones.containsAll(expectedResult));
        assertTrue(expectedResult.containsAll(stones));
    }

    @ParameterizedTest
    @CsvSource({"0,2", "1,3", "2,4", "3,5", "4,9", "5,13", "6,22", "25,55312"})
    void shouldApply25BlinksOnExample(int numberOfBlinks, int expectedNumberOfStones) {
        // Arrange
        List<Long> stones = Day11.loadStonesFromPath(TEST_INPUT_PATH);

        // Act
        stones = Day11.applyBlinks(stones, numberOfBlinks);

        // Assert
        assertEquals(expectedNumberOfStones, stones.size());
    }
}