package com.jonas.game.huaweiOd;

import java.util.Scanner;

public class GouGuNum {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            solution(n, m);
        }
    }

    private static void solution(int n, int m) {
        int count = 0;
        for (int a = n; a < m - 1; a++) {
            for (int b = a + 1; b < m; b++) {
                for (int c = b - 1; c < m + 1; c++) {
                    if (relativePrime(a, b) &&
                        relativePrime(b, c) &&
                        relativePrime(c, a) &&
                        (a * a + b * b == c * c)) {
                        count++;
                        System.out.println(a + " " + b + " " + c);
                    }
                }
            }
        }
        if (count == 0) {
            System.out.println("Na");
        }
    }

    private static boolean relativePrime(int a, int b) {
        if (a == 1 && b  == 1) {
            return false;
        }
        int min = Math.min(a, b);
        for (int i = 2; i < min; i++) {
            if (a % i == 0 && b % i == 0) {
                return false;
            }
        }
        return true;
    }
}
