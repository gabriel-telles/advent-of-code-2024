package com.gabrieltelles.adventofcode2024.day12;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class Day12Test {
    private static final String RESOURCES_DIR = "src/test/resources/";

    @ParameterizedTest
    @CsvSource(value = {"example1.txt,140", "example2.txt,772", "example3.txt,1930"}, delimiter = ',')
    void shouldCalculateFencingPrice(String filename, long expectedPrice) {
        // Arrange
        char[][] field = Day12.loadChar2DArrayFromPath(RESOURCES_DIR + filename);

        // Act
        long price = Day12.calculateFencingPrice(field);

        // Assert
        assertEquals(expectedPrice, price);
    }
}