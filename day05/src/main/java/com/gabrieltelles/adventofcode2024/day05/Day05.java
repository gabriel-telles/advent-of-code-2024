package com.gabrieltelles.adventofcode2024.day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class Day05 {
    private static final String INPUT_PATH = "day05/src/main/resources/rules.csv";
    private static final String DELIMITER = "\\|";
    public static void main(String[] args) {
        List<List<Integer>> rules;
        try (var lines = Files.lines(Paths.get(INPUT_PATH))) {
            rules = lines.map(line -> Stream.of(line.split(DELIMITER)).map(Integer::parseInt).toList()).toList();
        } catch (IOException _) {
            System.out.println("Failed to open file");
            return;
        }
        System.out.println(rules.getFirst().getLast());
    }
}