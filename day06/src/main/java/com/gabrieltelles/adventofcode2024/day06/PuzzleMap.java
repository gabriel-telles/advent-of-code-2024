package com.gabrieltelles.adventofcode2024.day06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class PuzzleMap {
    public static final char OBSTACLE = '#';
    public static final char VISITED = 'X';

    private final char[][] map;
    private final int height;
    private final int width;

    private PuzzleMap(char[][] map) {
        this.map = map;
        this.height = map.length;
        this.width = map[0].length;
    }

    public char[][] getMap() {
        return Arrays.copyOf(map, map.length);
    }

    public void updateGuard(Guard guard) {
        var guardPosition = guard.getPosition();
        map[guardPosition[0]][guardPosition[1]] = guard.getOrientation();
    }

    public boolean isInBounds(int[] position) {
        if (position.length != 2) {
            throw new IllegalArgumentException("Position must be a int[] of size 2");
        }
        return position[0] >= 0 && position[0] < height && position[1] >= 0 && position[1] < width;
    }

    public char charAt(int[] position) {
        if (position.length != 2) {
            throw new IllegalArgumentException("Position must be a int[] of size 2");
        }
        return map[position[0]][position[1]];
    }

    public void walkedOn(int[] position) {
        if (charAt(position) == OBSTACLE) {
            throw new IllegalArgumentException("Cannot walk over obstacles");
        }
        map[position[0]][position[1]] = VISITED;
    }

    public int countVisitedPositions() {
        int result = 0;
        for (char[] chars : map) {
            for (char aChar : chars) {
                if (aChar == VISITED) {
                    result++;
                }
            }
        }
        return result;
    }

    public static PuzzleMap from(String path) {
        char[][] map;
        try (var lines = Files.lines(Paths.get(path))) {
            List<String> tempList = lines.toList();
            map = new char[tempList.size()][];
            for (int i = 0; i < tempList.size(); i++) {
                map[i] = tempList.get(i).toCharArray();
            }
        } catch (IOException e) {
            System.out.println("Failed to load " + e.getMessage());
            return null;
        }
        return new PuzzleMap(map);
    }
}
