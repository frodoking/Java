package cn.com.frodo.algorithm.acm;

import cn.com.frodo.algorithm.AlgorithmPoint;

import java.util.Scanner;

/**
 * 事件推送
 * https://dream.blog.csdn.net/article/details/129259070
 */
@AlgorithmPoint(
        tag = AlgorithmPoint.Tag.interview,
        difficulty = AlgorithmPoint.Difficulty.easy,
        category = AlgorithmPoint.Category.str,
        company = AlgorithmPoint.Company.huawei)
public class EventPush {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int r = scanner.nextInt();
        int[] aArray = new int[m];
        int[] bArray = new int[n];
        for (int i = 0; i < m; i++) {
            aArray[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            bArray[i] = scanner.nextInt();
        }

        int a = 0;
        int b = 0;
        while (a< m && b < n) {
            if (aArray[a] <= bArray[b]  && bArray[b] - aArray[a]< r) {
                System.out.println(aArray[a] + " " + bArray[b]);
                a++;
                continue;
            }
            b++;
        }
    }
}
