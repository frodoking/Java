package cn.com.frodo.algorithm.acm;

import cn.com.frodo.algorithm.AlgorithmPoint;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 找左右两边和相同的下标
 *
 * 输入
 * 2,5,-1,8,6
 * 输出
 * 3
 */
@AlgorithmPoint(
        tag = AlgorithmPoint.Tag.interview,
        difficulty = AlgorithmPoint.Difficulty.easy,
        category = AlgorithmPoint.Category.thread,
        company = AlgorithmPoint.Company.huawei)
public class AlibabaFindGoldI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] array = scanner.nextLine().split(",");
        int[] intArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            intArray[i] = Integer.parseInt(array[i]);
        }

        System.out.println(findIndex(intArray));
    }

    public static int findIndex(int[] array) {
        int total = Arrays.stream(array).sum();
        int leftSum = 0;
        int rightSum = total;
        for (int i = 0; i < array.length; i++) {
            rightSum -= array[i];
            if (leftSum == rightSum) {
                return i;
            }
            leftSum += array[i];
        }
        return -1;
    }
}
