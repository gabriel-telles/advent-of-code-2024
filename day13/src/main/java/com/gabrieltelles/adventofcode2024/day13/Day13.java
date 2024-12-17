package com.gabrieltelles.adventofcode2024.day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day13 {
    public static void main(String[] args) {
        List<Machine> machines = loadMachinesFromPath("day13/src/main/resources/input.txt");
        long tokensNeeded = Day13.calculateTokensNeeded(machines);
        System.out.println("Fewest tokens needed to win all possible prizes: " + tokensNeeded);
    }

    static long calculateTokensNeeded(List<Machine> machines) {
        long tokensNeeded = 0;
        for (var machine : machines) {
            tokensNeeded += calculateTokensForMachine(machine);
        }
        return tokensNeeded;
    }

    static long calculateTokensForMachine(Machine m) {
        long maxA = Math.min(m.prizeX()/m.ax(), m.prizeY()/m.ay());
        long maxB = Math.min(m.prizeX()/m.bx(), m.prizeY()/m.by());
        long tokens = Long.MAX_VALUE;

        for (long a = maxA; a >= 0; a--) {
            long b = 0;
            while (a * m.ax() + b * m.bx() <= m.prizeX() && a * m.ay() + b * m.by() <= m.prizeY() && b <= maxB) {
                if (winsPrize(a, b, m) && calculateTokens(a, b) < tokens) {
                    tokens = calculateTokens(a, b);
                    break;
                }
                b++;
            }

        }
        return (tokens == Long.MAX_VALUE) ? 0L : tokens;
    }

    private static boolean winsPrize(long buttonsA, long buttonsB, Machine m) {
        return (buttonsA * m.ax() + buttonsB * m.bx() == m.prizeX()) && (buttonsA * m.ay() + buttonsB * m.by() == m.prizeY());
    }

    private static long calculateTokens(long buttonsA, long buttonsB) {
        return buttonsA * 3 + buttonsB;
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