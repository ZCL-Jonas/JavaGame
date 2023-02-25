package com.jonas.game.od2023q1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main018_ExpressDelivery {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Integer[] num = Arrays.stream(scanner.nextLine().split(" "))
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);
            List<String> exprPkg = new ArrayList<>();
            for (int i = 0; i < num[0]; i++) {
                exprPkg.add(scanner.nextLine());
            }
            List<String> deliPkg = new ArrayList<>();
            for (int i = 0; i < num[1]; i++) {
                String[] line = scanner.nextLine().split(" ");
                for (int j = 2; j < line.length; j++) {
                    deliPkg.add(line[j] + " " + line[0] + " " + line[1]);
                }
            }
            List<String> cantPkg = new ArrayList<>();
            for (String s : exprPkg) {
                String[] line = s.split(" ");
                if (deliPkg.contains(s)) {
                    cantPkg.add(line[0]);
                }
            }
            if (cantPkg.size() == 0){
                System.out.println("none");
            }
            String pkgs = Arrays.toString(cantPkg.toArray());
            System.out.println(pkgs.substring(1, pkgs.length() - 1));
        }
    }
}
