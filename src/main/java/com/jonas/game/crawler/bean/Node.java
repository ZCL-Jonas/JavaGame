package com.jonas.game.crawler.bean;

public class Node {
    public int x;
    public int y;
    public String dir;
    public Node(int x, int y, String dir) {
        this.x = x;
        this.y = y;
        this.dir  = dir;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + dir + ")";
    }

}
