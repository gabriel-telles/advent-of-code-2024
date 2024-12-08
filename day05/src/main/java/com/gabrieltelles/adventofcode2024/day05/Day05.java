package com.gabrieltelles.adventofcode2024.day05;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

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

        List<List<String>> invalidUpdates = updates.stream().filter(up -> !isUpdateValid(up, rules)).toList();
        List<List<String>> fixedUpdates = fixInvalidUpdates(invalidUpdates, rules);
        int fixedInvalidMiddlePageSum = fixedUpdates.stream().mapToInt(Day05::getMiddleElement).sum();
        System.out.println("Middle page sum for Invalid and Fixed updates: " + fixedInvalidMiddlePageSum);
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

    static List<List<String>> fixInvalidUpdates(List<List<String>> invalidUpdates, SetMultimap<String, String> rules) {
        List<List<String>> fixedUpdates = new LinkedList<>();
        for (var update : invalidUpdates) {
            List<String> fixedUpdate = new ArrayList<>(update);
            while (!isUpdateValid(fixedUpdate, rules)) {
                for (int i = fixedUpdate.size() - 1; i >= 0; i--) {
                    String element = fixedUpdate.get(i);
                    boolean flag = true;
                    for (int j = i; j  < fixedUpdate.size(); j++) {
                        if (rules.get(element).contains(fixedUpdate.get(j)) && flag) {
                            Collections.swap(fixedUpdate, i, j);
                            flag = false;
                        }
                    }
                }
            }

            fixedUpdates.add(fixedUpdate);
        }
        return fixedUpdates;
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