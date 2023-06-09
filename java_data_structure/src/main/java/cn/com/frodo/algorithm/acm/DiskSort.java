package cn.com.frodo.algorithm.acm;

import cn.com.frodo.algorithm.AlgorithmPoint;

import java.util.*;

/**
 * 磁盘空间大小排序，且稳定排序（相同大小顺序稳定）
 * https://dream.blog.csdn.net/article/details/129191172
 */
@AlgorithmPoint(
        tag = AlgorithmPoint.Tag.interview,
        difficulty = AlgorithmPoint.Difficulty.easy,
        category = AlgorithmPoint.Category.str,
        company = AlgorithmPoint.Company.huawei)
public class DiskSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            list.add(line);
        }
        list.sort(Comparator.comparingInt(DiskSort::sum));

        for (String lin : list) {
            System.out.println(lin);
        }

    }

    public static int sum(String line) {
        int sum = 0;
        int index = 0;
        for (int j = 0; j < line.length(); j++) {
            if ('M' == line.charAt(j)) {
                sum += Integer.parseInt(line.substring(index, j));
                index = j + 1;
            } else if ('G' == line.charAt(j)) {
                sum += Integer.parseInt(line.substring(index, j)) * 1024;
                index = j + 1;
            } else if ('T' == line.charAt(j)) {
                sum += Integer.parseInt(line.substring(index, j)) * 1024 * 1024;
                index = j + 1;
            }
        }
        return sum;
    }
}
