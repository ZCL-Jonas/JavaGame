package com.jonas.game;

import java.util.ArrayList;
import java.util.List;

public class SortHeightAndWeight {
    public static void main(String[] args) {
        String[] hArray = "100 100 120 130".split(" ");
        String[] vArray = "40 30 60 50".split(" ");
        List<Students> students = new ArrayList<>();
        for (int i = 0; i < hArray.length; i++) {
            Students stu = new Students();
            stu.height = Integer.parseInt(hArray[i]);
            stu.weight = Integer.parseInt(vArray[i]);
            stu.index = i + 1;
            students.add(stu);
        }
        studentSort(students);
        System.out.print("\n");
        for (Students st:students) {
            System.out.print(st.index + " ");
        }
    }

    private static void print(List<Students> students) {
        System.out.print("\n");
        for (Students st:students) {
            System.out.print(st.weight + " ");
        }
        System.out.print("\n");
        for (Students st:students) {
            System.out.print(st.index + " ");
        }
    }
    private static void studentSort(List<Students> students) {
        int index = 0;
        int count = 0;
        for (int i = 1; i < students.size(); i++) {
            if (students.get(i).height == students.get(index).height) {
                count++;
            } else {
                if (count > 0) {
                    sort(students, index, i);
                    count = 0;
                }
                index = i;
            }
            print(students);
            System.out.println("\ncount:" + count + " index:" + index + " i:" + i);
        }
    }

    private static void sort(List<Students> students, int start, int end) {
        for (int j = start; j < end; j++) {
            for (int k = j + 1; k < end; k++) {
                if (students.get(j).weight > students.get(k).weight) {
                    int w = students.get(j).weight;
                    students.get(j).weight = students.get(k).weight;
                    students.get(k).weight = w;
                    int ix = students.get(j).index;
                    students.get(j).index = students.get(k).index;
                    students.get(k).index = ix;
                }
            }
        }
    }
    static class Students {
        int height;
        int weight;
        int index;
    }
}
