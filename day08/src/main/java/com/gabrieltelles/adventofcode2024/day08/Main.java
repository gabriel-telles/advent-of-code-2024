package com.gabrieltelles.adventofcode2024.day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    private static final String INPUT_PATH = "day08/src/main/resources/input.txt";
    public static void main(String[] args) {
        char[][] antennas = loadAntennasFromPath(INPUT_PATH);
        System.out.println(antennas[0]);
    }

    private static char[][] loadAntennasFromPath(String path) {
        char[][] antennas;
        try (var lines = Files.lines(Paths.get(path))) {
            List<String> tempList = lines.toList();
            antennas = new char[tempList.size()][];
            for (int i = 0; i < tempList.size(); i++) {
                antennas[i] = tempList.get(i).toCharArray();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load " + e.getMessage(), e);
        }
        return antennas;
    }
}