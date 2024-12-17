package com.gabrieltelles.adventofcode2024.day12;

import java.util.HashMap;
import java.util.Map;

public class UnionFind {

    private final Map<Point, Point> parent;
    private final Map<Point, Integer> rank;

    public UnionFind() {
        parent = new HashMap<>();
        rank = new HashMap<>();
    }

    public Point find(Point p) {
        if (!parent.containsKey(p)) {
            parent.put(p, p);
            rank.put(p, 0);
        }
        if (parent.get(p) != p) {
            parent.put(p, find(parent.get(p)));
        }
        return parent.get(p);
    }

    public void union(Point p1, Point p2) {
        Point root1 = find(p1);
        Point root2 = find(p2);

        if (root1 != root2) {
            if (rank.get(root1) > rank.get(root2)) {
                parent.put(root2, root1);
            } else if (rank.get(root1) < rank.get(root2)) {
                parent.put(root1, root2);
            } else {
                parent.put(root2, root1);
                rank.put(root1, rank.get(root1) + 1);
            }
        }
    }
}
