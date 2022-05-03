package com.jonas.game.crawler.bean;

public class Food {
    public int x;
    public int y;
    public Food(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isEaten(int x, int y) {
        return this.x == x && this.y == y;
    }
}
