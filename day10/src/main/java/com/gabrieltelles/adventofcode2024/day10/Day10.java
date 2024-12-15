package com.gabrieltelles.adventofcode2024.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

public class Day10 {
    private static final String INPUT_PATH = "day10/src/main/resources/input.txt";

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
        return Set.of();
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