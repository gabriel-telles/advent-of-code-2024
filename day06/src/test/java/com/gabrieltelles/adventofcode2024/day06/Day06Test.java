package com.gabrieltelles.adventofcode2024.day06;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day06Test {

    private static final String EXAMPLE_MAP_PATH = "src/test/resources/exampleMap.txt";

    @Test
    void shouldTraverseExampleMap() {
        var puzzleMap = PuzzleMap.from(EXAMPLE_MAP_PATH);
        while (puzzleMap.moveGuard()) {}
        int visitedPositions = puzzleMap.countVisitedPositions();

        Assertions.assertEquals(41, visitedPositions);
    }

}