package com.gabrieltelles.adventofcode2024.day06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PuzzleMap {

    public enum MovementState {
        MOVABLE,
        FINISH,
        LOOPED
    }

    private static final char OBSTACLE = '#';
    private static final char VISITED = 'X';
    private static final char OPEN = '.';

    private final char[][] map;
    private final Guard guard;

    private final Map<int[], Character> guardPath = new HashMap<>();

    private PuzzleMap(char[][] map, Guard guard) {
        this.map = map;
        this.guard = guard;
    }

    public int getHeight() {
        return map.length;
    }

    public int getWidth() {
        return map[0].length;
    }

    public MovementState checkMovementState() {
        var nextPosition = guard.getNextPosition();
        if (isOutOfBounds(nextPosition)) {
            return MovementState.FINISH;
        }
        if (isInLoop()) {
            return MovementState.LOOPED;
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
                guardPath.put(guard.getPosition(), guard.getOrientation());
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
        int height = map.length;
        int width = map[0].length;
        return position[0] < 0 || position[0] >= height || position[1] < 0 || position[1] >= width;
    }

    private boolean isInLoop() {
        if (charAt(guard.getPosition()) != VISITED) {
            return false;
        }
        return guardPath.get(guard.getPosition()) == guard.getOrientation();
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

    public PuzzleMap getCopyWithAdditionalObstacle(int[] obstaclePosition) {
        if (charAt(obstaclePosition) != OPEN) {
            return null;
        }
        char[][] mapCopy = deepCopy(this.map);
        mapCopy[obstaclePosition[0]][obstaclePosition[1]] = OBSTACLE;
        Guard guard = Guard.from(mapCopy);
        return new PuzzleMap(mapCopy, guard);
    }

    private static char[][] deepCopy(char[][] original) {
        return Arrays.stream(original)
                .map(char[]::clone)
                .toArray(char[][]::new);
    }
}
