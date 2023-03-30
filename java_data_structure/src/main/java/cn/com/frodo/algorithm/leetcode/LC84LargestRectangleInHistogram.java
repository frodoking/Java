package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.Algorithm;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * <p>
 * 示例 1:
 * <p>
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域，面积为 10
 * 示例 2：
 * <p>
 * 输入： heights = [2,4]
 * 输出： 4
 * <p>
 * 提示：
 * <p>
 * 1 <= heights.length <=105
 * 0 <= heights[i] <= 104
 */
@AlgorithmPoint(
        tag = AlgorithmPoint.Tag.leetcode,
        difficulty = AlgorithmPoint.Difficulty.hard,
        category = AlgorithmPoint.Category.array,
        algorithm = @Algorithm(value = Algorithm.AlgorithmEnum.monotonicStack))
public class LC84LargestRectangleInHistogram implements IAlgorithm {
    @Override
    public void exec() {
        int[] heights = {2, 1, 5, 6, 2, 3};
        Assert.assertEquals(10, largestRectangleArea(heights));
        Assert.assertEquals(10, largestRectangleArea2(heights));
    }

    @Algorithm(value = Algorithm.AlgorithmEnum.force)
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return heights[0];
        }

        int area = 0;
        for (int i = 0; i < len; i++) {
            int left = i;
            int right = i;

            while (left - 1 >= 0 && heights[left - 1] >= heights[i]) {
                left--;
            }

            while (right + 1 <= len - 1 && heights[right + 1] >= heights[i]) {
                right++;
            }

            area = Math.max(area, (right - left + 1) * heights[i]);
            System.out.println("heights = " + heights[i] + "--" + area);
        }
        return area;
    }

    @Algorithm(value = Algorithm.AlgorithmEnum.monotonicStack)
    public int largestRectangleArea2(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return heights[0];
        }

        len += 2;
        int[] newHeights = new int[len];
        for (int i = 0; i < len - 2; i++) {
            newHeights[i + 1] = heights[i];
        }
        heights = newHeights;

        int area = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(0);
        for (int i = 1; i < len; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                int height = heights[stack.pop()];

                int left = stack.peek();
                int right = i;

                area = Math.max(area, (right - left - 1) * height);

                System.out.println("heights = " + height + "--" + area);
            }
            stack.push(i);
        }
        return area;
    }


}
