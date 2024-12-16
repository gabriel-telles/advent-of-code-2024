package com.gabrieltelles.adventofcode2024.day11;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day11Test {

    private static final String TEST_INPUT_PATH = "src/test/resources/testInput.txt";


    @ParameterizedTest
    @CsvSource({"0,2", "1,3", "2,4", "3,5", "4,9", "5,13", "6,22", "25,55312"})
    void shouldApplyBlinksOnExample(int numberOfBlinks, int expectedNumberOfStones) {
        // Arrange
        var stones = Day11.loadStonesFromPath(TEST_INPUT_PATH);

        // Act
        stones = Day11.applyBlinks(stones, numberOfBlinks);

        // Assert
        assertEquals(expectedNumberOfStones, Day11.totalStones(stones));
    }
}