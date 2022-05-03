package com.jonas.game.crawler.constant;

import java.awt.*;

public class SnakeConstant {
    public static final String[] MENUS = {
            "开始游戏",
            "继续游戏",
            "游戏帮助",
            "关于游戏",
            "退出游戏",
    };

    public static final String GAME_TITLE = "贪吃蛇";

    public static final String MENU_NAME = "菜单";

    public static final int FRAME_MAIN_WIDTH = 1200;

    public static final int FRAME_WIDTH = 850;

    public static final int FRAME_HEIGHT = 850;

    public static final int SPAN_25 = 25;

    public static final int SPAN_15 = 15;

    public static final int SPAN_35 = 35;

    public static final int MENU_BAR_HEIGHT = 100;

    public static final int TITLE_IMAGE_WIDTH = 850;

    public static final int SCREEN_W = Toolkit.getDefaultToolkit().getScreenSize().width;

    public static final int SCREEN_H = Toolkit.getDefaultToolkit().getScreenSize().height;

    public static final int FRAME_X = (SCREEN_W - FRAME_WIDTH) >> 1;

    public static final int FRAME_Y = (SCREEN_H - FRAME_HEIGHT) >> 1;

    public static final int GAME_MENU = 0;

    public static final int GAME_RUN = 1;

    public static final int GAME_CONTINUE = 2;

    public static final int GAME_HELP = 3;

    public static final int GAME_ABOUT = 4;

    public static final int GAME_OVER = 5;
}
