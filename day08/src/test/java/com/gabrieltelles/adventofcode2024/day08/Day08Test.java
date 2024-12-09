package com.gabrieltelles.adventofcode2024.day08;

import com.google.common.collect.SetMultimap;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day08Test {

    private static final String TEST_INPUT_PATH = "src/test/resources/testInput.txt";

    @Test
    void shouldLoadFromFile() {
        char[][] rawAntennas = Day08.loadChar2DArrayFromPath(TEST_INPUT_PATH);
        for (var row : rawAntennas) {
            System.out.println(row);
        }
    }

    @Test
    void shouldCreateSetMultimapCorrectly() {
        char[][] rawAntennas = Day08.loadChar2DArrayFromPath(TEST_INPUT_PATH);

        var antennas = Day08.getAntennas(rawAntennas);

        assertEquals(Set.of(new Point(1, 8), new Point(2,5), new Point(3, 7), new Point(4,4)), antennas.get('0'));
        assertEquals(Set.of(new Point(8, 8), new Point(9,9), new Point(5, 6)), antennas.get('A'));
    }

    @Test
    void shouldGetAntiNodes() {
        Point p1 = new Point(3, 4);
        Point p2 = new Point(5, 5);

        assertEquals(new Point(7, 6), Day08.getAntiNode(p1, p2));
        assertEquals(new Point(1, 3), Day08.getAntiNode(p2, p1));
    }

    @Test
    void shouldGetCorrectNumberOfAntiNodesPartOne() {
        char[][] rawAntennas = Day08.loadChar2DArrayFromPath(TEST_INPUT_PATH);

        SetMultimap<Character, Point> antennas = Day08.getAntennas(rawAntennas);
        char[][] antiNodes = Day08.getEmptyBoard(rawAntennas.length);

        Day08.populatePartOne(antennas, antiNodes);

        assertEquals(14, Day08.countOccurrences(antiNodes));
    }

    @Test
    void shouldGetCorrectNumberOfAntiNodesPartTwo() {
        char[][] rawAntennas = Day08.loadChar2DArrayFromPath(TEST_INPUT_PATH);

        SetMultimap<Character, Point> antennas = Day08.getAntennas(rawAntennas);
        char[][] antiNodes = Day08.getEmptyBoard(rawAntennas.length);

        Day08.populatePartTwo(antennas, antiNodes);

        assertEquals(34, Day08.countOccurrences(antiNodes));
    }
}