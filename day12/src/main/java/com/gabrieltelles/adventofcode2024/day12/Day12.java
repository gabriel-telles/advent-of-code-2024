package com.gabrieltelles.adventofcode2024.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day12 {
    private static final String INPUT_PATH = "day12/src/main/resources/input.txt";

    public static void main(String[] args) {
        char[][] field = loadChar2DArrayFromPath(INPUT_PATH);
        long price = calculateFencingPrice(field);
        System.out.println("Fencing price: " + price);
    }

    static long calculateFencingPrice(char[][] field) {
        return 0;
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