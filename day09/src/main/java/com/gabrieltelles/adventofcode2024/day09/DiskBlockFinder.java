package com.gabrieltelles.adventofcode2024.day09;

public class DiskBlockFinder {
    private static final int EMPTY_SPACE = -1;
    private final int[] disk;
    private int currentIndex;

    public DiskBlockFinder(int[] disk) {
        this.disk = disk;
        this.currentIndex = disk.length - 1;
    }

    public int[] nextBlock() {
        currentIndex = skipEmptySpaces(currentIndex);

        if (noMoreBlocksToProcess()) {
            return new int[]{EMPTY_SPACE, EMPTY_SPACE};
        }

        int end = currentIndex;
        currentIndex = findEndOfNextBlock(currentIndex);
        int start = currentIndex + 1;
        return new int[]{start, end};
    }

    private int skipEmptySpaces(int currentIndex) {
        int enfOfEmptySpaceIndex = currentIndex;
        while (enfOfEmptySpaceIndex >= 0 && disk[enfOfEmptySpaceIndex] == EMPTY_SPACE) {
            enfOfEmptySpaceIndex--;
        }
        return enfOfEmptySpaceIndex;
    }

    private boolean noMoreBlocksToProcess() {
        return currentIndex < 0;
    }

    private int findEndOfNextBlock(int currentIndex) {
        int endOfBlockIndex = currentIndex;
        int blockValue = disk[currentIndex];
        while (endOfBlockIndex >= 0 && disk[endOfBlockIndex] == blockValue) {
            endOfBlockIndex--;
        }
        return endOfBlockIndex;
    }
}
