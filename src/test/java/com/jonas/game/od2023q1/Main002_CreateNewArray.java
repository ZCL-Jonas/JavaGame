package com.jonas.game.od2023q1;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main002_CreateNewArray {
    private static int count = 0;
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){
            Integer[] n = Arrays.stream(scanner.nextLine().split(" "))
                    .map(Integer::parseInt).toArray(Integer[]::new);
            int m = scanner.nextInt();

            System.out.println(solution(n, m));
        }
    }

    private static int solution(Integer[] n, int m) {
        Integer[] newArr = Arrays.stream(n).filter(val -> val <= m).toArray(Integer[]::new);
        return dfs(newArr, 0, 0, newArr[0], m, 0);
    }

    private static int dfs(Integer[] arr, int index, int sum, int min, int m, int count) {
        if (sum > m) {
            return count;
        }
        if (sum == m || (m - sum > 0 && m - sum < min)) {
            return count + 1;
        }
        for (int i = index; i < arr.length; i++) {
            count = dfs(arr, i, sum + arr[i], min, m, count);
        }
        return count;
    }
}
