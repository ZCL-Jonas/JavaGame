package com.jonas.game.od2023q1;

import java.util.*;
import java.util.stream.Collectors;

public class Main016_OutstandingStudents {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // 员工数量
            scanner.nextLine();
            // 每天打卡员工数量
            scanner.nextLine();
            // 缓存每天打卡员工集合
            List<Integer[]> idDays = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                idDays.add(Arrays.stream(scanner.nextLine().split(" "))
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new));
            }
            System.out.println(solution(idDays));
        }
    }

    private static String solution(List<Integer[]> idDays) {
        HashMap<String, Student> map = new HashMap<>();
        for (int i = 0; i < idDays.size(); i++) { // 遍历天数
            for (int id : idDays.get(i)) { // 遍历每天id
                if (!map.containsKey(String.valueOf(id))) {
                    Student student = new Student(id, 1, i);
                    map.put(String.valueOf(id), student);
                } else {
                    Student student = map.get(String.valueOf(id));
                    student.count += 1;
                }
            }
        }
        // 排序
        List<Student> students = map.values().stream().sorted().collect(Collectors.toList());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 5; i++) { // 只是记录前5名
            stringBuilder.append(students.get(i).id).append(" ");
        }
        return stringBuilder.toString();
    }

    private static class Student implements Comparable<Student> {
        private final int id; // 员工编号
        private int count; // 员工打卡次数
        private final int firstTime; // 第一次打卡时间（天）

        @Override
        public int compareTo(Student stu) {
            if (this.count == stu.count) { // 打卡次数相同
                if (this.firstTime == stu.firstTime) { // 第一次打卡时间一样
                    return this.id - stu.id; // 比较id 升序
                } else {
                    return this.firstTime - stu.firstTime; // 比较第一次打卡时间 升序
                }
            } else {
                return stu.count - this.count; // 比较打卡次数 降序
            }
        }

        public Student(int id, int count, int firstTime) {
            this.id = id;
            this.count = count;
            this.firstTime = firstTime;
        }

        @Override
        public String toString() {
            return "\nStudent{" +
                    "id=" + id +
                    ", count=" + count +
                    ", firstTime=" + firstTime +
                    '}';
        }
    }
}
