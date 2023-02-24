package com.jonas.game.huaweiOd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ProcessorProblem {
    public static void main (String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String ar = scanner.nextLine();
            System.out.println(ar);
            ar = ar.substring(1, ar.length() - 1).replace(" ", "");
            System.out.println(ar);
            String[] array = ar.split(",");
            int num = scanner.nextInt();
            List<Integer> link1 = new ArrayList<>();
            List<Integer> link2 = new ArrayList<>();
            for (String s : array) {
                int a = Integer.parseInt(s);
                if (a < 4) {
                    link1.add(a);
                } else {
                    link2.add(a);
                }
            }

            switch (num) {
                case 1:
                    if (link1.size() == 1) {
                        System.out.println(Arrays.toString(link1.toArray()));
                        break;
                    }
                    if (link2.size() == 1) {
                        System.out.println(Arrays.toString(link2.toArray()));
                        break;
                    }
                    if (link1.size() == 3) {
                        System.out.println(Arrays.toString(link1.toArray()));
                        break;
                    }
                    if (link2.size() == 3) {
                        System.out.println(Arrays.toString(link2.toArray()));
                        break;
                    }
                    if (link1.size() == 2) {
                        System.out.println(Arrays.toString(link1.toArray()));
                        break;
                    }
                    if (link2.size() == 2) {
                        System.out.println(Arrays.toString(link2.toArray()));
                        break;
                    }
                    if (link1.size() == 4) {
                        System.out.println(Arrays.toString(link1.toArray()));
                        break;
                    }
                    if (link2.size() == 4) {
                        System.out.println(Arrays.toString(link2.toArray()));
                        break;
                    }
                    break;
                case 2:
                    if (link1.size() == 2) {
                        System.out.println(Arrays.toString(link1.toArray()));
                        break;
                    }
                    if (link2.size() == 2) {
                        System.out.println(Arrays.toString(link2.toArray()));
                        break;
                    }
                    if (link1.size() == 4) {
                        System.out.println(Arrays.toString(link1.toArray()));
                        break;
                    }
                    if (link2.size() == 4) {
                        System.out.println(Arrays.toString(link2.toArray()));
                        break;
                    }
                    if (link1.size() == 3) {
                        System.out.println(Arrays.toString(link1.toArray()));
                        break;
                    }
                    if (link2.size() == 3) {
                        System.out.println(Arrays.toString(link2.toArray()));
                        break;
                    }
                    break;
                case 4:
                    if (link1.size() == 4) {
                        System.out.println(Arrays.toString(link1.toArray()));
                        break;
                    }
                    if (link2.size() == 4) {
                        System.out.println(Arrays.toString(link2.toArray()));
                        break;
                    }
                    break;
                case 8:
                    System.out.println(Arrays.toString(array));
                    break;
                default:
                    break;
            }
        }
    }
}
