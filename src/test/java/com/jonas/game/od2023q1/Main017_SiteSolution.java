package com.jonas.game.od2023q1;

import java.util.*;

public class Main017_SiteSolution {
    /**
     * 3
     * 1 3
     * 2 4
     * 4 2
     *
     * @param args
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int maxSite = 0;
            List<Integer[]> siteList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int start = scanner.nextInt();
                int end = scanner.nextInt();
                // 计算最大站点
                maxSite = Math.max(maxSite, Math.max(start, end));
                // 线路是单程循环，需要考虑上车站点>下车站点如[3 2] 3->4->1->2, 可拆分为两人上下车
                if (start > end) {
                    siteList.add(new Integer[]{start, maxSite});
                    siteList.add(new Integer[]{1, end});
                } else {
                    siteList.add(new Integer[]{start, end});
                }
            }
            solution(siteList);
        }
    }

    private static void solution(List<Integer[]> sites) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer[] site : sites) {
            for (int i = site[0]; i <= site[1]; i++) {
                // 统计各个站点人数
                map.put(i, map.getOrDefault(i, 0) + 1);
            }
        }
        // map的value集合
        LinkedList<Map.Entry<Integer, Integer>> list = new LinkedList<>(map.entrySet());
        // 比较人数降序，人数相同比较站点升序
        list.sort((o1, o2) -> Objects.equals(o2.getValue(), o1.getValue()) ? o1.getKey() - o2.getKey() : o2.getValue() - o1.getValue());
        System.out.println(list.get(0).getKey());
    }

    private static void solution() {
        // 处理输入
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] nums = new int[n][2];
        int max_site = 0;
        for (int i = 0; i < n; i++) {
            int start = in.nextInt();
            int end = in.nextInt();
            max_site = Math.max(max_site, Math.max(start,end));
            nums[i] = new int[]{start, end};
        }

        ArrayList<Integer[]> sites = new ArrayList<>();
        for (int i=0;i<nums.length;i++) {
            if (nums[i][0] > nums[i][1]) {
                sites.add( new Integer[]{nums[i][0], max_site});
                sites.add( new Integer[]{1, nums[i][1]});
            } else {
                sites.add( new Integer[]{nums[i][0], nums[i][1]});
            }
        }

        //创建人数的数据结构
        HashMap<Integer, Integer> site_map = new HashMap<>();
        for (Integer[] pair : sites) {
            for (int i = pair[0]; i <= pair[1]; i++) {
                site_map.put(i, site_map.getOrDefault(i, 0) + 1);
            }
        }
        LinkedList<Map.Entry<Integer, Integer>> site_count = new LinkedList<>(site_map.entrySet());

        //先按照人数排序，人数
        site_count.sort((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()));
        site_count.sort(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                int res = o2.getValue() - o1.getValue();
                if (res == 0) {
                    res = o1.getKey() - o2.getKey();
                }
                return res;
            }
        });

        System.out.println(site_count.get(0).getKey());
    }
}