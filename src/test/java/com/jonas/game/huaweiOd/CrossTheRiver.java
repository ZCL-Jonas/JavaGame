package com.jonas.game.huaweiOd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CrossTheRiver {
    public static int minCount;
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            List<Integer> river = Arrays.stream(scanner.nextLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            int m = river.get(0);
            int n = river.get(1);
            int x = river.get(2);
            minCount = (m + n) * x;
            int mCount = 0;
            int nCount = 0;
            solution(m, n, x, mCount, nCount, 0);
            if (minCount == (m + n) * x) {
                System.out.println(0);
            } else {
                System.out.println(minCount);
            }
        }
    }

    private static int solution(int m, int n, int x, int mCount, int nCount, int count) {
        if (x >= m + n) {
            if (count + 1 < minCount) {
                minCount = count + 1;
            }
            return count + 1;
        }
        for (int i = 0; i <= m && i <= x; i++) {
            for (int j = 0; j <= n && i + j <= x; j++) {
                if (i + j == 0) {
                    continue;
                }
                if ((m - i == 0 || m - i > n - j) && ((mCount + i == 0) || (nCount + i > nCount + j))) {
                    int ret = solution(m - i, n - j, x, mCount + i, nCount + j, count + 1);
                    if (ret < minCount && ret != 0) {
                        minCount = ret;
                    }
                }
            }
        }
        return 0;
    }
}
