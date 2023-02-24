package com.jonas.game;

import java.util.*;

public class FindWord {

    public static void main(String[] args) {
        String vlanPool = "20-21,15,18,30,5-10";
        int vlan = 8;
        List<String> vlanList = new ArrayList<>(Arrays.asList(vlanPool.split(",")));
        handleVlan(vlanList, vlan);
        sort(vlanList);
        System.out.println(Arrays.toString(vlanList.toArray(new String[0])));
    }

    private static void sort(List<String> vlanList) {
        for (int i = 0; i < vlanList.size(); i++) {
            int n = getNum(vlanList.get(i));
            for (int j = i + 1; j < vlanList.size(); j++) {
                int m = getNum(vlanList.get(j));
                if (n > m) {
                    String v = vlanList.get(i);
                    vlanList.add(i, vlanList.get(j));
                    vlanList.remove(i + 1);
                    vlanList.add(j, v);
                    vlanList.remove(j + 1);
                    n = m;
                }
            }
        }
    }

    private static int getNum(String v) {
        return  (v.indexOf("-") > 0) ? Integer.parseInt(v.substring(0, v.indexOf("-"))) : Integer.parseInt(v);
    }

    private static void handleVlan(List<String> vlanList, int v) {
        for (int i = 0; i < vlanList.size(); i++) {
            int index = vlanList.get(i).indexOf("-");
            if (index > 0) {
                if (multiVlan(vlanList, i, index, v)) {
                    break;
                } else if (i == vlanList.size()) {
                    vlanList.add(v + "");
                }
            } else {
                if (v == Integer.parseInt(vlanList.get(i))) {
                    vlanList.remove(i);
                    break;
                } else if (i == vlanList.size()) {
                    vlanList.add(v + "");
                }
            }
        }
    }

    // a == v && b - a == 1             [20, 21] 20 -> 21
    // b == v && b - a == 1             [20, 21] 21 -> 20
    // v - a == 1 && b - v == 1         [20, 22] 21 -> 20 22
    // v - a == 1 && b - v > 1          [20, 25] 21 -> 20 22-25
    // v - a > 1 && b - v == 1          [20, 25] 24 -> 20-23 25
    // a == v && b - a > 1              [20, 25] 20 -> 21-25
    // v - a > 1 && b == v              [20, 25] 25 -> 20-24
    // v - a > 1 && b - v > 1           [20, 25] 23 -> 20-22 24- 25
    // v > b
    // v < a
    private static boolean multiVlan(List<String> vlanList, int i, int index, int v) {
        int a = Integer.parseInt(vlanList.get(i).substring(0, index));
        int b = Integer.parseInt(vlanList.get(i).substring(index + 1));
        boolean isVlan = true;
        if (a == v && b - a == 1) {
            vlanList.add(i, b + "");
        } else if (b == v && b - a == 1) {
            vlanList.add(i, a + "");
        } else if (v - a == 1 && b - v == 1) {
            vlanList.add(i, a + "");
            vlanList.add(b + "");
        } else if (v - a == 1 && b - v > 1) {
            vlanList.add(i, a + "");
            vlanList.add((v + 1) + "-" + b);
        } else if (v - a > 1 && b - v == 1) {
            vlanList.add(i, b + "");
            vlanList.add(a + "-" + (v - 1));
        } else if (a == v && b - a > 1 ) {
            vlanList.add(i, (a + 1) + "-" + b);
        } else if (v - a > 1 && b == v) {
            vlanList.add(i, a + "-" + (b - 1));
        } else if (v - a > 1 && b - v > 1) {
            vlanList.add(i, a + "-" + (v - 1));
            vlanList.add((v + 1) + "-" + b);
        } else  {
            return false;
        }
        vlanList.remove(i + 1);
        return isVlan;
    }
}
