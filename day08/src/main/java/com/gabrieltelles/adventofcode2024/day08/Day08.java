package com.gabrieltelles.adventofcode2024.day08;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Day08 {
    static final char EMPTY = '.';
    static final char ANTI_NODE = '#';

    private static final String INPUT_PATH = "day08/src/main/resources/input.txt";
    public static void main(String[] args) {
        char[][] rawAntennas = loadChar2DArrayFromPath(INPUT_PATH);

        // Part One
        SetMultimap<Character, Point> antennas = getAntennas(rawAntennas);
        char[][] antiNodes = getEmptyBoard(rawAntennas.length);
        populatePartOne(antennas, antiNodes);
        System.out.println("Part one: " + countOccurrences(antiNodes));

        // Part Two
        SetMultimap<Character, Point> antennas2 = getAntennas(rawAntennas);
        char[][] antiNodes2 = getEmptyBoard(rawAntennas.length);
        populatePartTwo(antennas2, antiNodes2);
        System.out.println("Part two: " + countOccurrences(antiNodes2));
    }

    static void populatePartOne(SetMultimap<Character, Point> antennas, char[][] antiNodes) {
        for (var frequency : antennas.keySet()) {
            for (Point first : antennas.get(frequency)) {
                for (Point second : antennas.get(frequency)) {
                    if (first != second) {
                        Point antiNode = getAntiNode(first, second);
                        if (isInBounds(antiNode, antiNodes)) {
                            antiNodes[antiNode.row()][antiNode.col()] = ANTI_NODE;
                        }
                    }
                }
            }
        }
    }

    static void populatePartTwo(SetMultimap<Character, Point> antennas, char[][] antiNodes) {
        for (var frequency : antennas.keySet()) {
            for (Point first : antennas.get(frequency)) {
                for (Point second : antennas.get(frequency)) {
                    antiNodes[first.row()][first.col()] = ANTI_NODE;
                    if (first != second) {
                        Point antiNode = getAntiNode(first, second);
                        Point previous = second;
                        Point temp;
                        while (isInBounds(antiNode, antiNodes)) {
                            antiNodes[antiNode.row()][antiNode.col()] = ANTI_NODE;
                            temp = getAntiNode(previous, antiNode);
                            previous = antiNode;
                            antiNode = temp;
                        }
                    }
                }
            }
        }
    }

    static Point getAntiNode(Point first, Point second) {
        int deltaRow = second.row() - first.row();
        int deltaCol = second.col() - first.col();

        return new Point(second.row() + deltaRow, second.col() + deltaCol);
    }

    static char[][] getEmptyBoard(int sideLength) {
        char[][] board = new char[sideLength][sideLength];
        for (char[] row : board) {
            Arrays.fill(row, EMPTY);
        }
        return board;
    }

    static SetMultimap<Character, Point> getAntennas(char[][] rawAntennas) {
        SetMultimap<Character, Point> antennas = HashMultimap.create();
        for (int row = 0; row < rawAntennas.length; row++) {
            for (int col = 0; col < rawAntennas[0].length; col++) {
                if (rawAntennas[row][col] != EMPTY) {
                    antennas.put(rawAntennas[row][col], new Point(row, col));
                }
            }
        }
        return antennas;
    }

    static boolean isInBounds(Point p, char[][] antiNodes) {
        int height = antiNodes.length;
        int width = antiNodes[0].length;
        return p.row() >= 0 && p.row() < height && p.col() >= 0 && p.col() < width;
    }

    static int countOccurrences(char[][] array) {
        int count = 0;
        for (char[] row : array) {
            for (char c : row) {
                if (c == Day08.ANTI_NODE) {
                    count++;
                }
            }
        }
        return count;
    }

    static char[][] loadChar2DArrayFromPath(String path) {
        char[][] antennas;
        try (var lines = Files.lines(Paths.get(path))) {
            List<String> tempList = lines.toList();
            antennas = new char[tempList.size()][];
            for (int i = 0; i < tempList.size(); i++) {
                antennas[i] = tempList.get(i).toCharArray();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load " + e.getMessage(), e);
        }
        return antennas;
    }
}