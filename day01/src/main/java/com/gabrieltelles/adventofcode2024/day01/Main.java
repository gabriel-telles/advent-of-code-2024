package com.gabrieltelles.adventofcode2024.day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static final String INPUT_PATH = "day01/src/main/resources/inputData.csv";
    private static final String DELIMITER = "\\s+";

    public static void main(String[] args) {
        List<List<String>> records;
        try (var lines = Files.lines(Paths.get(INPUT_PATH))) {
            records = lines.map(line -> List.of(line.split(DELIMITER))).toList();
        } catch (IOException _) {
            System.out.println("Failed to open file");
            return;
        }

        List<Integer> leftList = extractSortedList(records, 0);
        List<Integer> rightList = extractSortedList(records, 1);

        int resultOfPartOne = partOne(leftList, rightList);
        System.out.println(resultOfPartOne);

        long resultOfPartTwo = partTwo(leftList, rightList);
        System.out.println(resultOfPartTwo);
    }

    private static List<Integer> extractSortedList(List<List<String>> records, int index) {
        return records.stream()
                .map(record -> Integer.parseInt(record.get(index)))
                .sorted()
                .toList();
    }

    private static int partOne(List<Integer> left, List<Integer> right) {
        return IntStream.range(0, left.size()).map(i -> Math.abs(left.get(i) - right.get(i))).sum();
    }

    private static long partTwo(List<Integer> left, List<Integer> right) {
        Map<Integer, Long> counts = right.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        return left.stream()
                .mapToLong(i -> i * counts.getOrDefault(i, 0L))
                .sum();
    }

}