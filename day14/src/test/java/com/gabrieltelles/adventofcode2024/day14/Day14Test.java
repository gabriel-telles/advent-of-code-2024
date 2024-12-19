package com.gabrieltelles.adventofcode2024.day14;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day14Test {
    private static final int WIDTH = 11;
    private static final int HEIGHT = 7;

    @Test
    void shouldCalculateSafetyFactorOnExample() {
        List<Robot> robots = Day14.loadRobotsFromPath("src/test/resources/example.txt", WIDTH, HEIGHT);

        Day14.moveRobots(robots, 100);

        int[][] bathroom = Day14.computeBathroomGrid(robots, WIDTH, HEIGHT);

        long safetyFactor = Day14.calculateSafetyFactor(bathroom, WIDTH, HEIGHT);

        assertEquals(12, safetyFactor);
    }
}