package com.gabrieltelles.adventofcode2024.day04;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

class Day04Test {

    static final Pattern PATTERN = Pattern.compile("XMAS|SAMX");

    @ParameterizedTest
    @MethodSource
    void shouldCountHorizontalForSingleLine(String line, int expectedHorizontalCount) {
        int actualHorizontalCount = Day04.countHorizontal(List.of(line), PATTERN);

        Assertions.assertEquals(expectedHorizontalCount, actualHorizontalCount);
    }

    private static Stream<Arguments> shouldCountHorizontalForSingleLine() {
        return Stream.of(
                Arguments.of("", 0),
                Arguments.of("XMA", 0),
                Arguments.of("XMAS", 1),
                Arguments.of("SAMX", 1),
                Arguments.of("XMASMMMXMAS", 2),
                Arguments.of("SAMXMMMSAMX", 2),
                Arguments.of("XMASSAMX", 2),
                Arguments.of("XMASAMX", 2),
                Arguments.of("SAMXMASAMXMASAMX", 5),
                Arguments.of("XMASAMXMASAMXMASAMX", 6)
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldCountHorizontalForMultipleLineS(List<String> lines, int expectedHorizontalCount) {
        int actualHorizontalCount = Day04.countHorizontal(lines, PATTERN);

        Assertions.assertEquals(expectedHorizontalCount, actualHorizontalCount);
    }

    private static Stream<Arguments> shouldCountHorizontalForMultipleLineS() {
        return Stream.of(
                Arguments.of(List.of(), 0),
                Arguments.of(List.of(""), 0),
                Arguments.of(List.of("XMAS"), 1),
                Arguments.of(List.of("XMASSAMX"), 2),
                Arguments.of(List.of("",""), 0),
                Arguments.of(List.of("XMAS","SAMX"), 2),
                Arguments.of(List.of("XMASAMX", "XMASAMX"), 4),
                Arguments.of(Collections.nCopies(5, "XMASAMXMASAMXMASAMX"), 30)
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldCountVertical(List<String> lines, int expectedVerticalCount) {
        int actualVerticalCount = Day04.countVertical(lines, PATTERN);

        Assertions.assertEquals(expectedVerticalCount, actualVerticalCount);
    }

    private static Stream<Arguments> shouldCountVertical() {
        return Stream.of(
                Arguments.of(List.of(), 0),
                Arguments.of(List.of(""), 0),
                Arguments.of(List.of("XMAS"), 0),
                Arguments.of(List.of("","","",""), 0),
                Arguments.of(List.of("X","X","X","X"), 0),
                Arguments.of(List.of("X","M","A","S"), 1),
                Arguments.of(List.of("S","A","M","X"), 1),
                Arguments.of(List.of("X","M","A","S","A","M","X"), 2),
                Arguments.of(List.of("XX","MM","AA","SS"), 2),
                Arguments.of(List.of("XX.SS","MM.AA","AA.MM","SS.XX"), 4)
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldCountDiagonal(List<String> lines, int expectedCount) {
        int actualCount = Day04.countDiagonal(lines, PATTERN);

        Assertions.assertEquals(expectedCount, actualCount);
    }

    private static Stream<Arguments> shouldCountDiagonal() {
        return Stream.of(
                Arguments.of(List.of(), 0),
                Arguments.of(List.of(""), 0),
                Arguments.of(List.of("XMAS"), 0),
                Arguments.of(List.of("","","",""), 0),
                Arguments.of(List.of("X","M","A","S"), 0),
                Arguments.of(List.of("X...",".M..","..A.","...S"), 1),
                Arguments.of(List.of("S...",".A..","..M.","...X"), 1),
                Arguments.of(List.of("X..X",".MM.",".AA.","S..S"), 2),
                Arguments.of(List.of("XXXXXX","MMMMMM","AAAAAA","SSSSSS"), 6)
        );
    }
}