package com.jonas.game.huaweiOd;

import java.util.Scanner;

public class TlvDecode1 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String tag = scanner.nextLine();
            String source = scanner.nextLine();
            solution(tag, source);
        }
    }

    private static void solution(String tag, String source) {
        int p = 0;
        while (p < source.length()) {
            String currentTag = source.substring(p, p + 2);
            String lenHEX = source.substring(p + 6, p + 8) + source.substring(p + 3, p + 5);
            int lenDex = Integer.parseInt(lenHEX);
            if (tag.equals(currentTag)) {
                System.out.println(source.substring(p + 9, p + 9 + lenDex * 3));
            }
            p += 9 + lenDex * 3;
        }
    }
}
