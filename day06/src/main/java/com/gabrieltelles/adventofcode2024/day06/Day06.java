package com.gabrieltelles.adventofcode2024.day06;

public class Day06 {
    private static final String MAP_PATH = "day06/src/main/resources/map.txt";

    public static void main(String[] args) {
        // Part One
        var puzzleMap = PuzzleMap.from(MAP_PATH);
        while (puzzleMap.checkMovementState() == PuzzleMap.MovementState.MOVABLE) {
            puzzleMap.moveGuard();
        }
        if (puzzleMap.checkMovementState() == PuzzleMap.MovementState.FINISH) {
            puzzleMap.moveGuard();
            int visitedPositions = puzzleMap.countVisitedPositions();
            System.out.println(visitedPositions);
        }

        // Part Two
        var originalPuzzleMap = PuzzleMap.from(MAP_PATH);
        int loopCounter = 0;
        int mapHeight = originalPuzzleMap.getHeight();
        int mapWidth = originalPuzzleMap.getWidth();
        for (int row = 0; row < mapHeight; row++) {
            for (int col = 0; col < mapWidth; col++) {
                System.out.println(row + "," + col + "," + loopCounter);
                var puzzleMapCopy = originalPuzzleMap.getCopyWithAdditionalObstacle(new int[]{row, col});
                if (puzzleMapCopy != null) {
                    if (row == 5 && col == 5) {
                        System.out.println("hey");
                    }
                    while (puzzleMapCopy.checkMovementState() == PuzzleMap.MovementState.MOVABLE) {
                        puzzleMapCopy.moveGuard();
                    }
                    if (puzzleMapCopy.checkMovementState() == PuzzleMap.MovementState.LOOPED) {
                        loopCounter++;
                    }
                }
            }
        }
        System.out.println(loopCounter);
    }
}