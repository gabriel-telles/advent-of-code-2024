package com.gabrieltelles.adventofcode2024.day09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day09 {

    private final static String INPUT_PATH = "day09/src/main/resources/input.txt";

    public static void main(String[] args) {
        String diskMap = loadDiskMap(INPUT_PATH);
    }

    static List<Integer> decompressNotation(String diskMap) {
        return null;
    }

    static List<Integer> compact(List<Integer> disk) {
        return null;
    }

    static long calculateChecksum(List<Integer> compactedDisk) {
        long checksum = 0;
        for (int i = 0; i < compactedDisk.size(); i ++) {
            checksum += (long) i * compactedDisk.get(i);
        }
        return checksum;
    }

    static String loadDiskMap(String path) {
        try (var lines = Files.lines(Path.of(path))) {
            return lines.toString();
        } catch (IOException e) {
            throw new RuntimeException("Error reading the input file: " + e.getMessage());
        }
    }
}