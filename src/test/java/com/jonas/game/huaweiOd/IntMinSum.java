package com.jonas.game.huaweiOd;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IntMinSum {

    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            int[] arr1 = parseArr(scanner.nextLine());
            int[] arr2 = parseArr(scanner.nextLine());
            int k = scanner.nextInt();
            solution(arr1, arr2, k);
        }
    }

    private static void solution(int[] arr1, int[] arr2, int k) {
        int len = arr1.length * arr2.length;
        List<Integer> sums = new ArrayList<>(len);
        for (int a : arr1) {
            for (int b : arr2) {
                sums.add(a + b);
            }
        }
        sums.sort(Integer::compareTo);
        int result = 0;
        for (int i = 0; i < k; i++) {
            result += sums.get(i);
        }
        System.out.println(result);
    }

    private static int[] parseArr(String nextLine) {
        String[] arr = nextLine.split(" ");
        int m = Integer.parseInt(arr[0]);
        int[] array = new int[m];
        for (int i = 0; i < m; i++) {
            array[i] = Integer.parseInt(arr[i]);
        }
        return array;
    }
}
