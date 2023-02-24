package com.jonas.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GenerateParenthesis {

    public static void main(String[] args) {
        List<String> kh = generateParenthesis(2);
        for (String st : kh) {
            System.out.println(st);
        }
        System.out.println(generateNum("(()))("));
    }

    private static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        if (n <= 0) {
            return list;
        }
        dfs("", 0, 0, n, list);
        return list;
    }

    private static void dfs(String str, int left, int right, int n, List<String> list) {
        if (left == n && right == n) {
            list.add(str);
        }
        if (left < n) {
            System.out.println("left < n : " + (str + "(") + " left:" + (left + 1) + " right:" + right);
            dfs(str + "(", left + 1, right, n, list);
        }
        if (right < left) {
            System.out.println("right < left : " + (str + ")") + " left:" + left + " right:" + (right + 1));
            dfs(str + ")", left, right + 1, n, list);
        }
    }

    private static int generateNum(String str) {
        int max = 0;
        int index = 0;
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if ('(' == str.charAt(i)) {
                stack.add("(");
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    max = Math.max(i - index, max);
                    index = i + 1;
                }
            }
        }
        return Math.max(str.length() - index, max);
    }
}
