package com.gabrieltelles.adventofcode2024.day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Day11 {
    private static final String INPUT_PATH = "day11/src/main/resources/input.txt";
    
    public static void main(String[] args) {
        List<Long> stones = loadStonesFromPath(INPUT_PATH);
        List<Long> stonesAfterBlinks = applyBlinks(stones, 25);
        System.out.println("Number of stones: " + stonesAfterBlinks.size());
    }

    static List<Long> applyBlinks(List<Long> initialStones, int numberOfBlinks) {
        List<Long> result = new LinkedList<>(initialStones);
        for (int i = 0; i < numberOfBlinks; i++) {
            result = applyBlink(result);
        }
        return result;
    }

    private static List<Long> applyBlink(List<Long> initialStones) {
        List<Long> result = new LinkedList<>(initialStones);
        result = result.stream().flatMap(stone -> {
            if (stone == 0L) {
                return Stream.of(1L);
            } else if (stone.toString().length() % 2 == 0) {
                var numberString = stone.toString();
                int middleIndex = numberString.length() / 2;

                var firstHalf = numberString.substring(0, middleIndex);
                var secondHalf = numberString.substring(middleIndex);

                Long firstPart = Long.parseLong(firstHalf);
                Long secondPart = Long.parseLong(secondHalf);

                return Stream.of(firstPart, secondPart);
            } else {
                return Stream.of(stone * 2024);
            }
        }).toList();
        return result;
    }

    static List<Long> loadStonesFromPath(String path) {
        List<Long> stones;
        try {
            String line = Files.readString(Paths.get(path));
            stones = Stream.of(line.trim().split("\\s+")).map(Long::parseLong).toList();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load " + e.getMessage(), e);
        }
        return stones;
    }
}