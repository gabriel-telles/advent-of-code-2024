package com.gabrieltelles.adventofcode2024.day06;

public class Day06 {
    private static final String MAP_PATH = "day06/src/main/resources/map.txt";

    public static void main(String[] args) {
        var puzzleMap = PuzzleMap.from(MAP_PATH);
        while (puzzleMap.moveGuard()) {}
        int visitedPositions = puzzleMap.countVisitedPositions();
        System.out.println(visitedPositions);
    }
}