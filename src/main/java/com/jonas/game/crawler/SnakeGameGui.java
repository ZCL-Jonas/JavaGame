package com.jonas.game.crawler;

import sun.font.FontDesignMetrics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Objects;

import static com.jonas.game.crawler.constant.FontConstant.*;
import static com.jonas.game.crawler.constant.SnakeConstant.*;

public class SnakeGameGui extends Frame implements Runnable {
    private BufferedImage image = new BufferedImage(FRAME_WIDTH, FRAME_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
    private int uiState = GAME_MENU;
    private int menuIndex;
    private ImageIcon title;
    private Snake snake;
    private int length = 0;
    private boolean isRunning = true;
    private Thread snakeThread = new Thread(this);
    private SnakeListener snakeListener = new SnakeListener() {
        @Override
        public void onSnakeLength(int length) {
            SnakeGameGui.this.length = length;
        }

        @Override
        public void onSnakeError(int errorCode, String errMrg) {
            isRunning = false;
            snakeThread.interrupt();
            showDialog("游戏", "Game Over!!");
        }
    };

    public SnakeGameGui() {
        init();
        addListener();
        URL url = Objects.requireNonNull(
                getClass().getClassLoader().getResource("image/title.jpg"));
        title = new ImageIcon(url);
        new Thread(this).start();
    }

    private void init() {
        setTitle(GAME_TITLE);
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocation(FRAME_X, FRAME_Y);
        setVisible(true);
    }

    private void addListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (uiState == GAME_MENU) {
                    int height = FontDesignMetrics.getMetrics(FONT_SONG_PLAIN_18).getHeight();
                    for (int i = 0; i < MENUS.length; i++) {
                        int wordWidth = getWordWidth(FONT_SONG_PLAIN_18, MENUS[i]);
                        int x = (FRAME_WIDTH - wordWidth) >> 1;
                        int y = getMenuHeight(FONT_SONG_PLAIN_18) + getMenuDIS(FONT_SONG_PLAIN_18) * i;
                        int x1 = x + wordWidth;
                        int y1 = y + height;
                        if (x <= e.getX() && e.getX() <= x1 && (y - height) <= e.getY() && e.getY() <= y1) {
                            menuIndex = i;
                            keyEnterEvent();
                            break;
                        }
                    }
                } else if (uiState == GAME_RUN || uiState == GAME_CONTINUE) {
                    int wordWidth = getWordWidth(FONT_YA_HEI_PLAIN_18, MENU_NAME);
                    int height = FontDesignMetrics.getMetrics(FONT_YA_HEI_PLAIN_18).getHeight();
                    int x = SPAN_15;
                    int y = SPAN_35 + getInsets().top;
                    int x1 = x + wordWidth;
                    int y1 = y + height;
                    if (x <= e.getX() && e.getX() <= x1 && (y - height) <= e.getY() && e.getY() <= y1) {
                        uiState = GAME_MENU;
                        menuIndex = 1;
                    }
                }
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                if (uiState == GAME_MENU) {
                    int height = FontDesignMetrics.getMetrics(FONT_SONG_PLAIN_18).getHeight();
                    for (int i = 0; i < MENUS.length; i++) {
                        int wordWidth = getWordWidth(FONT_SONG_PLAIN_18, MENUS[i]);
                        int x = (FRAME_WIDTH - wordWidth) >> 1;
                        int y = getMenuHeight(FONT_SONG_PLAIN_18) + getMenuDIS(FONT_SONG_PLAIN_18) * i;
                        int x1 = x + wordWidth;
                        int y1 = y + height;
                        if (x <= e.getX() && e.getX() <= x1 && (y - height) <= e.getY() && e.getY() <= y1) {
                            menuIndex = i;
                            break;
                        }
                    }
                }
            }
        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                int keyCode = e.getKeyCode();
                switch (uiState) {
                    case GAME_MENU:
                        keyEventMenu(keyCode);
                        break;
                    case GAME_RUN:
                    case GAME_CONTINUE:
                        keyEventGame(keyCode);
                        break;
                }
            }
        });
    }

    private void keyEventGame(int keyCode) {
        if (uiState == GAME_RUN || snake == null) {
            snake = new Snake(snakeListener);
        }
        snake.keyEvent(keyCode);
    }

    private void keyEventMenu(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                menuIndex--;
                menuIndex = (menuIndex < 0) ? (MENUS.length - 1) : menuIndex;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                menuIndex++;
                menuIndex = (menuIndex > MENUS.length - 1) ? 0 : menuIndex;
                break;
            case KeyEvent.VK_ENTER:
                keyEnterEvent();
                break;
        }
    }

    private void keyEnterEvent() {
        uiState = menuIndex + 1;
        switch (uiState) {
            case GAME_RUN:
                uiState = GAME_CONTINUE;
                menuIndex = 1;
                snake = new Snake(snakeListener);

                break;
            case GAME_CONTINUE:

                break;
            case GAME_HELP:
                uiState = GAME_MENU;
                showDialog("帮助", "");
                break;
            case GAME_ABOUT:
                break;
            case GAME_OVER:
                System.exit(0);
                break;
        }
    }

    @Override
    public void update(Graphics graphics) {
        Graphics gh = image.getGraphics();
        switch (uiState) {
            case GAME_MENU:
                drawMenu(gh);
                break;
            case GAME_RUN:
            case GAME_CONTINUE:
                drawGame(gh);
                break;
        }
        graphics.drawImage(image, 0, 0, null);
    }

    private void drawGame(Graphics gh) {
        gh.setColor(Color.BLACK);
        gh.fillRect(0, 0, TITLE_IMAGE_WIDTH, FRAME_HEIGHT);
        title.paintIcon(this, gh, 0, getInsets().top);

        gh.setFont(FONT_YA_HEI_PLAIN_18);
        gh.setColor(Color.WHITE);
        gh.drawString("积分：" + length * 10, TITLE_IMAGE_WIDTH - 8 * SPAN_15, SPAN_35 + getInsets().top);
        gh.drawString(MENU_NAME, SPAN_15, SPAN_35 + getInsets().top);

        if (snake == null) {
            snake = new Snake(snakeListener);
        }
        snake.draw(this, gh);
    }

    private void showDialog(String title, String content) {
        JDialog jDialog = new JDialog();
        jDialog.setTitle(title);
        jDialog.setLayout(new GridLayout(1, 1, 5, 5));
        jDialog.setSize(250, 150);
        JLabel jLabel = new JLabel(content, JLabel.CENTER);
        jLabel.setFont(FONT_YA_HEI_PLAIN_18);
        jDialog.add(jLabel);
        jDialog.setAlwaysOnTop(true);
        jDialog.setLocationRelativeTo(null);
        jDialog.setModal(true);
        jDialog.setResizable(false);
        jDialog.setVisible(true);
    }

    private void drawMenu(Graphics gh) {
        gh.setColor(Color.BLACK);
        gh.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        gh.setFont(FONT_SONG_BOLD_24);
        for (int i = 0; i < MENUS.length; i++) {
            if (i == menuIndex) {
                gh.setColor(Color.RED);
            } else {
                gh.setColor(Color.WHITE);
            }
            int wordWidth = getWordWidth(FONT_SONG_PLAIN_18, MENUS[i]);
            int x = (FRAME_WIDTH - wordWidth) >> 1;
            int y = getMenuHeight(FONT_SONG_PLAIN_18) + getMenuDIS(FONT_SONG_PLAIN_18) * i;
            gh.drawString(MENUS[i], x, y);
        }
    }

    private int getMenuDIS(Font font) {
        return getWordHeight(font) + SPAN_25;
    }

    private int getMenuHeight(Font font) {
        int wordHeights = getWordHeight(font) * MENUS.length;
        int wordSpans = SPAN_25 * (MENUS.length - 1);
        return (FRAME_HEIGHT - wordHeights - wordSpans) >> 1;
    }

    private int getWordHeight(Font font) {
        return FontDesignMetrics.getMetrics(font).getHeight();
    }

    private int getWordWidth(Font font, String word) {
        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
        int width = 0;
        for (int i = 0; i < word.length(); i++) {
            width += metrics.charWidth(word.charAt(i));
        }
        return width;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                this.repaint();
                int sleepTime = (uiState == GAME_RUN || uiState == GAME_CONTINUE) ? speedLarge : speedMedium;
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int speedLarge = 100;
    private int speedMedium = 1000;
    private int speedNormal = 1200;
    private int speedSmall = 1500;

}
