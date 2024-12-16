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
    private static final int SUMMIT = 9;

    public static void main(String[] args) {
        int[][] topographicMap = loadMapFromPath(INPUT_PATH);
        int sumOfScores = 0;
        for (Point trailhead : getTrailheads(topographicMap)) {
            sumOfScores += score(topographicMap, trailhead, new HashSet<>());
        }
        System.out.println(sumOfScores);
    }

    static int score(int[][] map, Point p, Set<Point> reachableSummits) {
        if (isOutOfBounds(map,p))
            return 0;

        int height = map[p.row()][p.col()];
        if (height == SUMMIT) {
            reachableSummits.add(p);
            return 1;
        }

        int neighborsTrailsToSummits = 0;
        for (var neighbor : getNeighborsOf(p)) {
            if (!isOutOfBounds(map, neighbor) && !reachableSummits.contains(neighbor) && map[neighbor.row()][neighbor.col()] == height + 1)
                neighborsTrailsToSummits += score(map, neighbor, reachableSummits);
        }
        return neighborsTrailsToSummits;
    }

    private static Set<Point> getNeighborsOf(Point p) {
        return Set.of(
                new Point(p.row()+1, p.col()),
                new Point(p.row()-1, p.col()),
                new Point(p.row(), p.col()+1),
                new Point(p.row(), p.col()-1)
        );
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

    private static boolean isOutOfBounds(int[][] map, Point p) {
        return p.row() < 0 || p.row() >= map.length || p.col() < 0 || p.col() >= map[0].length;
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