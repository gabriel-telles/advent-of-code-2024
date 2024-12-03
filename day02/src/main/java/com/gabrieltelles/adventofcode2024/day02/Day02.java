package com.gabrieltelles.adventofcode2024.day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day02 {
    private static final String INPUT_PATH = "day02/src/main/resources/inputData.csv";
    private static final String DELIMITER = " ";
    public static final int SMALLEST_SAFE_DIFFERENCE = 1;
    public static final int LARGEST_SAFE_DIFFERENCE = 3;

    public static void main(String[] args) {
        List<List<Integer>> reports;
        try (var lines = Files.lines(Paths.get(INPUT_PATH))) {
            reports = lines.map(line -> Stream.of(line.split(DELIMITER)).map(Integer::parseInt).toList()).toList();
        } catch (IOException _) {
            System.out.println("Failed to open file");
            return;
        }

        long numberOfSafeReports = reports.stream().filter(Day02::isSafe).count();
        System.out.println(numberOfSafeReports);

        long numberOfSafeReportsWithProblemDampener = reports.stream().filter(Day02::isSafeWithProblemDampener).count();
        System.out.println(numberOfSafeReportsWithProblemDampener);
    }

    private static boolean isSafe(List<Integer> report) {
        return (isIncreasing(report) || isDecreasing(report)) && adjacentDifferencesBetweenLimits(report);
    }

    private static boolean isIncreasing(List<Integer> report) {
        for (int i = 0; i < report.size() - 1; i++) {
            if (report.get(i) >= report.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDecreasing(List<Integer> report) {
        for (int i = 0; i < report.size() - 1; i++) {
            if (report.get(i) <= report.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    private static boolean adjacentDifferencesBetweenLimits(List<Integer> report) {
        for (int i = 0; i < report.size() - 1; i++) {
            int diff = Math.abs(report.get(i) - report.get(i + 1));
            if (diff < SMALLEST_SAFE_DIFFERENCE || diff > LARGEST_SAFE_DIFFERENCE) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSafeWithProblemDampener(List<Integer> originalReport) {
        List<Integer> report = new ArrayList<>(originalReport);
        if (isSafe(report)) {
            return true;
        }
        for (int i = 0; i < report.size(); i++) {
            Integer removedLevel = report.remove(i);
            if (isSafe(report)) {
                return true;
            }
            report.add(i, removedLevel);
        }
        return false;
    }
}