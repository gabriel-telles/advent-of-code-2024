package com.gabrieltelles.adventofcode2024.day09;

public class FreeSpaceFinder {
    private static final int EMPTY_SPACE = -1;
    private final int[] disk;
    private int currentIndex;

    public FreeSpaceFinder(int[] disk) {
        this.disk = disk;
        this.currentIndex = 0;
    }

    public int[] nextFreeSpace() {
        currentIndex = skipNonFreeSpace(currentIndex);

        if (noMoreFreeSpacesToProcess(currentIndex)) {
            return new int[]{EMPTY_SPACE, EMPTY_SPACE};     // No more free spaces to process
        }

        int start = currentIndex;
        currentIndex = findEndOfFreeSpace(currentIndex);
        int end = currentIndex - 1;
        return new int[]{start, end};
    }

    private int skipNonFreeSpace(int currentIndex) {
        int freeSpaceStart = currentIndex;
        while (freeSpaceStart < disk.length && disk[freeSpaceStart] != EMPTY_SPACE) {
            freeSpaceStart++;
        }
        return freeSpaceStart;
    }

    private boolean noMoreFreeSpacesToProcess(int currentIndex) {
        return currentIndex >= disk.length;
    }

    private int findEndOfFreeSpace(int currentIndex) {
        int endOfFreeSpace = currentIndex;
        while (endOfFreeSpace < disk.length && disk[endOfFreeSpace] == EMPTY_SPACE) {
            endOfFreeSpace++;
        }
        return endOfFreeSpace;
    }
}
