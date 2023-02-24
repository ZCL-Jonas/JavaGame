package com.jonas.game;

public class MaxCommonStr {
    public static void main(String[] args) {
        String a = "abcdefghijklmnop";
        String b = "abcsafjklmnopqrstuvw";
        System.out.println(getMaxCommonSubStr(a, b));
    }

    private static String getMaxCommonSubStr(String a, String b) {
        int max = 0;
        String str = "";
        for (int i = a.length() - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (b.contains(a.substring(j, i))) {
                    String subStr = a.substring(j, i);
                    if (subStr.length() > max) {
                        str = subStr;
                        max = subStr.length();
                    }
                }
            }
        }
        return str;
    }
}
