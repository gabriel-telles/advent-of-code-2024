package com.gabrieltelles.adventofcode2024.day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class Day04 {
    private static final String INPUT_PATH = "day04/src/main/resources/inputData.txt";

    public static void main(String[] args) {
        List<String> input;
        try (var lines = Files.lines(Path.of(INPUT_PATH))) {
            input = lines.toList();
        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
            return;
        }

        int numberOfXMAS = countXMAS(input);
        System.out.println("Total: " + numberOfXMAS);
    }

    static int countXMAS(List<String> input) {
        String regex = "XMAS|SAMX";
        var pattern = Pattern.compile(regex);
        return countHorizontal(input, pattern) + countVertical(input, pattern) + countDiagonal(input,pattern);
    }

    static int countHorizontal(List<String> input, Pattern pattern) {
        int horizontalCount = 0;
        for (var line : input) {
            horizontalCount += countOccurrencesIn(line, pattern);
        }
        System.out.println("Horizontal: " + horizontalCount);
        return horizontalCount;
    }

    static int countVertical(List<String> input, Pattern pattern) {
        int verticalCount = 0;
        List<String> transposedInput = transpose(input);
        for (var column : transposedInput) {
            verticalCount += countOccurrencesIn(column, pattern);
        }
        System.out.println("Vertical: " + verticalCount);
        return verticalCount;
    }

    static int countDiagonal(List<String> input, Pattern pattern) {
        return 0;
    }

    private static int countOccurrencesIn(String line, Pattern pattern) {
        var matcher = pattern.matcher(line);
        int from = 0;
        int count = 0;
        while(matcher.find(from)) {
            count++;
            from = matcher.start() + 1;
        }
        return count;
    }

    private static List<String> transpose(List<String> input) {
        if (input.isEmpty()) {
            return new LinkedList<>();
        }

        int stringLength = input.getFirst().length();
        List<String> transposed = new LinkedList<>();

        for (int i = 0; i < stringLength; i++) {
            StringBuilder sb = new StringBuilder();
            for (String str : input) {
                sb.append(str.charAt(i));
            }
            transposed.add(sb.toString());
        }

        return transposed;
    }
}