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
        var diskBlockFinder = new DiskBlockFinder(result);
        int[] block = diskBlockFinder.nextBlock();
        int blockLeft;
        int blockRight;
        int[] end = new int[] {FREE_SPACE, FREE_SPACE};
        while (!Arrays.equals(block, end)) {
            blockLeft = block[0];
            blockRight = block[1];
            int blockLength = blockRight - blockLeft + 1;

            var freeSpaceFinder = new FreeSpaceFinder(result);
            int[] freeSpace = freeSpaceFinder.nextFreeSpace();
            int freeSpaceLeft;
            int freeSpaceRight;
            boolean hasNotSwitchedYet = true;
            while (!Arrays.equals(freeSpace, end) && hasNotSwitchedYet) {
                freeSpaceLeft = freeSpace[0];
                freeSpaceRight = freeSpace[1];
                int freeSpaceLength = freeSpaceRight - freeSpaceLeft + 1;
                if (blockLength <= freeSpaceLength && freeSpaceRight <= blockLeft ) {
                    int blockValue = result[blockLeft];
                    Arrays.fill(result, freeSpaceLeft, freeSpaceLeft + blockLength, blockValue);
                    Arrays.fill(result, blockLeft, blockLeft + blockLength, FREE_SPACE);
                    hasNotSwitchedYet = false;
                }
                freeSpace = freeSpaceFinder.nextFreeSpace();
            }

            block = diskBlockFinder.nextBlock();
        }
        return result;
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