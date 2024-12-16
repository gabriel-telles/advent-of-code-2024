package com.gabrieltelles.adventofcode2024.day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Day11 {
    private static final String INPUT_PATH = "day11/src/main/resources/input.txt";

    public static void main(String[] args) {
        Map<Long, Long> stones = loadStonesFromPath(INPUT_PATH);

        Map<Long, Long> stonesAfter25Blinks = applyBlinks(stones, 25);
        System.out.println("Total number of stones after 25 blinks: " + totalStones(stonesAfter25Blinks));

        Map<Long, Long> stonesAfter75Blinks = applyBlinks(stones, 75);
        System.out.println("Total number of stones after 75 blinks: " + totalStones(stonesAfter75Blinks));
    }

    static Map<Long, Long> applyBlinks(Map<Long, Long> initialStones, int numberOfBlinks) {
        Map<Long, Long> result = new HashMap<>(initialStones);
        for (int i = 0; i < numberOfBlinks; i++) {
            System.out.println("Step " + i);
            result = applyBlink(result);
        }
        return result;
    }

    private static Map<Long, Long> applyBlink(Map<Long, Long> initialStones) {
        Map<Long, Long> result = new HashMap<>();

        for (Map.Entry<Long, Long> entry : initialStones.entrySet()) {
            Long stone = entry.getKey();
            Long count = entry.getValue();

            if (stone == 0L) {
                result.put(1L, result.getOrDefault(1L, 0L) + count);
            } else if (stone.toString().length() % 2 == 0) {
                var numberString = stone.toString();
                int middleIndex = numberString.length() / 2;

                var firstHalf = numberString.substring(0, middleIndex);
                var secondHalf = numberString.substring(middleIndex);

                Long firstPart = Long.parseLong(firstHalf);
                Long secondPart = Long.parseLong(secondHalf);

                result.put(firstPart, result.getOrDefault(firstPart, 0L) + count);
                result.put(secondPart, result.getOrDefault(secondPart, 0L) + count);
            } else {
                Long newStone = stone * 2024;
                result.put(newStone, result.getOrDefault(newStone, 0L) + count);
            }
        }
        return result;
    }

    static long totalStones(Map<Long, Long> stones) {
        return stones.values().stream().mapToLong(Long::longValue).sum();
    }

    static Map<Long, Long> loadStonesFromPath(String path) {
        Map<Long, Long> stones = new HashMap<>();
        try {
            String line = Files.readString(Paths.get(path));
            for (String stoneString : line.trim().split("\\s+")) {
                Long stone = Long.parseLong(stoneString);
                stones.put(stone, stones.getOrDefault(stone, 0L) + 1);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load " + e.getMessage(), e);
        }
        return stones;
    }
}