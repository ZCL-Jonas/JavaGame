package com.jonas.game.crawler;

public interface SnakeListener {
    void onSnakeLength(int length);
    void onSnakeError(int errorCode, String errMrg);
}
