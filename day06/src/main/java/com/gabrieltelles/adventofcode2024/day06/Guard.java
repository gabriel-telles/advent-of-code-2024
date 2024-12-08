package com.gabrieltelles.adventofcode2024.day06;

import java.util.Arrays;

public class Guard {
    private final int[] position;
    private char orientation;

    private Guard(int[] position, char orientation) {
        this.position = Arrays.copyOf(position,2);
        this.orientation = orientation;
    }

    public int[] getPosition() {
        return position;
    }

    public char getOrientation() {
        return orientation;
    }

    public void moveForward() {
        switch (orientation) {
            case '^' -> position[0]--;
            case 'v' -> position[0]++;
            case '<' -> position[1]--;
            case '>' -> position[1]++;
        }
    }

    public void changeOrientation() {
        switch (orientation) {
            case '^' -> orientation = '>';
            case 'v' -> orientation = '<';
            case '<' -> orientation = '^';
            case '>' -> orientation = 'v';
        }
    }

    public int[] getNextPosition() {
        int[] nextPosition = Arrays.copyOf(position, 2);
        switch (orientation) {
            case '^' -> nextPosition[0]--;
            case 'v' -> nextPosition[0]++;
            case '<' -> nextPosition[1]--;
            case '>' -> nextPosition[1]++;
        }
        return nextPosition;
    }

    public static Guard from(char[][] map) {
        char[] orientations = {'^', 'v', '<', '>'};
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                for (char orientation : orientations) {
                    if (map[row][col] == orientation) {
                        return new Guard(new int[]{row, col}, orientation);
                    }
                }
            }
        }
        throw new IllegalArgumentException("No guard found in the puzzle map");
    }
}
