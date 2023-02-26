package com.jonas.game.od2023q1;

public class UnionFindSet {
    // 每个节点的父节点
    private int[] parent;
    // 连通分量
    private int count;

    public UnionFindSet(int n) {
        // 初始联通分量，开始互不连通
        this.count = n;
        // 父节点指针始终指向自己
        parent = new int[n];
        for (int i = 0; i < n; i++){
            parent[i] = i;
        }
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        // 合并两棵树
        parent[rootQ] = rootP;
        // 两个连通分量合一
        count--;
    }

    // 找到x的父节点。过程中进行平衡处理
    public int find(int x) {
        if (parent[x] != x) {
            // 进行路径压缩
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // 获取连通分量
    public int getCount() {
        return count;
    }

    // 连个节点是否连通
    public boolean isConnect(int p, int q) {
        return find(p) == find(q);
    }
}
