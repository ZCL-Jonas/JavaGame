package com.jonas.game.huaweiOd;

import java.util.HashMap;
import java.util.Scanner;

public class UnionLookupSet {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        System.out.println(getResult(matrix, n));
    }

    public static int getResult(int[][] matrix, int n) {
        UnionFindSet ufs = new UnionFindSet(n);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) { // j从i+1开始，是因为矩阵是对称的
                if (matrix[i][j] == 1) {
                    ufs.union(i, j);
                }
            }
        }

        // connected的key代表某个连通分量的顶级父节点，value代表该连通分量下的节点个数
        HashMap<Integer, Integer> connected = new HashMap<>();

        for (int i = 0; i < n; i++) {
            Integer fa = ufs.find(ufs.fa[i]);
            connected.put(fa, connected.getOrDefault(fa, 0) + 1);
        }

        // 返回最大节点数
        return connected.values().stream().max((a, b) -> a - b).get();
    }
}

// 并查集实现
class UnionFindSet {
    int[] fa;
    int count;

    public UnionFindSet(int n) {
        this.count = n;
        this.fa = new int[n];
        for (int i = 0; i < n; i++) this.fa[i] = i;
    }

    public int find(int x) {
        if (x != this.fa[x]) {
            return (this.fa[x] = this.find(this.fa[x]));
        }
        return x;
    }

    public void union(int x, int y) {
        int x_fa = this.find(x);
        int y_fa = this.find(y);

        if (x_fa != y_fa) {
            this.fa[y_fa] = x_fa;
            this.count--;
        }
    }
}
