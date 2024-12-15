package com.gabrieltelles.adventofcode2024.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day10 {
    private static final String INPUT_PATH = "day10/src/main/resources/input.txt";
    private static final int TRAILHEAD = 0;

    public static void main(String[] args) {
        int[][] topographicMap = loadMapFromPath(INPUT_PATH);
        for (int[] row : topographicMap) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    static Set<Point> getTrailheads(int[][] topographicMap) {
        Set<Point> trailheads = new HashSet<>();
        for (int row = 0; row < topographicMap.length; row++) {
            for (int col = 0; col < topographicMap[0].length; col++) {
                if (topographicMap[row][col] == TRAILHEAD) {
                    trailheads.add(new Point(row, col));
                }
            }
        }
        return trailheads;
    }

    static int[][] loadMapFromPath(String path) {
        int[][] topographicMap;
        try (var lines = Files.lines(Paths.get(path))) {
            List<int[]> mapList = lines
                    .map(line -> line.chars()
                            .map(Character::getNumericValue)
                            .toArray()
                    ).toList();
            topographicMap = mapList.toArray(new int[0][]);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load " + e.getMessage(), e);
        }
        return topographicMap;
    }
}