package com.gabrieltelles.adventofcode2024.day09;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day09Test {
    
    @ParameterizedTest
    @MethodSource
    void shouldGetDiskFromDiskMap(String path, int[] expectedDecompressedNotation) {
        // Arrange
        var diskMap = Day09.loadDiskMap(path);

        // Act
        var actualDecompressedNotation = Day09.getDiskFrom(diskMap);

        // Assert
        assertArrayEquals(expectedDecompressedNotation, actualDecompressedNotation);
    }

    private static Stream<Arguments> shouldGetDiskFromDiskMap() {
        return Stream.of(
                Arguments.of("src/test/resources/testInput12345.txt",
                        "0..111....22222".chars().map(Character::getNumericValue).toArray()),
                Arguments.of("src/test/resources/testInput2333133121414131402.txt",
                        "00...111...2...333.44.5555.6666.777.888899".chars().map(Character::getNumericValue).toArray())
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldCompact(int[] disk, int[] expectedCompactedDisk) {
        // Arrange & Act
        var compactedDisk = Day09.compact(disk);

        // Assert
        assertArrayEquals(expectedCompactedDisk, compactedDisk);
    }

    private static Stream<Arguments> shouldCompact() {
        return Stream.of(
                Arguments.of(new int[] {0, -1, -1, 1, 1, 1, -1, -1, -1, -1, 2, 2, 2, 2, 2},
                        new int[] {0, 2, 2, 1, 1, 1, 2, 2, 2}),
                Arguments.of(new int[] {0, 0, -1, -1, -1, 1, 1, 1, -1, -1, -1, 2, -1, -1, -1, 3, 3, 3, -1, 4, 4, -1, 5, 5, 5, 5, -1, 6, 6, 6, 6, -1, 7, 7, 7, -1, 8, 8, 8, 8, 9, 9},
                        new int[] {0, 0, 9, 9, 8, 1, 1, 1, 8, 8, 8, 2, 7, 7, 7, 3, 3, 3, 6, 4, 4, 6, 5, 5, 5, 5, 6, 6})
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldCalculateChecksum(int[] compactedDisk, long expectedChecksum) {
        // Arrange & Act
        var checksum = Day09.calculateChecksum(compactedDisk);
        
        // Assert
        assertEquals(expectedChecksum, checksum);
    }

    private static Stream<Arguments> shouldCalculateChecksum() {
        return Stream.of(
                Arguments.of("022111222".chars().map(Character::getNumericValue).toArray(), 60L),
                Arguments.of("0099811188827773336446555566".chars().map(Character::getNumericValue).toArray(), 1928L)
        );
    }


}