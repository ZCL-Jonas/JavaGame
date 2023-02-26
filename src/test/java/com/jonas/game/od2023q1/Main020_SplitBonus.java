package com.jonas.game.od2023q1;

import java.util.Arrays;
import java.util.Scanner;

public class Main020_SplitBonus {
    /**
     * 输入
     * 3
     * 2 10 3
     * 输出 8 10 3
     *
     * @param args
     */
    public static void main (String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            scanner.nextLine();
            Integer[] randNums = Arrays.stream(scanner.nextLine().split(" "))
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);
            System.out.println(solution(randNums));
        }
    }

    /**
     * 1. 每个人随机抽取一个数字。按照工号的顺序往后排列
     * 2. 遇到第一个数字比自己数字大: 前面的员工奖金 = 距离 * 数字差值
     * 3. 遇不到比自己数字大的: 奖金 = 给自己分配随机数数量
     *
     * @param randNums 随机数列表
     * @return 每位员工分到的奖金数量
     */
    private static String solution(Integer[] randNums) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < randNums.length; i++) {
            int k = i;
            for (int j = i + 1; j < randNums.length; j++) {
                if (randNums[i] < randNums[j]) {
                    k = j;
                    break;
                }
            }
            if (k > i) {
                stringBuilder.append((randNums[k] - randNums[i]) * (k - i)).append(" ");
            } else {
                stringBuilder.append(randNums[i]).append(" ");
            }
        }
        return stringBuilder.toString();
    }
}
