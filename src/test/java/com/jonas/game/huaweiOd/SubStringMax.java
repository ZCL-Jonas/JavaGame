package com.jonas.game.huaweiOd;

import java.util.Scanner;

public class SubStringMax {
    public static void main(String[] args) {
//        try (Scanner scanner = new Scanner(System.in)){
//            String line1 = scanner.nextLine();
//            String line2 = scanner.nextLine();
//            String maxStr = "";
//            int k = 0;
//            for (int i = 0; i < line1.length(); i++ ) {
//                for (int j = i + 1; j < line1.length(); j++) {
//                    String str = line1.substring(i,j);
//                    if (line2.contains(str)) {
//                        int len = str.length();
//                        if (k < len) {
//                            k = len;
//                            maxStr = str;
//                        }
//                    }
//                }
//            }
//            System.out.println(maxStr + " " + k);
//        }
        try(Scanner scanner = new Scanner(System.in)) {
            String line1 = scanner.nextLine();
            String line2 = scanner.nextLine();
            String maxSubString = "";
            for (int i = 0; i < line1.length(); i++) {
                for (int j = i + 1; j < line1.length() + 1; j++) {
                    String sub = line1.substring(i, j);
                    if (line2.contains(sub)) {
                        if (maxSubString.length() < sub.length()) {
                            maxSubString = sub;
                        }
                    }
                }
            }
            System.out.println(maxSubString);
        }
    }
}
