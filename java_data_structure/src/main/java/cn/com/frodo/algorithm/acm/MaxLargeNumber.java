package cn.com.frodo.algorithm.acm;

import cn.com.frodo.algorithm.AlgorithmPoint;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 卡片组成的最大数字
 * https://dream.blog.csdn.net/article/details/129755720
 */
@AlgorithmPoint(
        tag = AlgorithmPoint.Tag.interview,
        difficulty = AlgorithmPoint.Difficulty.easy,
        category = AlgorithmPoint.Category.str,
        company = AlgorithmPoint.Company.huawei)
public class MaxLargeNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Comparator<String> comparator = (o1, o2) -> {
            if (o1.startsWith(o2)) {
                return o1.charAt(o2.length()) - o2.charAt(0) ;
            }
            if (o2.startsWith(o1)) {
                return o1.charAt(0) - o2.charAt(o1.length());
            }
            return o1.compareTo(o2);
        };
        String res = Arrays.stream(scanner.nextLine().split(","))
                .sorted(comparator.reversed()).collect(Collectors.joining());

        System.out.println(res);
    }
}
