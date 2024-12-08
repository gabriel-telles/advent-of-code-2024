package com.gabrieltelles.adventofcode2024.day05;

import com.google.common.collect.SetMultimap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class Day05Test {

    SetMultimap<String, String> rules;
    List<List<String>> updates;

    @BeforeEach
    void setup() {
        rules = Day05.loadRules("src/test/resources/exampleRules.csv");
        updates = Day05.loadUpdates("src/test/resources/exampleUpdates.csv");
        Assertions.assertNotNull(rules);
        Assertions.assertNotNull(updates);
    }

    @Test
    void shouldGetMiddlePageSum() {
        List<List<String>> validUpdates = updates.stream().filter(up -> Day05.isUpdateValid(up, rules)).toList();
        int middlePageSum = validUpdates.stream().mapToInt(up -> Integer.parseInt(up.get((up.size()-1)/2))).sum();

        Assertions.assertEquals(143, middlePageSum);
    }

    @Test
    void shouldFixInvalidUpdates() {
        List<List<String>> invalidUpdates = updates.stream().filter(up -> !Day05.isUpdateValid(up, rules)).toList();
        List<List<String>> fixedUpdates = Day05.fixInvalidUpdates(invalidUpdates, rules);
        int fixedInvalidMiddlePageSum = fixedUpdates.stream().mapToInt(Day05::getMiddleElement).sum();

        Assertions.assertEquals(123, fixedInvalidMiddlePageSum);
    }
}