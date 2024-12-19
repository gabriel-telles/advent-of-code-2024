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

        List<Machine> modifiedMachines = new ArrayList<>();
        long prizeModification = 10000000000000L;
        for (var m : machines) {
            modifiedMachines.add(new Machine(m.ax(), m.ay(), m.bx(), m.by(), m.prizeX() + prizeModification, m.prizeY() + prizeModification));
        }
        long tokensNeededModified = Day13.calculateTokensNeeded(modifiedMachines);
        System.out.println("Fewest tokens needed to win all possible modified prizes: " + tokensNeededModified);
    }

    static long calculateTokensNeeded(List<Machine> machines) {
        long tokensNeeded = 0;
        for (var machine : machines) {
            tokensNeeded += calculateTokensForMachine(machine);
        }
        return tokensNeeded;
    }

    static long calculateTokensForMachine(Machine m) {
        // Let's view the machine as a 2x2 system of equations
        long determinant = m.ax() * m.by() - m.ay() * m.bx();
        if (determinant != 0) {
            // guaranteed single solution
            long a = (m.prizeX() * m.by() - m.prizeY() * m.bx()) / determinant;
            long b = (m.prizeY() * m.ax() - m.prizeX() * m.ay()) / determinant;

            return winsPrize(a, b, m) ? calculateTokens(a, b) : 0;
        } else {
            return 0;
        }
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