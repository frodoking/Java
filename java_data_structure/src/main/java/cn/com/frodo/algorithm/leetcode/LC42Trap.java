package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.Algorithm;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 * <p>
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *
 * @author frodoking
 * @ClassName: LC42Trap
 * @date 2022/3/14
 */
@Deprecated
@AlgorithmPoint(
        tag = AlgorithmPoint.Tag.leetcode,
        difficulty = AlgorithmPoint.Difficulty.hard,
        category = AlgorithmPoint.Category.array,
        algorithm = @Algorithm(value = {
                Algorithm.AlgorithmEnum.dp,
                Algorithm.AlgorithmEnum.twoPointers,
                Algorithm.AlgorithmEnum.monotonicStack
        }))
public class LC42Trap implements IAlgorithm {

    @Override
    public void exec() {
        int[] height = {4, 2, 0, 3, 2, 5};

        Assert.assertEquals(9, trap(height));
        Assert.assertEquals(9, trapWithDP(height));
        Assert.assertEquals(9, trapWithStack(height));
        Assert.assertEquals(9, trapWithTwoPointers(height));
    }

    /**
     * 暴力解法, 找每一个柱子的左右最大值
     */
    @Algorithm(value = Algorithm.AlgorithmEnum.force)
    public int trap(int[] height) {
        int len = height.length;
        if (len < 2) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < len; i++) {
            int left = i;
            int leftMax = height[i];
            int right = i;
            int rightMax = height[i];
            for (int j = 0; j < i; j++) {
                if (height[j] > leftMax) {
                    leftMax = height[j];
                    left = j;
                }
            }

            for (int j = i + 1; j < len; j++) {
                if (height[j] > rightMax) {
                    rightMax = height[j];
                    right = j;
                }
            }

            ans += Math.min(height[left], height[right]) - height[i];
            System.out.println("height = " + i + "--" + ans);
        }
        return ans;
    }

    /**
     * 动态规划
     */
    @Algorithm(value = Algorithm.AlgorithmEnum.dp)
    public int trapWithDP(int[] height) {
        int len = height.length;
        if (len < 2) {
            return 0;
        }
        int[] leftMaxArray = new int[len];
        leftMaxArray[0] = height[0];
        int[] rightMaxArray = new int[len];
        rightMaxArray[len - 1] = height[len - 1];
        for (int i = 1; i < len; i++) {
            leftMaxArray[i] = leftMaxArray[i - 1];
            if (height[i] > leftMaxArray[i - 1]) {
                leftMaxArray[i] = height[i];
            }

            rightMaxArray[len - i - 1] = rightMaxArray[len - i];
            if (height[i] > rightMaxArray[len - i]) {
                rightMaxArray[len - i - 1] = height[len - i - 1];
            }
        }

        int ans = 0;
        for (int i = 0; i < len; i++) {
            if (height[i] == leftMaxArray[i] || height[i] == rightMaxArray[i]) {
                continue;
            }

            ans += Math.min(leftMaxArray[i], rightMaxArray[i]) - height[i];
        }

        return ans;
    }

    /**
     * 单调递减栈，栈顶和当前元素对比，一定形成一个低洼
     */
    @Algorithm(value = Algorithm.AlgorithmEnum.monotonicStack)
    public int trapWithStack(int[] height) {
        int len = height.length;
        if (len < 2) {
            return 0;
        }

        Deque<Integer> stack = new LinkedList<>();
        int ans = 0;
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int h = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int weight = i - stack.peek() - 1;

                ans += weight * (Math.min(height[i], height[stack.peek()]) - height[h]);
            }
            stack.push(i);
        }
        return ans;
    }

    /**
     * 双指针法，主要将最小柱子找完，在找最大柱子
     */
    @Algorithm(value = Algorithm.AlgorithmEnum.twoPointers)
    public int trapWithTwoPointers(int[] height) {
        int len = height.length;
        if (len < 2) {
            return 0;
        }
        int left = 0;
        int right = len - 1;
        int ans = 0;
        int leftMax = 0;
        int rightMax = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    ans += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    ans += rightMax - height[right];
                }
                right--;
            }
        }
        return ans;
    }
}
