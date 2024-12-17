package com.gabrieltelles.adventofcode2024.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day12 {
    private static final String INPUT_PATH = "day12/src/main/resources/input.txt";

    public static void main(String[] args) {
        char[][] field = loadChar2DArrayFromPath(INPUT_PATH);
        long price = calculateFencingPrice(field);
        System.out.println("Fencing price: " + price);
    }

    static long calculateFencingPrice(char[][] field) {
        Map<Point, List<Point>> regions = groupRegions(field);
        return 0;
    }

    static Map<Point, List<Point>> groupRegions(char[][] field) {
        UnionFind uf = new UnionFind();
        int rows = field.length;
        int cols = field[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Point current = new Point(i,j);
                char currentChar = field[i][j];

                if (i + 1 < rows && field[i+1][j] == currentChar) {
                    uf.union(current, new Point(i+1, j));
                }
                if (j + 1 < cols && field[i][j+1] == currentChar) {
                    uf.union(current, new Point(i, j+1));
                }
            }
        }

        Map<Point, List<Point>> regions = new HashMap<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Point root = uf.find(new Point(i, j));
                regions.computeIfAbsent(root, k -> new ArrayList<>()).add(new Point(i, j));
            }
        }

        return regions;
    }

    static char[][] loadChar2DArrayFromPath(String path) {
        char[][] array2D;
        try (var lines = Files.lines(Paths.get(path))) {
            List<String> tempList = lines.toList();
            array2D = new char[tempList.size()][];
            for (int i = 0; i < tempList.size(); i++) {
                array2D[i] = tempList.get(i).toCharArray();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load " + e.getMessage(), e);
        }
        return array2D;
    }
}