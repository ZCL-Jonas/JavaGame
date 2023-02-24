package com.jonas.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StrSort {

    public static void main(String[] args) {
        String input = "AbCGdeFG";
        char[] arrayCh = input.toCharArray();
        Arrays.sort(arrayCh);
        System.out.println(Arrays.toString(arrayCh));
        System.out.println(input.indexOf(arrayCh[4]));
    }


    private static void sort(String[] h, String[] v) {
        int index = 0;
        int count = 0;
        int[] team = new int[h.length];
        for (int i = 0; i < h.length; i++){
            team[i] = i + 1;

        }
        List<String> hList = new ArrayList(Arrays.asList(v));
        List<String> vList = new ArrayList(Arrays.asList(v));
        for (int i = 1; i < hList.size(); i++) {
            int h1 = Integer.parseInt(hList.get(i));
            int h2 = Integer.parseInt(hList.get(index));
            if (h1 == h2) {
                int v1 = Integer.parseInt(vList.get(i));
                for (int j = i - 1; j >= index; j--) {
                    int v2 = Integer.parseInt(vList.get(j));
                    if (v2 <= v1) {
                        vList.add(j + 1, vList.get(i));
                        vList.remove(i + 1);
                        int t = team[i];
                        break;
                    }
                }
            } else {
                index = i;
            }
        }
    }
}
