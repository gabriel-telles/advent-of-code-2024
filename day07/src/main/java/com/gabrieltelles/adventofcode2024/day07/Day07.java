package com.gabrieltelles.adventofcode2024.day07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.LongBinaryOperator;
import java.util.stream.Stream;

public class Day07 {
    private static final String INPUT_PATH = "day07/src/main/resources/input.txt";

    public static void main(String[] args) {
        List<long[]> equations = loadEquationsFromPath(INPUT_PATH);
        long sum = getSumOfAcceptableEquations(equations);
        System.out.println(sum);
    }

    static long getSumOfAcceptableEquations(List<long[]> equations) {
        return equations.stream().filter(Day07::isAcceptable).mapToLong(array -> array[0]).sum();
    }

    static boolean isAcceptable(long[] equation) {
        int numOperations = equation.length - 2;
        Operation[] operations = new Operation[numOperations];
        Arrays.fill(operations, Operation.ADD);
        for (int i = 0; i < (1 << numOperations); i++) {
            if (evaluatesTo(equation, operations)) {
                return true;
            }
            operations = next(operations);
        }
        return false;
    }

    static Operation[] next(Operation[] current) {
        Operation[] next = Arrays.copyOf(current, current.length);
        for (int i = current.length - 1; i >= 0; i--) {
            if (next[i] == Operation.ADD) {
                next[i] = Operation.MULTIPLY;
                break;
            } else {
                next[i] = Operation.ADD;
            }
        }
        return next;
    }

    static boolean evaluatesTo(long[] equation, Operation[] operations) {
        long expectedResult = equation[0];
        int length = operations.length;

        long result = equation[1];
        for (int i = 0; i < length; i++) {
            result = operations[i].apply(result, equation[i+2]);
        }
        return result == expectedResult;
    }

    static List<long[]> loadEquationsFromPath(String path) {
        List<long[]> equations;
        String delimiter = " |: ";
        try (var lines = Files.lines(Paths.get(path))) {
            equations = lines.map(line -> Stream.of(line.split(delimiter)).mapToLong(Long::parseLong).toArray()).toList();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load " + e.getMessage(), e);
        }
        return equations;
    }

    enum Operation {
        ADD("+", Long::sum),
        MULTIPLY("*", (x, y) -> x * y);

        private final String symbol;
        private final LongBinaryOperator op;

        Operation(String symbol, LongBinaryOperator op) {
            this.symbol = symbol;
            this.op = op;
        }

        @Override
        public String toString() {
            return symbol;
        }

        public long apply(long left, long right) {
            return op.applyAsLong(left, right);
        }
    }
}