package com.gabrieltelles.adventofcode2024.day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day04 {
    private static final String INPUT_PATH = "day04/src/main/resources/inputData.txt";
    private static final String XMAS_REGEX = "XMAS|SAMX";
    public static final Pattern PATTERN = Pattern.compile(XMAS_REGEX);
    private static final String X_MAS_REGEX = "(M.M.A.S.S)|(M.S.A.M.S)|(S.M.A.S.M)|(S.S.A.M.M)";
    public static final Pattern X_MAS_PATTERN = Pattern.compile(X_MAS_REGEX);

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

        int numberOfX_MAS = countX_MAS(input);
        System.out.println("Count of X-MAS: " + numberOfX_MAS);
    }

    static int countXMAS(List<String> input) {
        return countHorizontalOccurrences(input, PATTERN) + countVerticalOccurrences(input, PATTERN) + countDiagonalOccurrences(input, PATTERN);
    }

    static int countHorizontalOccurrences(List<String> input, Pattern pattern) {
        int horizontalCount = 0;
        for (var line : input) {
            horizontalCount += countOccurrences(line, pattern);
        }
        System.out.println("Horizontal: " + horizontalCount);
        return horizontalCount;
    }

    static int countVerticalOccurrences(List<String> input, Pattern pattern) {
        int verticalCount = 0;
        List<String> transposedInput = transposeMatrix(input);
        for (var column : transposedInput) {
            verticalCount += countOccurrences(column, pattern);
        }
        System.out.println("Vertical: " + verticalCount);
        return verticalCount;
    }

    static int countDiagonalOccurrences(List<String> input, Pattern pattern) {
        List<String> mainDiagonals = extractDiagonals(input, true);
        List<String> antiDiagonals = extractDiagonals(input, false);

        int diagonalCount = 0;
        for (var diagonal : mainDiagonals) {
            diagonalCount += countOccurrences(diagonal, pattern);
        }
        for (var diagonal : antiDiagonals) {
            diagonalCount += countOccurrences(diagonal, pattern);
        }
        System.out.println("Diagonals: " + diagonalCount);
        return diagonalCount;
    }

    static int countX_MAS(List<String> input) {
        var squares = get3x3squares(input);
        int squareCount = 0;
        for (var square : squares) {
            squareCount += countOccurrences(square, X_MAS_PATTERN);
        }
        return  squareCount;
    }

    private static List<String> get3x3squares(List<String> matrix) {
        List<String> result = new ArrayList<>();
        int width = matrix.getFirst().length();

        for (int i = 0; i <= matrix.size() - 3; i++) {
            for (int j = 0; j <= width - 3; j++) {
                StringBuilder square = new StringBuilder(9);
                for (int k = 0; k < 3; k++) {
                    square.append(matrix.get(i + k), j, j + 3);
                }
                result.add(square.toString());
            }
        }

        return result;
    }

    private static int countOccurrences(String line, Pattern pattern) {
        var matcher = pattern.matcher(line);
        int count = 0;
        int start = 0;
        while (matcher.find(start)) {
            count++;
            start = matcher.start() + 1;
        }
        return count;
    }

    private static List<String> transposeMatrix(List<String> matrix) {
        if (matrix.isEmpty()) {
            return new LinkedList<>();
        }

        int cols = matrix.getFirst().length();
        List<String> transposed = new LinkedList<>();

        for (int col = 0; col < cols; col++) {
            StringBuilder sb = new StringBuilder();
            for (String row : matrix) {
                sb.append(row.charAt(col));
            }
            transposed.add(sb.toString());
        }
        return transposed;
    }

    private static List<String> extractDiagonals(List<String> matrix, boolean isMain) {
        int rows = matrix.size();
        int cols = matrix.getFirst().length();
        int numDiagonals = rows + cols - 1;
        List<StringBuilder> diagonals = new ArrayList<>();

        for (int i = 0; i < numDiagonals; i++) {
            diagonals.add(new StringBuilder());
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int index = isMain ? row - col + cols - 1 : row + col;
                diagonals.get(index).append(matrix.get(row).charAt(col));
            }
        }

        return diagonals.stream()
                .map(StringBuilder::toString)
                .collect(Collectors.toList());
    }
}