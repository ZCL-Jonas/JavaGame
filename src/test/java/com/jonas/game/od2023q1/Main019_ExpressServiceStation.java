package com.jonas.game.od2023q1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main019_ExpressServiceStation {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = Integer.parseInt(scanner.nextLine());
            Integer[][] matrix = new Integer[n][]; // n
            for (int i = 0; i < n; i++) {
                matrix[i] = Arrays.stream(scanner.nextLine().split(" "))
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new);
            }
            long time = System.currentTimeMillis();
            int ufRet = solution(n, matrix);
            System.out.println("union find use time:" + (System.currentTimeMillis() - time));
            long time1 = System.currentTimeMillis();
            int ufRet1 = solution2(n, matrix);
            System.out.println("\nnot union find use time:" + (System.currentTimeMillis() - time1));
            System.out.println();
        }
    }

    /**
     * 1. 快递业务范围有N个站点
     * 1. A站点与B站点可以中转快递，则认为A-B站可达
     * 1. 如果A-B可达，B-C可达，则A-C可达
     * 1. 用s[i][j]表示i-j是否可达
     * 2. s[i][j] = 1表示i-j可达
     * 3. s[i][j] = 0表示i-j不可达
     * 4. s[i][j]与s[j][i]取值相同
     *
     * @return 站点
     */
    private static int solution(int n, Integer[][] matrix) {
        UnionFindSet unionFindSet = new UnionFindSet(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    unionFindSet.union(i, j);
                }
            }
        }
        return unionFindSet.getCount();
    }

    private static int solution2(int n, Integer[][] matrix) {
        int count = 0;
        Set<Integer> cover = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            if (!cover.contains(i)) {
                System.out.println("\ni: " + i);
                count++;
            }
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    System.out.print(" (" + i + ", " + j + ") ");
                    cover.add(j);
                }
            }
        }
        return count;
    }
}
