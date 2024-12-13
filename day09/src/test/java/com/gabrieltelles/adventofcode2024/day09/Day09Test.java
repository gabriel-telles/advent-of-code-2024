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
                Arguments.of("0..111....22222".chars().map(Character::getNumericValue).toArray(),
                        "022111222......".chars().map(Character::getNumericValue).toArray()),
                Arguments.of("00...111...2...333.44.5555.6666.777.888899".chars().map(Character::getNumericValue).toArray(),
                        "0099811188827773336446555566..............".chars().map(Character::getNumericValue).toArray())
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldBlockCompact(int[] disk, int[] expectedBlockCompactedDisk) {
        // Arrange & Act
        var blockCompactedDisk = Day09.blockCompact(disk);

        // Assert
        assertArrayEquals(expectedBlockCompactedDisk, blockCompactedDisk);
    }

    private static Stream<Arguments> shouldBlockCompact() {
        return Stream.of(
                Arguments.of("00...111...2...333.44.5555.6666.777.888899".chars().map(Character::getNumericValue).toArray(),
                        "00992111777.44.333....5555.6666.....8888..".chars().map(Character::getNumericValue).toArray())
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
                Arguments.of("0099811188827773336446555566".chars().map(Character::getNumericValue).toArray(), 1928L),
                Arguments.of("00992111777.44.333....5555.6666.....8888..".chars().map(Character::getNumericValue).toArray(), 2858L)
        );
    }


}