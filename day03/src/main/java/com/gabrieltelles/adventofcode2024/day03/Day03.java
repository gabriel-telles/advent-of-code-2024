package com.gabrieltelles.adventofcode2024.day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day03 {
    private static final String INPUT_PATH = "day03/src/main/resources/inputData.txt";

    private static final String MUL_REGEX = "mul\\(\\d{1,3},\\d{1,3}\\)";
    static final Pattern MUL_PATTERN = Pattern.compile(MUL_REGEX);

    private static final String COMMAND = "do\\(\\)|don't\\(\\)";
    private static final String MUL_OR_COMMAND_REGEX = MUL_REGEX + "|" + COMMAND;
    static final Pattern MUL_OR_COMMAND_PATTERN = Pattern.compile(MUL_OR_COMMAND_REGEX);

    private static final String NUMBER_REGEX = "\\d{1,3}";
    private static final Pattern NUMBER_PATTERN = Pattern.compile(NUMBER_REGEX);

    public static void main(String[] args) {
        try {
            String input = Files.readString(Path.of(INPUT_PATH));
            processMULCommands(input);
            processMULWithStateCommands(input);
        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
        }
    }

    private static void processMULCommands(String input) {
        findMatches(input, MUL_PATTERN)
                .map(Day03::executeMultiplication)
                .reduce(Integer::sum)
                .ifPresent(result -> System.out.println("Part One: " + result));
    }

    private static void processMULWithStateCommands(String input) {
        findMatches(input, MUL_OR_COMMAND_PATTERN)
                .filter(new CommandStateTracker()::filterAndTrackState)
                .map(Day03::executeMultiplication)
                .reduce(Integer::sum)
                .ifPresent(result -> System.out.println("Part Two: " + result));
    }

    static Stream<String> findMatches(String input, Pattern pattern) {
        return pattern.matcher(input).results().map(MatchResult::group);
    }

    static int executeMultiplication(String input) {
        return NUMBER_PATTERN.matcher(input)
                .results()
                .mapToInt(match -> Integer.parseInt(match.group()))
                .reduce(1, (a, b) -> a * b);
    }

    private static class CommandStateTracker {
        private boolean isEnabled = true;

        public boolean filterAndTrackState(String command) {
            return switch (command) {
                case "do()" -> {
                    isEnabled = true;
                    yield false;
                }
                case "don't()" -> {
                    isEnabled = false;
                    yield false;
                }
                default -> isEnabled;
            };
        }
    }
}