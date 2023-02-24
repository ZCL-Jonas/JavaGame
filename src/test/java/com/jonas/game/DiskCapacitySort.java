package com.jonas.game;

public class DiskCapacitySort {
    public static void main (String[] args) {
        String[] cap = {"1G", "2G", "1024M"};
//        String[] cap = {"2G4M", "3M2G", "1T"};
//        String[] cap = {"1T", "20M", "3G", "10G6T", "3M12G9M"};
        for (int i = 0; i < cap.length; i++) {
            long a = getDiskCap(cap[i]);
            // System.out.print("cap[i]:" + cap[i] + " a:" + a);
            for (int j = i + 1; j < cap.length; j++) {
                long b = getDiskCap(cap[j]);
                // System.out.print(" cap[j]:" + cap[j] + " b:" + b);
                if (a > b) {
                    String p = cap[i];
                    cap[i] = cap[j];
                    cap[j] = p;
                    break;
                }
            }
            // System.out.println("");
        }

        for (String cp:cap) {
            System.out.println(cp);
        }
    }

    private static long getDiskCap(String disk) {
        int index = 0;
        long sum = 0;
        for (int i = 0; i < disk.length(); i++) {
            char ch = disk.charAt(i);
            if (ch == 'M' || ch == 'G' || ch == 'T') {
                int n = Integer.parseInt(disk.substring(index, i));
                switch (ch) {
                    case 'T':
                        sum += t_m(n);
                        break;
                    case 'G':
                        sum += g_m(n);
                        break;
                    case 'M':
                        sum += n;
                        break;
                }
                index = i + 1;
            }
        }
        return sum;
    }

    private static long t_m(int t) {
        return t * g_m(1024);
    }

    private static long g_m(int g) {
        return g * 1024L;
    }
}
