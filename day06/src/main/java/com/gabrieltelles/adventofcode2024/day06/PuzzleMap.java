package com.gabrieltelles.adventofcode2024.day06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class PuzzleMap {

    public enum MovementState {
        MOVABLE,
        FINISH,
    }

    private static final char OBSTACLE = '#';
    private static final char VISITED = 'X';

    private final char[][] map;
    private final int height;
    private final int width;
    private final Guard guard;

    private PuzzleMap(char[][] map, Guard guard) {
        this.map = map;
        this.height = map.length;
        this.width = map[0].length;
        this.guard = guard;
    }

    public MovementState checkMovementState() {
        var nextPosition = guard.getNextPosition();
        if (isOutOfBounds(nextPosition)) {
            return MovementState.FINISH;
        }
        return MovementState.MOVABLE;
    }

    public void moveGuard() {
        MovementState state = checkMovementState();
        if (state == MovementState.FINISH) {
            walkedOn(guard.getPosition());
            return;
        }

        if (state == MovementState.MOVABLE) {
            if (charAt(guard.getNextPosition()) == OBSTACLE) {
                guard.changeOrientation();
            } else {
                walkedOn(guard.getPosition());
                guard.moveForward();
            }
        }
        updateGuard(guard);
    }

    private void updateGuard(Guard guard) {
        var guardPosition = guard.getPosition();
        map[guardPosition[0]][guardPosition[1]] = guard.getOrientation();
    }

    private boolean isOutOfBounds(int[] position) {
        if (position.length != 2) {
            throw new IllegalArgumentException("Position must be a int[] of size 2");
        }
        return position[0] < 0 || position[0] >= height || position[1] < 0 || position[1] >= width;
    }

    private char charAt(int[] position) {
        if (position.length != 2) {
            throw new IllegalArgumentException("Position must be a int[] of size 2");
        }
        return map[position[0]][position[1]];
    }

    private void walkedOn(int[] position) {
        if (charAt(position) == OBSTACLE) {
            throw new IllegalArgumentException("Cannot walk over obstacles");
        }
        map[position[0]][position[1]] = VISITED;
    }

    public int countVisitedPositions() {
        return (int) Arrays.stream(map)
                .flatMapToInt(row -> new String(row).chars())
                .filter(c -> c == VISITED)
                .count();
    }

    public static PuzzleMap from(String path) {
        char[][] map = loadMapFromPath(path);
        Guard guard = Guard.from(map);
        return new PuzzleMap(map, guard);
    }

    private static char[][] loadMapFromPath(String path) {
        char[][] map;
        try (var lines = Files.lines(Paths.get(path))) {
            List<String> tempList = lines.toList();
            map = new char[tempList.size()][];
            for (int i = 0; i < tempList.size(); i++) {
                map[i] = tempList.get(i).toCharArray();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load " + e.getMessage(), e);
        }
        return map;
    }
}
