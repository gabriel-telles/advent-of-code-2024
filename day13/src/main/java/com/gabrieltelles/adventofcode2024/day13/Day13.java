package com.gabrieltelles.adventofcode2024.day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day13 {
    public static void main(String[] args) {
        List<Machine> machines = loadMachinesFromPath("day13/src/main/resources/input.txt");
        int tokensNeeded = Day13.calculateTokensNeeded(machines);
        System.out.println("Fewest tokens needed to win all possible prizes: " + tokensNeeded);
    }

    static int calculateTokensNeeded(List<Machine> machines) {
        return 0;
    }

    static List<Machine> loadMachinesFromPath(String path) {
        List<Machine> machines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Button A:")) {
                    String[] aParts = line.split("X\\+|, Y\\+");
                    int ax = Integer.parseInt(aParts[1]);
                    int ay = Integer.parseInt(aParts[2]);

                    line = reader.readLine(); // Button B
                    String[] bParts = line.split("X\\+|, Y\\+");
                    int bx = Integer.parseInt(bParts[1]);
                    int by = Integer.parseInt(bParts[2]);

                    line = reader.readLine(); // Prize
                    String[] prizeParts = line.split("X=|, Y=");
                    int prizeX = Integer.parseInt(prizeParts[1]);
                    int prizeY = Integer.parseInt(prizeParts[2]);

                    machines.add(new Machine(ax, ay, bx, by, prizeX, prizeY));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return machines;
    }
}