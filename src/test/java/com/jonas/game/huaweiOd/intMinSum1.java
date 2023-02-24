package com.jonas.game.huaweiOd;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class intMinSum1 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int[] arr1 = parseArr(scanner.nextLine());
            int[] arr2 = parseArr(scanner.nextLine());
            int k = scanner.nextInt();
            solution(arr1, arr2, k);
        }
    }

    private static void solution(int[] arr1, int[] arr2, int k) {
        List<Integer> sums = new ArrayList<>(arr1.length * arr1.length);
        for (int x:arr1) {
            for (int y:arr2) {
                sums.add(x + y);
            }
        }
        sums.sort(Integer::compareTo);
        int result = 0;
        for(int i = 0; i < k; i++){
            result += sums.get(i);
        }
        System.out.println(result);
    }

    private static int[] parseArr(String line) {
        String[] arr = line.split(" ");
        int[] nums = new int[arr.length - 1];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(arr[i + 1]);
        }
        return nums;
    }
}
