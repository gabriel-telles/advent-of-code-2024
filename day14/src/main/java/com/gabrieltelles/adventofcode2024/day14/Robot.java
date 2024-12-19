package com.gabrieltelles.adventofcode2024.day14;

public class Robot {
    private int posX;
    private int posY;
    private final int velX;
    private final int velY;
    private final int width;
    private final int height;

    public Robot(int posX, int posY, int velX, int velY, int width, int height) {
        this.posX = posX;
        this.posY = posY;
        this.velX = velX;
        this.velY = velY;
        this.width = width;
        this.height = height;
    }

    public int posX() {
        return posX;
    }

    public int posY() {
        return posY;
    }

    public void move() {
        posX = (posX + velX) % width;
        if (posX < 0) {
            posX += width;
        }
        posY = (posY + velY) % height;
        if (posY < 0) {
            posY += height;
        }
    }
}
