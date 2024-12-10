package com.gabrieltelles.adventofcode2024.day09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Day09 {

    private final static String INPUT_PATH = "day09/src/main/resources/input.txt";

    public static void main(String[] args) {
        String diskMap = loadDiskMap(INPUT_PATH);
    }

    static List<Integer> decompressNotation(String diskMap) {
        return null;
    }

    static int[] compact(int[] disk) {
        int[] result = Arrays.copyOf(disk, disk.length);
        int left = 0;
        int right = disk.length - 1;
        while (left < right) {
            while (result[left] != -1) {left++;}
            while (result[right] == -1) {right--;}
            if (left < right) {
                result[left] = result[right];
                result[right] = -1;
            }
        }
        int realLength = disk.length;
        for (int i = 0; i < result.length; i++) {
            if (result[i] == -1) {
                realLength = i;
                break;
            }
        }
        return Arrays.copyOf(result, realLength);
    }

    static long calculateChecksum(int[] compactedDisk) {
        long checksum = 0;
        for (int i = 0; i < compactedDisk.length; i ++) {
            checksum += (long) i * compactedDisk[i];
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