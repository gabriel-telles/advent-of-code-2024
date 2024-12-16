package com.gabrieltelles.adventofcode2024.day10;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

class Day10Test {

    private static final String RESOURCES_DIR = "src/test/resources/";

    @ParameterizedTest
    @MethodSource
    void shouldGetTrailheads(String fileName, Set<Point> expected) {
        // Arrange
        int[][] topographicMap = Day10.loadMapFromPath(RESOURCES_DIR + fileName);

        // Act
        Set<Point> trailheads = Day10.getTrailheads(topographicMap);

        // Assert
        Assertions.assertEquals(expected.size(),trailheads.size());
        Assertions.assertTrue(expected.containsAll(trailheads));
        Assertions.assertTrue(trailheads.containsAll(expected));
    }

    private static Stream<Arguments> shouldGetTrailheads() {
        return Stream.of(
                Arguments.of("basicExample.txt", Set.of(new Point(0,0))),
                Arguments.of("oneTrailheadOf2.txt", Set.of(new Point(0, 3))),
                Arguments.of("oneTrailheadOf4.txt", Set.of(new Point(0,3))),
                Arguments.of("twoTrailheadsOf1And2.txt", Set.of(new Point(0,1), new Point(6, 5))),
                Arguments.of("largeExample.txt", Set.of(
                        new Point(0,2),
                        new Point(0,4),
                        new Point(2,4),
                        new Point(4,6),
                        new Point(5,2),
                        new Point(5,5),
                        new Point(6,0),
                        new Point(6,6),
                        new Point(7,1)))
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldGetScore(String fileName, Point trailhead, int expectedScore) {
        // Arrange
        int[][] topographicMap = Day10.loadMapFromPath(RESOURCES_DIR + fileName);

        // Act
        Set<Point> reachableSummits = new HashSet<>();
        int score = Day10.score(topographicMap, trailhead, reachableSummits);

        // Assert
        Assertions.assertEquals(expectedScore, score);
    }

    private static Stream<Arguments> shouldGetScore() {
        return Stream.of(
                Arguments.of("basicExample.txt", new Point(0,0), 1),
                Arguments.of("oneTrailheadOf2.txt", new Point(0, 3), 2),
                Arguments.of("oneTrailheadOf4.txt", new Point(0,3), 4),
                Arguments.of("twoTrailheadsOf1And2.txt", new Point(0,1), 1),
                Arguments.of("twoTrailheadsOf1And2.txt", new Point(6, 5), 2),
                Arguments.of("largeExample.txt",new Point(0,2), 5),
                Arguments.of("largeExample.txt",new Point(0,4), 6),
                Arguments.of("largeExample.txt",new Point(2,4), 5),
                Arguments.of("largeExample.txt",new Point(4,6), 3),
                Arguments.of("largeExample.txt",new Point(5,2), 1),
                Arguments.of("largeExample.txt",new Point(5,5), 3),
                Arguments.of("largeExample.txt",new Point(6,0), 5),
                Arguments.of("largeExample.txt",new Point(6,6), 3),
                Arguments.of("largeExample.txt",new Point(7,1), 5)
        );
    }@ParameterizedTest
    @MethodSource
    void shouldGetRating(String fileName, Point trailhead, int expectedScore) {
        // Arrange
        int[][] topographicMap = Day10.loadMapFromPath(RESOURCES_DIR + fileName);

        // Act
        int score = Day10.rating(topographicMap, trailhead);

        // Assert
        Assertions.assertEquals(expectedScore, score);
    }

    private static Stream<Arguments> shouldGetRating() {
        return Stream.of(
                Arguments.of("largeExample.txt",new Point(0,2), 20),
                Arguments.of("largeExample.txt",new Point(0,4), 24),
                Arguments.of("largeExample.txt",new Point(2,4), 10),
                Arguments.of("largeExample.txt",new Point(4,6), 4),
                Arguments.of("largeExample.txt",new Point(5,2), 1),
                Arguments.of("largeExample.txt",new Point(5,5), 4),
                Arguments.of("largeExample.txt",new Point(6,0), 5),
                Arguments.of("largeExample.txt",new Point(6,6), 8),
                Arguments.of("largeExample.txt",new Point(7,1), 5)
        );
    }

}