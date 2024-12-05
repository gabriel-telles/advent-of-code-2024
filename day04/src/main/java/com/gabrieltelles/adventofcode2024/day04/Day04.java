package com.gabrieltelles.adventofcode2024.day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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
        int diagonalCount = 0;
        List<String> mainDiagonals = getMainDiagonals(input);
        List<String> antiDiagonals = getAntiDiagonals(input);
        for (var diagonal : mainDiagonals) {
            diagonalCount += countOccurrencesIn(diagonal, pattern);
        }
        for (var diagonal : antiDiagonals) {
            diagonalCount += countOccurrencesIn(diagonal, pattern);
        }
        System.out.println("Diagonals: " + diagonalCount);
        return diagonalCount;
    }

    public static List<String> getMainDiagonals(List<String> matrix) {
        int numRows = matrix.size();
        int numCols = matrix.getFirst().length();
        List<StringBuilder> diagonals = new ArrayList<>();

        for (int i = 0; i < numRows + numCols - 1; i++) {
            diagonals.add(new StringBuilder());
        }

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                int index = row - col + (numCols - 1); // Adjust to non-negative index
                diagonals.get(index).append(matrix.get(row).charAt(col));
            }
        }

        List<String> result = new ArrayList<>();
        for (StringBuilder diagonal : diagonals) {
            result.add(diagonal.toString());
        }

        return result;
    }

    public static List<String> getAntiDiagonals(List<String> matrix) {
        int numRows = matrix.size();
        int numCols = matrix.getFirst().length();
        List<StringBuilder> diagonals = new ArrayList<>();

        for (int i = 0; i < numRows + numCols - 1; i++) {
            diagonals.add(new StringBuilder());
        }

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                int index = row + col;
                diagonals.get(index).append(matrix.get(row).charAt(col));
            }
        }

        List<String> result = new LinkedList<>();
        for (StringBuilder diagonal : diagonals) {
            result.add(diagonal.toString());
        }

        return result;
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