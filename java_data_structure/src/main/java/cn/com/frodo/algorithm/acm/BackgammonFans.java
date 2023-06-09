package cn.com.frodo.algorithm.acm;

import cn.com.frodo.algorithm.AlgorithmPoint;

import java.util.Scanner;

/**
 * 五子棋迷
 * https://dream.blog.csdn.net/article/details/130837867
 *
 * 输入
 * 1
 * -1 0 1 1 1 0 1 0 1 -1 1
 * 输出 5
 *
 * 输入
 * -1
 * -1 0 1 1 1 0 1 0 1 -1 1
 * 输出 1
 */
@AlgorithmPoint(
        tag = AlgorithmPoint.Tag.interview,
        difficulty = AlgorithmPoint.Difficulty.easy,
        category = AlgorithmPoint.Category.str,
        company = AlgorithmPoint.Company.huawei)
public class BackgammonFans {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int target = scanner.nextInt();
        scanner.nextLine();
        String line = scanner.nextLine();
        String[] lineArray = line.split(" ");
        int[] lineInt = new int[lineArray.length];
        for (int i = 0; i < lineArray.length; i++) {
            lineInt[i] = Integer.parseInt(lineArray[i]);
        }
        System.out.println(findMaxMatchIndex(lineInt, target));
    }

    public static int findMaxMatchIndex(int[] nums, int target) {
        int len = nums.length;
        int start = nums[0];
        for (int i = 1; i < len; i++) {
            if ((start > 0 && nums[i] < 0) || (start < 0 && nums[i] > 0)) {
                start = nums[i];
                continue;
            }
            if (nums[i] == 1 && start > 0) {
                nums[i] = nums[i - 1] + 1;
                continue;
            }
            if (nums[i] == -1 && start < 0) {
                nums[i] = nums[i - 1] - 1;
                continue;
            }
        }
        for (int i = len - 2; i > 0; i--) {
            if ((nums[i] > 0 && nums[i + 1] > 0) || (nums[i] < 0 && nums[i + 1] < 0)) {
                nums[i] = nums[i + 1];
            }
        }

        int index = 0;
        int maxLength = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                int currentLength = 0;
                if (i > 1) {
                    if ((target > 0 && nums[i - 1] > 0)) {
                        currentLength = nums[i - 1];
                    } else if (target < 0 && nums[i - 1] < 0) {
                        currentLength = nums[i - 1];
                    }
                }
                if (i < len - 1) {
                    if ((target > 0 && nums[i + 1] > 0)) {
                        currentLength += nums[i + 1];
                    } else if (target < 0 && nums[i + 1] < 0) {
                        currentLength += nums[i + 1];
                    }
                }
                currentLength = Math.abs(currentLength) + 1;
                if (currentLength > maxLength
                        || (currentLength == maxLength && Math.abs(len / 2 - i) < Math.abs(len / 2 - index))) {
                    index = i;
                    maxLength = currentLength;
                }
            }
        }
        return maxLength == 0 ? -1 : index;
    }
}
