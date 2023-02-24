package com.jonas.game.odMain;

import java.util.Arrays;
import java.util.Scanner;

public class Main002 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){
            Integer[] n = Arrays.stream(scanner.nextLine().split(" "))
                    .map(Integer::parseInt).
                    toArray(Integer[]::new);
            int m = Integer.parseInt(scanner.nextLine());
            System.out.println(solution(n, m));
        }
    }

    private static int solution(Integer[] n, int m) {
        Integer[] newArr = Arrays.stream(n).filter(v -> v <= m).toArray(Integer[]::new);
        return dfs(newArr, 0, 0, newArr[0], m, 0);
    }

    private static int dfs(Integer[] newArr, int index, int sum, int min, int m, int count) {
        if (sum > m) {
            return count;
        }
        // 相加等于M 或 M减和值在区间内(0, m)
        if (sum == m || (m - sum > 0 && m - sum < m)) {
            return count + 1;
        }
        for (int i = index; i < newArr.length; i++) {
            count = dfs(newArr, i, sum + newArr[i], min, m, count);
        }
        return count;
    }
}
