package com.jonas.game.crawler;

import com.jonas.game.crawler.bean.Food;
import com.jonas.game.crawler.bean.Node;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;

import static com.jonas.game.crawler.constant.SnakeConstant.*;
import static com.jonas.game.crawler.constant.SnakeErrorCode.*;

public class Snake {
    // 图片
    private ImageIcon upImage;
    private ImageIcon downImage;
    private ImageIcon leftImage;
    private ImageIcon rightImage;
    private ImageIcon bodyImage;
    private ImageIcon foodImage;

    // 方向常量
    private static final String UP = "up";
    private static final String DOWN = "down";
    private static final String LEFT = "left";
    private static final String RIGHT = "right";

    /**
     * 随机数
     */
    private final Random random = new Random();

    /**
     * 蛇头
     */
    private final Node head;

    /**
     * 食物
     */
    private Food food;

    // 用于记录蛇的身体
    private final LinkedList<Node> nodeList = new LinkedList<>();

    private SnakeListener snakeListener;

    private int length = 3;

    public Snake(SnakeListener snakeListener) {
        initImage();
        int x = randomX();
        int y = randomY();
        head = new Node(x, y, RIGHT);
        nodeList.add(new Node(x - SPAN_25, y, RIGHT));
        nodeList.add(new Node(x - SPAN_25 - SPAN_25, y, RIGHT));
        food = randomFood();
        this.snakeListener = snakeListener;
    }

    private int randomX() {
        return SPAN_25 * random.nextInt((TITLE_IMAGE_WIDTH - SPAN_25) / SPAN_25 - 1) + SPAN_25;
    }

    private int randomY() {
        return MENU_BAR_HEIGHT + SPAN_25 * random.nextInt(FRAME_HEIGHT / SPAN_25 - 5);
    }

    private void initImage() {
        upImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("image/up.png")));
        downImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("image/down.png")));
        leftImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("image/left.png")));
        rightImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("image/right.png")));
        bodyImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("image/body.png")));
        foodImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("image/food.png")));
    }

    // 随机生成食物
    private Food randomFood() {
        int x, y;
        boolean isFood;
        do {
            x = randomX();
            y = randomY();
            isFood = true;
            for (Node n : nodeList) {
                if (x == n.x && y == n.y) {
                    isFood = false;
                    break;
                }
            }
            if (head.x == x && head.y == y) {
                isFood = false;
            }
        } while (!isFood);

        return new Food(x, y);
    }

    // 修改蛇移动的方向
    public void keyEvent(int keyCode) {
        String dir = head.dir;
        switch (keyCode) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
            case KeyEvent.VK_8:
                head.dir = (!dir.equals(DOWN) && !dir.equals(UP)) ? UP : head.dir;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
            case KeyEvent.VK_2:
                head.dir = (!dir.equals(DOWN) && !dir.equals(UP)) ? DOWN : head.dir;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
            case KeyEvent.VK_4:
                head.dir = (!dir.equals(LEFT) && !dir.equals(RIGHT)) ? LEFT : head.dir;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
            case KeyEvent.VK_6:
                head.dir = (!dir.equals(LEFT) && !dir.equals(RIGHT)) ? RIGHT : head.dir;
                break;
        }
    }

    public void draw(Component component, Graphics graphics) {
        drawHead(component, graphics);
        eatFood();
        drawFood(component, graphics);
        drawBody(component, graphics);
    }

    private void drawFood(Component component, Graphics graphics) {
        // 画食物
        foodImage.paintIcon(component, graphics, food.x, food.y);
    }

    private void eatFood() {
        if (food.isEaten(head.x, head.y)) {
            // 食物被吃重新生成食物
            food = randomFood();
            if (snakeListener != null) {
                snakeListener.onSnakeLength(++length);
            }
        } else {
            // 不是食物移动一格需要删除最后一个点，这样可以造成移动的效果
            nodeList.removeLast();
        }
    }

    private void drawBody(Component component, Graphics graphics) {
        for (Node n : nodeList) {
            // 画蛇身体
            bodyImage.paintIcon(component, graphics, n.x, n.y);
        }
    }

    private void drawHead(Component component, Graphics graphics) {
        // 移动一格添加一个点
        nodeList.add(0, new Node(head.x, head.y, head.dir));
        switch (head.dir) {
            case UP:
                // 向上移动y减少SPAN_25，x不变
                head.y -= SPAN_25;
                if (head.y < MENU_BAR_HEIGHT && snakeListener != null) {
                    snakeListener.onSnakeError(ERROR_CODE_AGAINST_TOP_WALL, "撞top");
                    return;
                }
                // 画向上的蛇头
                upImage.paintIcon(component, graphics, head.x, head.y);
                break;
            case DOWN:
                // 向下移动y增加SPAN_25，x不变
                head.y += SPAN_25;
                if (head.y >= FRAME_HEIGHT && snakeListener != null) {
                    snakeListener.onSnakeError(ERROR_CODE_AGAINST_DOWN_WALL, "撞down");
                    return;
                }
                // 画向下的蛇头
                downImage.paintIcon(component, graphics, head.x, head.y);
                break;
            case LEFT:
                // 向左移动x减少SPAN_25，y不变
                head.x -= SPAN_25;
                if (head.x < 0 && snakeListener != null) {
                    snakeListener.onSnakeError(ERROR_CODE_AGAINST_LEFT_WALL, "撞left");
                    return;
                }
                // 画向左的蛇头
                leftImage.paintIcon(component, graphics, head.x, head.y);
                break;
            case RIGHT:
                // 向左移动x增加SPAN_25，y不变
                head.x += SPAN_25;
                if (head.x >= TITLE_IMAGE_WIDTH && snakeListener != null) {
                    snakeListener.onSnakeError(ERROR_CODE_AGAINST_RIGHT_WALL, "撞right");
                    return;
                }
                // 画向右的蛇头
                rightImage.paintIcon(component, graphics, head.x, head.y);
                break;
        }
    }
}
