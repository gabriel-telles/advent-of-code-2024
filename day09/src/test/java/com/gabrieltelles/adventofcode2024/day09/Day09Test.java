package com.gabrieltelles.adventofcode2024.day09;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day09Test {
    
    @ParameterizedTest
    @MethodSource
    void shouldDecompressNotation(String path, String expectedDecompressedNotation) {
        // Arrange
        String diskMap = Day09.loadDiskMap(path);

        // Act
        String actualDecompressedNotation = Day09.decompressNotation(diskMap);

        // Assert
        assertEquals(expectedDecompressedNotation, actualDecompressedNotation);
    }

    private static Stream<Arguments> shouldDecompressNotation() {
        return Stream.of(
                Arguments.of("src/test/resources/testInput12345.txt", "0..111....22222"),
                Arguments.of("src/test/resources/testInput2333133121414131402.txt", "00...111...2...333.44.5555.6666.777.888899")
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldCompact(String disk, String expectedCompactedDisk) {
        // Arrange & Act
        String compactedDisk = Day09.compact(disk);

        // Assert
        assertEquals(expectedCompactedDisk, compactedDisk);
    }

    private static Stream<Arguments> shouldCompact() {
        return Stream.of(
                Arguments.of("0..111....22222", "022111222"),
                Arguments.of("00...111...2...333.44.5555.6666.777.888899", "0099811188827773336446555566")
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldCalculateChecksum(String compactedDisk, long expectedChecksum) {
        // Arrange & Act
        long checksum = Day09.calculateChecksum(compactedDisk);

        // Assert
        assertEquals(expectedChecksum, checksum);
    }

    private static Stream<Arguments> shouldCalculateChecksum() {
        return Stream.of(
                Arguments.of("022111222", 60L),
                Arguments.of("0099811188827773336446555566", 1928L)
        );
    }


}