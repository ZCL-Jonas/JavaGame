package com.jonas.game;

import java.util.*;

public class NumOfAnimals {

    public static void main(String[] args) {

        String[] Nnums = {"4,2", "6,3,2", "3,9,6,4", "5"};
        List<String> numList = new ArrayList<>();
        int len = 0;
        int size = 0;
        do {
            len = Nnums.length;
            numList.clear();
            duank(Nnums, numList);
            size = numList.size();
            Nnums = new String[size];
            numList.toArray(Nnums);
            System.out.println(Arrays.toString(Nnums));
        } while (size != len);
//        System.out.println(Arrays.toString(Nnums));
    }

    private static int minNumOfFrogs(String croakOfFrogs) {
        int c = 0;
        int r = 0;
        int o = 0;
        int a = 0;
        int k = 0;
        int re = 0;
        boolean flag = true;
        for (int i = 0; i < croakOfFrogs.length(); i++) {
            if (croakOfFrogs.charAt(i) == 'c') c++;
            if (croakOfFrogs.charAt(i) == 'r') r++;
            if (croakOfFrogs.charAt(i) == 'o') o++;
            if (croakOfFrogs.charAt(i) == 'a') a++;
            re = Math.max(re, c);
            if (croakOfFrogs.charAt(i) == 'k') {
                k++;
                if (c >= r && r >= o && o >= a && a >= k) {
                    c--;
                    r--;
                    o--;
                    a--;
                    k--;
                }
            }
            if (!(c >= r && r >= o && o >= a && a >= k)) {
                flag = false;
                break;
            }
        }
        if (c != 0 || r != 0 || o != 0 || a != 0 || k != 0) {
            flag = false;
        }
        if (flag) {
            return re;
        }
        return -1;
    }

    // {"4,2", "6,3,2", "3,9,6,4", "5"};
    public static void duank(String[] Nnums, List<String> numList) {
        for (int i = 0; i < Nnums.length; i++) {
            if (!Nnums[i].contains(",")) {
                continue;
            }
            String num = Nnums[i];
            for (int j = i + 1; j < Nnums.length; j++) {
                if (!Nnums[j].contains(",")) {
                    continue;
                }
                if (isTurn(Nnums[i], Nnums[j])) {
                    Nnums[i] = newNum(Nnums[i], Nnums[j]);
                    Nnums[j] = "";
                }
            }
        }
        for (int i = 0; i < Nnums.length; i++) {
            if (Nnums[i].length() > 0) {
                numList.add(Nnums[i]);
            }
        }
    }

    public static boolean isTurn(String str1, String str2) {
        String[] nums = str2.split(",");
        int count = 0;
        for (String num : nums) {
            if (str1.contains(num)) {
                count++;
            }
        }
        return count >= 2;
    }

    public static String newNum(String str1, String str2) {
        String[] nums1 = str1.split(",");
        String[] nums2 = str2.split(",");
        List<String> listNum = new ArrayList<>();
        for (String num : nums1) {
            if (!listNum.contains(num)) {
                listNum.add(num);
            }
        }
        for (String num : nums2) {
            if (!str1.contains(num)) {
                listNum.add(num);
            }
        }
        Collections.sort(listNum);
        String nm = Arrays.toString(listNum.toArray(new String[listNum.size()]));
        return nm.substring(1, nm.length() - 1);
    }
}
