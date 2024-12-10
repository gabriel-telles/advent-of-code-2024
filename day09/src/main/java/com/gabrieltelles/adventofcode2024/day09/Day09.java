package com.gabrieltelles.adventofcode2024.day09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Day09 {

    private static final String INPUT_PATH = "day09/src/main/resources/input.txt";
    private static final int EMPTY_POSITION = -1;

    public static void main(String[] args) {
        var diskMap = loadDiskMap(INPUT_PATH);
        var disk = getDiskFrom(diskMap);
        var compactedDisk = compact(disk);
        var checksum = calculateChecksum(compactedDisk);
        System.out.println(checksum);

    }

    static int[] getDiskFrom(char[] diskMap) {
        List<Integer> result = new LinkedList<>();
        boolean isFreeSpace = false;
        int idNumber = 0;
        for (char c : diskMap) {
            if (isFreeSpace) {
                int lengthOfFreeSpace = Character.getNumericValue(c);
                result.addAll(Collections.nCopies(lengthOfFreeSpace, EMPTY_POSITION));
                isFreeSpace = false;
            } else {
                int lengthOfFile = Character.getNumericValue(c);
                result.addAll(Collections.nCopies(lengthOfFile, idNumber));
                idNumber++;
                isFreeSpace = true;
            }
        }

        return result.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    static int[] compact(int[] disk) {
        int[] result = Arrays.copyOf(disk, disk.length);
        int left = 0;
        int right = disk.length - 1;
        while (left < right) {
            while (result[left] != EMPTY_POSITION) {left++;}
            while (result[right] == EMPTY_POSITION) {right--;}
            if (left < right) {
                result[left] = result[right];
                result[right] = EMPTY_POSITION;
            }
        }
        int realLength = disk.length;
        for (int i = 0; i < result.length; i++) {
            if (result[i] == EMPTY_POSITION) {
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

    static char[] loadDiskMap(String path) {
        try {
            return Files.readString(Path.of(path)).toCharArray();
        } catch (IOException e) {
            throw new RuntimeException("Error reading the input file: " + e.getMessage());
        }
    }
}