package com.jonas.game.od2023q1;

import java.util.Scanner;

public class Main021_NumAddSubGame {
    /**
     * 输入 1 10 5 2
     * 输出 1
     *
     * 输入 11 33 4 10
     * 输出 2
     *
     * @param args
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int s = scanner.nextInt();
            int t = scanner.nextInt();
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            System.out.println(solution(s, t, a, b));
        }
    }

    /**
     * 1. 只使用加法或者减法
     * 2. b没有使用次数限制
     * 3. 数字s一定能变成数字t
     *
     * @param s s
     * @param t t
     * @param a a
     * @param b b
     * @return 使用a最小次数
     */
    private static int solution(int s, int t, int a, int b) {
        int df = Math.abs(s - t); // s,t差值取绝对值
        int count1 = 0;
        int tamp = df;
        StringBuilder sb1 = new StringBuilder("sub ").append(tamp % b).append(" ");
        // 使用tamp是否是b的倍数做判断条件，tamp循环减a
        // tamp是b的倍数得到减a的最小次数
        while (tamp % b != 0) {
            tamp -= a;
            count1++;
            sb1.append("tamp:").append(tamp).append(" ").append(tamp % b).append(" ").append(count1).append("\n");
        }
        System.out.println(sb1.toString());
        tamp = df;
        int count2 = 0;
        StringBuilder sb2 = new StringBuilder("add ").append(tamp % b).append(" ");
        // 使用tamp是否是b的倍数做判断条件，tamp循环加a
        // tamp是b的倍数得到加a的最小次数
        while (tamp % b != 0) {
            tamp += a;
            count2++;
            sb2.append("tamp:").append(tamp).append(" ").append(tamp % b).append(" ").append(count2).append("\n");
        }
        System.out.println(sb2.toString());
        // 加a最小次数和减a最小次数取最小
        return Math.min(count1, count2);
    }
}
