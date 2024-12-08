package com.gabrieltelles.adventofcode2024.day05;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Day05 {
    private static final String RULES_PATH = "day05/src/main/resources/rules.csv";
    private static final String RULES_DELIMITER = "\\|";

    private static final String UPDATES_PATH = "day05/src/main/resources/updates.csv";
    private static final String UPDATES_DELIMITER = ",";

    public static void main(String[] args) {
        SetMultimap<String, String> rules = loadRules(RULES_PATH);
        List<List<String>> updates = loadUpdates(UPDATES_PATH);
        if (rules == null || updates == null) return;

        List<List<String>> validUpdates = updates.stream().filter(up -> isUpdateValid(up, rules)).toList();
        int middlePageSum = validUpdates.stream().mapToInt(Day05::getMiddleElement).sum();

        System.out.println("Middle page sum for Valid updates: " + middlePageSum);
    }

    static boolean isUpdateValid(List<String> update, SetMultimap<String, String> rules) {
        ListIterator<String> iterator = update.listIterator(update.size());
        List<String> successors = new LinkedList<>();
        while (iterator.hasPrevious()) {
            String element = iterator.previous();
            for (var successor : successors) {
                if (rules.get(element).contains(successor)) {
                    return false;
                }
            }
            successors.add(element);
        }
        return true;
    }

    static int getMiddleElement(List<String> update) {
        int middleIndex = (update.size() - 1) / 2;
        return Integer.parseInt(update.get(middleIndex));
    }

    static SetMultimap<String, String> loadRules(String path) {
        SetMultimap<String, String> rules = HashMultimap.create();
        try (var lines = Files.lines(Paths.get(path))) {
            lines.map(line -> line.split(RULES_DELIMITER))
                    .forEach(rule -> rules.put(rule[1], rule[0]));
        } catch (IOException e) {
            System.out.println("Failed to load " + e.getMessage());
            return null;
        }
        return rules;
    }

    static List<List<String>> loadUpdates(String path) {
        List<List<String>> updates;
        try (var lines = Files.lines(Paths.get(path))) {
            updates = lines.map(line -> List.of(line.split(UPDATES_DELIMITER))).toList();
        } catch (IOException e) {
            System.out.println("Failed to load " + e.getMessage());
            return null;
        }
        return updates;
    }
}