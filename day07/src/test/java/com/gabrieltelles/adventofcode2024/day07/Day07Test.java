package com.gabrieltelles.adventofcode2024.day07;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day07Test {

    @Test
    void testEvaluatesTo() {
        // Arrange
        long[] equation = new long[] {292, 11, 6, 16, 20};
        Day07.Operation[] operations = new Day07.Operation[] {Day07.Operation.ADD, Day07.Operation.MULTIPLY, Day07.Operation.ADD};

        // Act
        boolean result = Day07.evaluatesTo(equation, operations);

        // Assert
        assertTrue(result);
    }

    @Test
    void testGetSumOfAcceptableEquations() {
        // Arrange
        List<long[]> equations = Day07.loadEquationsFromPath("src/test/resources/testInput.txt");

        // Act
        long result = Day07.getSumOfAcceptableEquations(equations);

        // Assert
        assertEquals(3749L, result);
    }

}