package cn.com.frodo.algorithm.acm;


import cn.com.frodo.algorithm.AlgorithmPoint;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 最小交换次数
 * https://blog.csdn.net/qq_38792491/article/details/128997464
 * https://dream.blog.csdn.net/article/details/130937891
 */
@AlgorithmPoint(
        tag = AlgorithmPoint.Tag.interview,
        difficulty = AlgorithmPoint.Difficulty.easy,
        category = AlgorithmPoint.Category.str,
        company = AlgorithmPoint.Company.huawei)
public class MinSwitch {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] array = scanner.nextLine().split(" ");
        int num = scanner.nextInt();
        int[] intArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            intArray[i] = Integer.parseInt(array[i]);
        }
        if (Arrays.stream(intArray).allMatch(item -> item > num)
                || Arrays.stream(intArray).allMatch(item -> item < num)) {
            System.out.println("0");
            return;
        }

        int count = Integer.MAX_VALUE;
        int lessCount = (int) Arrays.stream(intArray).filter(item -> item < num).count();
        // 滑动窗口内需要交换出去的个数次数
        for (int i = 0; i < array.length - lessCount + 1; i++) {
            int cCount = 0;
            for (int j = 0; j < lessCount; j++) {
                if (intArray[i + j] > num) {
                    cCount++;
                }
            }
            count = Math.min(count, cCount);
        }

        System.out.println(count);
    }

}
