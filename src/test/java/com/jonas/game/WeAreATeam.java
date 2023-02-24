package com.jonas.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WeAreATeam {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner((System.in));
//        String[] nm = scanner.nextLine().split(" ");
//        int n = Integer.parseInt(nm[0]);
//        int m = Integer.parseInt(nm[1]);
        //if (n >= 1 && n <= 100000 && m >= 1 && m <= 100000) {
            List<String> scanList = new ArrayList<String>();
            //for (int i = 0; i < m; i++) {
            //scanList.add("5 6");
            scanList.add("1 2 0");
            scanList.add("1 2 1");
            scanList.add("1 5 0");
            scanList.add("2 3 1");
            scanList.add("2 5 1");
            scanList.add("1 3 2");
            //}
            List<String> teamList = new ArrayList<String>();
            for (int i = 0; i < 6; i++) {
                String[] inputs = scanList.get(i).split(" ");
                if (isValidValue(5, inputs)) {
                    if (inputs[2].equals("0")) {
                        saveTeam(teamList, inputs[0], inputs[1]);
                    } else {
                        String log = !isATeam(teamList, inputs[0], inputs[1])
                                ? "we are not team" : "we are a team";
                        System.out.println(log);
                    }
                } else {
                    System.out.println("da pian zi");
                }
            }
//        } else {
//            System.out.println("NULL");
//        }
    }

    private static boolean isValidValue(int n, String[] inputs) {
        int a = Integer.parseInt(inputs[0]);
        int b = Integer.parseInt(inputs[1]);
        int c = Integer.parseInt(inputs[2]);
        return a >= 1 && a <= n && b >= 1 && b <= n && c >= 0 && c <= 1;
    }

    private static void saveTeam(List<String> teamList, String a, String b) {
        boolean isContains = false;
        for (int i = 0; i < teamList.size(); i++) {
            String team = teamList.get(i);
            if (team.contains(a)) {
                teamList.add(i, team + b);
                isContains = true;
                break;
            } else if (team.contains(b)) {
                teamList.add(i, team + a);
                isContains = true;
                break;
            }
        }
        if (!isContains) {
            teamList.add(a + b);
        }
        // System.out.println(isContains);
    }

    private static boolean isATeam(List<String> teamList, String a, String b) {
        for (String team : teamList) {
            // System.out.println(team + " " + a + " " + b + " " + (team.contains(a) && team.contains(b)));
            if (team.contains(a) && team.contains(b)) {
                return true;
            }
        }
        return false;
    }
}
