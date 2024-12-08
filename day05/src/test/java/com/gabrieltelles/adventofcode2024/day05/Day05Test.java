package com.gabrieltelles.adventofcode2024.day05;

import com.google.common.collect.SetMultimap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Day05Test {

    @Test
    void shouldGetMiddlePageSum() {
        SetMultimap<String, String> rules = Day05.loadRules("src/test/resources/exampleRules.csv");
        List<List<String>> updates = Day05.loadUpdates("src/test/resources/exampleUpdates.csv");
        if (rules == null || updates == null) return;

        List<List<String>> validUpdates = updates.stream().filter(up -> Day05.isUpdateValid(up, rules)).toList();
        int middlePageSum = validUpdates.stream().mapToInt(up -> Integer.parseInt(up.get((up.size()-1)/2))).sum();

        Assertions.assertEquals(143, middlePageSum);
    }
}