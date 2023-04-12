package cn.com.frodo.algorithm.other;

import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 题目：给定一个整型数组，数组元素随机无序的，要求打印出所有元素右边第一个大于该元素的值。
 *
 * 如数组A=[1,5,3,6,4,8,9,10] 输出[5, 6, 6, 8, 8, 9, 10, -1]
 *
 * 如数组A=[8, 2, 5, 4, 3, 9, 7, 2, 5] 输出[9, 5, 9, 9, 9, -1, -1, 5, -1]
 *
 */
@AlgorithmPoint(tag = {AlgorithmPoint.Tag.interview},
        difficulty = AlgorithmPoint.Difficulty.medium,
        company = {AlgorithmPoint.Company.bytedance},
        category = AlgorithmPoint.Category.array)
public class FindMaxRight implements IAlgorithm {

    @Override
    public void exec() {
        int[] nums = {1, 5, 3, 6, 4, 8, 9, 10};
        int[] res = findMaxRight(nums);
        System.out.println(Arrays.toString(res));
    }

    public int[] findMaxRight(int[] nums) {
        // 单调递减栈
        Deque<Integer> stack = new ArrayDeque<>();
        int len = nums.length;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                res[stack.poll()] = nums[i];
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            res[stack.poll()] = -1;
        }

        return res;
    }
}
