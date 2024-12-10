package com.gabrieltelles.adventofcode2024.day09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day09 {

    private final static String INPUT_PATH = "day09/src/main/resources/input.txt";

    public static void main(String[] args) {
        String diskMap = loadDiskMap(INPUT_PATH);
    }

    static String decompressNotation(String diskMap) {
        return "";
    }

    static String compact(String disk) {
        return "";
    }

    static long calculateChecksum(String compactedDisk) {
        return 0L;
    }

    static String loadDiskMap(String path) {
        try (var lines = Files.lines(Path.of(path))) {
            return lines.toString();
        } catch (IOException e) {
            throw new RuntimeException("Error reading the input file: " + e.getMessage());
        }
    }
}