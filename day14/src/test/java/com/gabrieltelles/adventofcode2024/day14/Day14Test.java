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
        for (int i = 0; i < 100; i++) {
            for (var robot : robots) {
                robot.move();
            }
        }
        int[][] bathroom = new int[WIDTH][HEIGHT];
        for (var robot : robots) {
            bathroom[robot.posX()][robot.posY()]++;
        }

        int midRow = WIDTH / 2;
        int midCol = HEIGHT / 2;

        // Quadrant sums
        long topLeftSum = 0, topRightSum = 0, bottomLeftSum = 0, bottomRightSum = 0;

        // Calculate sums for each quadrant
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (i < midRow && j < midCol) {
                    topLeftSum += bathroom[i][j];
                } else if (i < midRow && j > midCol) {
                    topRightSum += bathroom[i][j];
                } else if (i > midRow && j < midCol) {
                    bottomLeftSum += bathroom[i][j];
                } else if (i > midRow && j > midCol) {
                    bottomRightSum += bathroom[i][j];
                }
            }
        }

        long safetyFactor = topLeftSum * topRightSum * bottomLeftSum * bottomRightSum;

        assertEquals(12, safetyFactor);
    }
}