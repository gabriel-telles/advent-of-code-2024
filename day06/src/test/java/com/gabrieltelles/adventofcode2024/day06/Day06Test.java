package com.gabrieltelles.adventofcode2024.day06;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day06Test {

    private static final String EXAMPLE_MAP_PATH = "src/test/resources/exampleMap.txt";

    @Test
    void shouldTraverseExampleMap() {
        // Arrange
        var puzzleMap = PuzzleMap.from(EXAMPLE_MAP_PATH);

        // Act
        while (puzzleMap.checkMovementState() == PuzzleMap.MovementState.MOVABLE) {
            puzzleMap.moveGuard();
        }
        if (puzzleMap.checkMovementState() == PuzzleMap.MovementState.FINISH) {
            puzzleMap.moveGuard();
        }

        // Assert
        Assertions.assertEquals(41, puzzleMap.countVisitedPositions());
    }

    @Test
    void shouldGetCorrectNumberOfPossibleLoops() {
        // Arrange
        var puzzleMap = PuzzleMap.from(EXAMPLE_MAP_PATH);

        // Act
        int loopCounter = 0;

        int mapHeight = puzzleMap.getHeight();
        int mapWidth = puzzleMap.getWidth();
        for (int row = 0; row < mapHeight; row++) {
            for (int col = 0; col < mapWidth; col++) {
                System.out.println(row + "," + col);
                var puzzleMapCopy = puzzleMap.getCopyWithAdditionalObstacle(new int[]{row, col});
                if (puzzleMapCopy != null) {
                    while (puzzleMapCopy.checkMovementState() == PuzzleMap.MovementState.MOVABLE) {
                        puzzleMapCopy.moveGuard();
                    }
                    if (puzzleMapCopy.checkMovementState() == PuzzleMap.MovementState.LOOPED) {
                        loopCounter++;
                    }
                }
            }
        }

        // Assert
        Assertions.assertEquals(6, loopCounter);
    }

}