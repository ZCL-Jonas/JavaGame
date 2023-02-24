package com.jonas.game.huaweiOd;

import java.util.Scanner;

public class GouGuNum1 {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            solution(n, m);
        }
    }

    /**
     * 计算整数范围[n, m]内的毕达哥拉斯定理的数
     *
     * @param n n
     * @param m m
     */
    private static void solution(int n, int m) {
        int count = 0;
        for (int a = n; a < m - 1; a++) {
            for (int b = a + 1; b < m; b++) {
                for (int c = b + 1; c < m + 1; c++) {
                    if (relativelyPrime(a, b) &&
                        relativelyPrime(a, c) &&
                        relativelyPrime(b, c) &&
                        (a * a + b * b == c * c)) {
                        count++;
                        System.out.println(a + " " + b + " " + c);
                    }
                }
            }
        }
        if (count == 0) {
            System.out.println("NA");
        }
    }

    /**
     * 判断两个整数是否是互质
     *
     * @param a a
     * @param b b
     * @return true:互质, false:不互质
     */
    private static boolean relativelyPrime(int a, int b) {
        if (a == 1 && b == 1) {
            return false;
        }
        int min = Math.min(a, b);
        for (int i = 2; i <= min; i++) {
            if (a % i == 0 && b % i == 0) {
                return false;
            }
        }
        return true;
    }
}
