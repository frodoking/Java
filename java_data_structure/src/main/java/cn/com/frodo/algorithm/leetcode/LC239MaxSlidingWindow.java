package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.Arrays;
import cn.com.frodo.algorithm.Algorithm;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 239. 滑动窗口最大值
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回 滑动窗口中的最大值 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * 示例 2：
 *
 * 输入：nums = [1], k = 1
 * 输出：[1]
 */
@AlgorithmPoint(difficulty = AlgorithmPoint.Difficulty.hard,
        category = AlgorithmPoint.Category.array,
        algorithm = @Algorithm(value = Algorithm.AlgorithmEnum.monotonicStack))
public class LC239MaxSlidingWindow implements IAlgorithm {

    @Override
    public void exec() {
        // 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
        // 输出：[3,3,5,5,6,7]

        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;

        Arrays.show(maxSlidingWindow(nums, k), "LC239MaxSlidingWindow");
    }

    /**
     * 思路：用单调递减队列记录最大值，最前边记录最大值。为了满足滑块的概念，所以需要记录编号
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
        }

        int size = nums.length;
        int[] res = new int[size - k + 1];
        res[0] = nums[queue.peekFirst()];
        for (int i = k; i < size; i++) {
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
            while (queue.peekFirst() <= i - k) {
                queue.pollFirst();
            }
            res[i - k + 1] = nums[queue.peekFirst()];
        }

        return res;
    }
}
