package com.jonas.game;

import java.util.HashMap;

public class PoplarReplant {

    public static void main(String[] args) {
        int count = 0;
        int xiaci = 1;
        String[] str = {"aabeebuu", "asdbuiodevauufgh"};
        int index = -1;
        int maxLen = 0;
        String yuanying = "aeiouAEIOU";
        for (int j = 0; j < str.length; j++) {
            for (int i = 0; i < str[j].length(); i++) {
                int ix = yuanying.indexOf(str[j].charAt(i));
                if (index == -1) {
                    if (ix != -1) {
                        index = i;
                    }
                    continue;
                }
                if (count == xiaci) {
                    if (ix == -1) {
                        maxLen = Math.max(str[j].substring(index, i).length(), maxLen);
                        count = 0;
                    }
                } else {
                    if (ix == -1) {
                        count++;
                    }
                }
                System.out.println(str[j].charAt(i) + " " + ix + " " + maxLen + " " + count);
            }
            System.out.println(maxLen);
        }
    }
    // count == n,
}
