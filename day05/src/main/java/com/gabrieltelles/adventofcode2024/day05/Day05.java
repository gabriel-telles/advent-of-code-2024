package com.gabrieltelles.adventofcode2024.day05;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day05 {
    private static final String RULES_PATH = "day05/src/main/resources/rules.csv";
    private static final String RULES_DELIMITER = "\\|";

    private static final String UPDATES_PATH = "day05/src/main/resources/updates.csv";
    private static final String UPDATES_DELIMITER = ",";

    public static void main(String[] args) {
        SetMultimap<String, String> rules = getRules();
        List<List<String>> updates = getUpdates();
        if (rules == null || updates == null) return;
        System.out.println(rules.get("44"));
        System.out.println(updates.getFirst());
    }

    private static SetMultimap<String, String> getRules() {
        SetMultimap<String, String> rules = HashMultimap.create();
        try (var lines = Files.lines(Paths.get(RULES_PATH))) {
            lines.map(line -> line.split(RULES_DELIMITER))
                    .forEach(rule -> rules.put(rule[1], rule[0]));
        } catch (IOException e) {
            System.out.println("Failed to load " + e.getMessage());
            return null;
        }
        return rules;
    }

    private static List<List<String>> getUpdates() {
        List<List<String>> updates;
        try (var lines = Files.lines(Paths.get(UPDATES_PATH))) {
            updates = lines.map(line -> List.of(line.split(UPDATES_DELIMITER))).toList();
        } catch (IOException e) {
            System.out.println("Failed to load " + e.getMessage());
            return null;
        }
        return updates;
    }
}