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
    private static final int FREE_SPACE = -1;

    public static void main(String[] args) {
        var diskMap = loadDiskMap(INPUT_PATH);
        var disk = getDiskFrom(diskMap);

        // Part One
        var compactedDisk = compact(disk);
        var checksum = calculateChecksum(compactedDisk);
        System.out.println("Simple compact checksum: " + checksum);

        // Part Two
        var blockCompactedDisk = blockCompact(disk);
        var blockChecksum = calculateChecksum(blockCompactedDisk);
        System.out.println("Block compact checksum: " + blockChecksum);
    }

    static int[] getDiskFrom(char[] diskMap) {
        List<Integer> result = new LinkedList<>();
        boolean isFreeSpace = false;
        int idNumber = 0;
        for (char c : diskMap) {
            if (isFreeSpace) {
                int lengthOfFreeSpace = Character.getNumericValue(c);
                result.addAll(Collections.nCopies(lengthOfFreeSpace, FREE_SPACE));
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
            while (result[left] != FREE_SPACE) {left++;}
            while (result[right] == FREE_SPACE) {right--;}
            if (left < right) {
                result[left] = result[right];
                result[right] = FREE_SPACE;
            }
        }
        return result;
    }

    static int[] blockCompact(int[] disk) {
        int[] result = Arrays.copyOf(disk, disk.length);
        int freeSpaceLeft = 0;
        int freeSpaceRight = 0;
        int blockRight = disk.length - 1;
        int blockLeft = blockRight;
        while (blockLeft > 0) {
            while (result[blockRight] == FREE_SPACE) {
                blockRight--;
                blockLeft--;
            }
            while (result[blockLeft] == result[blockRight]) {
                blockLeft--;
            }
            int blockSize = blockRight - blockLeft;

            do {
                freeSpaceRight = freeSpaceLeft;
                do {
                    freeSpaceLeft++;
                    freeSpaceRight++;
                }
                while (result[freeSpaceLeft] != FREE_SPACE);
                while (result[freeSpaceRight] == FREE_SPACE) {
                    freeSpaceRight++;
                }
            } while ((freeSpaceRight - freeSpaceLeft) < blockSize && freeSpaceRight < blockLeft);

            int freeSpaceSize = freeSpaceRight - freeSpaceLeft;
            if (freeSpaceSize >= blockSize) {
                int blockValue = result[blockRight];
                Arrays.fill(result, freeSpaceLeft, freeSpaceLeft + blockSize, blockValue);
                Arrays.fill(result, blockLeft + 1, blockRight + 1, FREE_SPACE);

                freeSpaceLeft = 0;
                freeSpaceRight = 0;
            }
        }
        return result;
    }

    private static boolean getPreviousBlock(int[] disk, int blockLeft, int blockRight) {
        boolean hasNotTriedThisBlock = true;
        while (disk[blockRight] == FREE_SPACE && blockRight > 0 && hasNotTriedThisBlock) {
            blockRight--;
            blockLeft--;
        }
        while (disk[blockLeft] == disk[blockRight] && blockLeft > 0) {
            blockLeft--;
        }
        if (blockLeft > 0) {

        }
        return true;
    }

    static long calculateChecksum(int[] compactedDisk) {
        long checksum = 0;
        for (int i = 0; i < compactedDisk.length; i ++) {
            if (compactedDisk[i] != FREE_SPACE) {
                checksum += (long) i * compactedDisk[i];
            }
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