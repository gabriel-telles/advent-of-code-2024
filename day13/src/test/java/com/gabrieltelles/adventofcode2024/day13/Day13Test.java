package com.gabrieltelles.adventofcode2024.day13;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day13Test {

    @Test
    void shouldCalculateTokensNeeded() {
        // Arrange
        List<Machine> machines = Day13.loadMachinesFromPath("src/test/resources/example.txt");

        // Act
        long tokensNeeded = Day13.calculateTokensNeeded(machines);

        // Assert
        assertEquals(480L, tokensNeeded);
    }

}