package com.gabrieltelles.adventofcode2024.day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day14 {
    private static final int WIDTH = 101;
    private static final int HEIGHT = 103;

    public static void main(String[] args) {
        List<Robot> robots = loadRobotsFromPath("day14/src/main/resources/input.txt", WIDTH, HEIGHT);
        for (int i = 0; i < 100; i++) {
            for (var robot : robots) {
                robot.move();
            }
        }
        int[][] bathroom = new int[WIDTH][HEIGHT];
        for (var robot : robots) {
            bathroom[robot.posX()][robot.posY()]++;
        }

        int midRow = WIDTH / 2;
        int midCol = HEIGHT / 2;

        // Quadrant sums
        long topLeftSum = 0, topRightSum = 0, bottomLeftSum = 0, bottomRightSum = 0;

        // Calculate sums for each quadrant
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (i < midRow && j < midCol) {
                    topLeftSum += bathroom[i][j];
                } else if (i < midRow && j > midCol) {
                    topRightSum += bathroom[i][j];
                } else if (i > midRow && j < midCol) {
                    bottomLeftSum += bathroom[i][j];
                } else if (i > midRow && j > midCol) {
                    bottomRightSum += bathroom[i][j];
                }
            }
        }

        long safetyFactor = topLeftSum * topRightSum * bottomLeftSum * bottomRightSum;
        System.out.println(safetyFactor);
    }

    public static List<Robot> loadRobotsFromPath(String path, int width, int height) {
        try {
            List<Robot> result = new ArrayList<>();
            var lines = Files.readAllLines(Path.of(path));
            for (var line : lines) {
                String[] parts = line.split(" ");

                String[] position = parts[0].substring(2).split(",");
                String[] velocity = parts[1].substring(2).split(",");

                int posX = Integer.parseInt(position[0]);
                int posY = Integer.parseInt(position[1]);
                int velX = Integer.parseInt(velocity[0]);
                int velY = Integer.parseInt(velocity[1]);
                result.add(new Robot(posX, posY, velX, velY, width, height));
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}