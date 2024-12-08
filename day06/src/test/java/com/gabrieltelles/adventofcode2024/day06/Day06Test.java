package com.gabrieltelles.adventofcode2024.day06;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day06Test {

    private static final String EXAMPLE_MAP_PATH = "src/test/resources/exampleMap.txt";

    @Test
    void shouldTraverseExampleMap() {
        PuzzleMap puzzleMap = PuzzleMap.from(EXAMPLE_MAP_PATH);
        Assertions.assertNotNull(puzzleMap);

        var guard = Guard.from(puzzleMap.getMap());
        Day06.traverseMap(puzzleMap, guard);
        int visitedPositions = puzzleMap.countVisitedPositions();

        Assertions.assertEquals(41, visitedPositions);
    }

}