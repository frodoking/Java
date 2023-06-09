package cn.com.frodo.algorithm.acm;

import cn.com.frodo.Arrays;
import cn.com.frodo.algorithm.AlgorithmPoint;

import java.util.Scanner;

/**
 * 题目描述
 * 现在有一队小朋友，他们高矮不同，我们以正整数数组表示这一队小朋友的身高，如数组5,3,1,2,3)。
 * 我们现在希望小朋友排队，以"高”矮"高”矮”顺序排列，每一个"高”位置的小朋友要比相邻的位置高或者相等;每一个"矮”位置的小朋友要比相邻的位置矮或者相等;
 * 要求小朋友们移动的距离和最小，第一个从”高”位开始排，输出最小移动距离即可例如，在示范小队15,3,1,2,3中，5,1,3,2,31是排序结果
 * 5,2.3,1,3)虽然也满足”高”矮"”高”矮"顺序排列，但小朋友们的移动距离大，所以不是最优结果移动距离的定义如下所示:
 * 第二位小朋友移到第三位小朋友后面，移动距离为1，若移动到第四位小朋友后面，移动距离为2:
 * <p>
 * 4 3 5 7 8 >> 4 3 7 5 8
 *
 * https://blog.csdn.net/hihell/article/details/129191089?ops_request_misc=&request_id=6e092d142cdd47a18b766f76d370e3f2&biz_id=&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~koosearch~default-1-129191089-null-null.268^v1^control&utm_term=%E9%AB%98%E7%9F%AE&spm=1018.2226.3001.4450
 *
 */
@AlgorithmPoint(
        tag = AlgorithmPoint.Tag.interview,
        difficulty = AlgorithmPoint.Difficulty.easy,
        category = AlgorithmPoint.Category.thread,
        company = AlgorithmPoint.Company.huawei)
public class GaoAiSort {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] boys = line.split(" ");
            int[] cBoys = new int[boys.length];
            for (int i = 0; i < boys.length; i++) {
                cBoys[i] = Integer.parseInt(boys[i]);
            }

            sort(cBoys);

            for (Integer boy : cBoys) {
                System.out.print(boy + " ");
            }
        }
    }

    public static void sort(int[] array) {
        int len = array.length;
        for (int i = 0; i < len; i++) {
            if (i % 2 == 0 && i < len - 1 && array[i] < array[i + 1]) {
                Arrays.swap(array, i, i + 1);
            }
            if (i % 2 == 1 && i < len - 1 && array[i] > array[i + 1]) {
                Arrays.swap(array, i, i + 1);
            }
        }
    }
}
