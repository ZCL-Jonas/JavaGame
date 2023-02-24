package com.jonas.game.huaweiOd;

import java.util.Scanner;

public class TlvDecode {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String tag = scanner.nextLine();
            String source = scanner.nextLine();
            solution(tag, source);
        }
    }

    // 32 01 00 AE 90 02 00 01 02 30 03 00 AB 32 31 31 02 00 32 33 33 01 00 CC
    private static void solution(String tag, String source) {
        int p = 0;
        while (p < source.length()) {
            String curTag = source.substring(p, p + 2);
            String lenHex = source.substring(p + 6, p + 8) + source.substring(p + 3, p + 5); // 小端
            int lenDec = Integer.parseInt(lenHex, 16);
            if (tag.equals(curTag)) {
                System.out.println(source.substring(p + 9, p + 9 + lenDec * 3));
            }
            p += 9 + lenDec * 3;
            System.out.println(curTag + " " + lenHex + " " + lenDec + " " + p);
        }
    }
}
