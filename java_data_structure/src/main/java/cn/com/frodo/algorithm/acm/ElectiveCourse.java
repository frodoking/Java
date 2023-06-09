package cn.com.frodo.algorithm.acm;

import cn.com.frodo.algorithm.AlgorithmPoint;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 选修课 都选择了的同学 班级 + 成绩排序
 * https://blog.csdn.net/hihell/article/details/130816577
 */
@AlgorithmPoint(
        tag = AlgorithmPoint.Tag.interview,
        difficulty = AlgorithmPoint.Difficulty.easy,
        category = AlgorithmPoint.Category.str,
        company = AlgorithmPoint.Company.huawei)
public class ElectiveCourse {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] one = scanner.nextLine().split(";");
        String[] two = scanner.nextLine().split(";");

        Map<String, Student> map = new HashMap<>();
        for (String oneS: one) {
            String[] stuStrArr = oneS.split(",");
            Student student = new Student();
            student.id = stuStrArr[0];
            student.oneScore = Integer.parseInt(stuStrArr[1]);
            map.put(student.id, student);
        }

        for (String twoS: two) {
            String[] stuStrArr = twoS.split(",");
            if (map.containsKey(stuStrArr[0])) {
                map.get(stuStrArr[0]).twoScore = Integer.parseInt(stuStrArr[1]);
            }
        }

        TreeMap<String, List<Student>> resultMap = new TreeMap<>();
        map.entrySet().stream().filter(entry -> entry.getValue().both())
                .collect(Collectors.groupingBy(key -> key.getKey().substring(0, 5)))
                .keySet().stream().sorted().forEach(key -> {
                    resultMap.putIfAbsent(key, new ArrayList<>());
                    map.entrySet().stream().filter(entry -> entry.getValue().both()).forEach(entry -> {
                        if (entry.getKey().startsWith(key)) {
                            resultMap.get(key).add(entry.getValue());
                        }
                    });
        });

        if (resultMap.isEmpty()) {
            System.out.println("NULL");
        } else {
            resultMap.forEach((key, list) -> {
                System.out.println(key);
                list.sort(Comparator.comparing(Student::getScore).thenComparing(Student::getId));
                String res = list.stream().map(Student::getId).collect(Collectors.joining(";"));
                System.out.println(res);
            });
        }
    }

    public static class Student {
        String id;
        int oneScore;
        int twoScore;

        public String getId() {
            return id;
        }

        public int getScore() {
            return oneScore + twoScore;
        }

        public boolean both() {
            return oneScore > 0 && twoScore > 0;
        }
    }
}
