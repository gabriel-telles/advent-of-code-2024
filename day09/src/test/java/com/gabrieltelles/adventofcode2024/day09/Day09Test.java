package com.gabrieltelles.adventofcode2024.day09;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day09Test {
    
    @ParameterizedTest
    @MethodSource
    void shouldDecompressNotation(String path, List<Integer> expectedDecompressedNotation) {
        // Arrange
        String diskMap = Day09.loadDiskMap(path);

        // Act
        var actualDecompressedNotation = Day09.decompressNotation(diskMap);

        // Assert
        assertEquals(expectedDecompressedNotation, actualDecompressedNotation);
    }

    private static Stream<Arguments> shouldDecompressNotation() {
        return Stream.of(
                Arguments.of("src/test/resources/testInput12345.txt", "0..111....22222".chars().mapToObj(c -> c - '0').toList()),
                Arguments.of("src/test/resources/testInput2333133121414131402.txt", "00...111...2...333.44.5555.6666.777.888899".chars().mapToObj(c -> c - '0').toList())
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldCompact(List<Integer> disk, List<Integer> expectedCompactedDisk) {
        // Arrange & Act
        var compactedDisk = Day09.compact(disk);

        // Assert
        assertEquals(expectedCompactedDisk, compactedDisk);
    }

    private static Stream<Arguments> shouldCompact() {
        return Stream.of(
                Arguments.of("0..111....22222".chars().mapToObj(c -> c - '0').toList(), "022111222".chars().mapToObj(c -> c - '0').toList()),
                Arguments.of("00...111...2...333.44.5555.6666.777.888899".chars().mapToObj(c -> c - '0').toList(), "0099811188827773336446555566".chars().mapToObj(c -> c - '0').toList())
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldCalculateChecksum(List<Integer> compactedDisk, long expectedChecksum) {
        // Arrange & Act
        var checksum = Day09.calculateChecksum(compactedDisk);

        // Assert
        assertEquals(expectedChecksum, checksum);
    }

    private static Stream<Arguments> shouldCalculateChecksum() {
        return Stream.of(
                Arguments.of("022111222".chars().mapToObj(c -> c - '0').toList(), 60L),
                Arguments.of("0099811188827773336446555566".chars().mapToObj(c -> c - '0').toList(), 1928L)
        );
    }


}