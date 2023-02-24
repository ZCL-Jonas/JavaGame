package com.jonas.game.huaweiOd;

import java.util.Scanner;

public class DigitalDP {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String line = scanner.nextLine();
            int l = Integer.parseInt(line.split(" ")[0]);
            int r = Integer.parseInt(line.split(" ")[1]);
            int count = 0;
            for (int i = l; i < r + 1; i++) {
                if (!Integer.toBinaryString(i).contains("101")) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
