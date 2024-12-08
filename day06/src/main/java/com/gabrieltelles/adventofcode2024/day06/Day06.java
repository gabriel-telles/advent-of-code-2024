package com.gabrieltelles.adventofcode2024.day06;

public class Day06 {
    private static final String MAP_PATH = "day06/src/main/resources/map.txt";

    public static void main(String[] args) {
        var puzzleMap = PuzzleMap.from(MAP_PATH);
        if (puzzleMap == null) return;
        var guard = Guard.from(puzzleMap.getMap());
        traverseMap(puzzleMap, guard);
        int visitedPositions = puzzleMap.countVisitedPositions();
        System.out.println(visitedPositions);
    }

    static void traverseMap(PuzzleMap puzzleMap, Guard guard) {
        while (puzzleMap.isInBounds(guard.getNextPosition())) {
            if (puzzleMap.charAt(guard.getNextPosition()) == PuzzleMap.OBSTACLE) {
                guard.changeOrientation();
            } else {
                puzzleMap.walkedOn(guard.getPosition());
                guard.moveForward();
            }
            puzzleMap.updateGuard(guard);
        }
        puzzleMap.walkedOn(guard.getPosition());
    }
}