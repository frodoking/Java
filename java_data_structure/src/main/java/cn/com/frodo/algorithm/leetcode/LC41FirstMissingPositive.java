package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.Arrays;
import cn.com.frodo.algorithm.Algorithm;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

/**
 * 41. 缺失的第一个正数
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 *
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 *
 * 示例 1：
 * 输入：nums = [1,2,0]
 * 输出：3
 *
 * 示例 2：
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 *
 * 示例 3：
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 *
 * @author frodoking
 * @ClassName: LC41FirstMissingPositive
 * @date 2022/3/20
 */
@AlgorithmPoint(
        tag = AlgorithmPoint.Tag.leetcode,
        difficulty = AlgorithmPoint.Difficulty.hard,
        category = AlgorithmPoint.Category.array)
public class LC41FirstMissingPositive implements IAlgorithm {
    @Override
    public void exec() {
        int[] nums = {3,4,-1,1};
        int result = firstMissingPositive(nums);
        System.out.println(result);
    }

    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            // while的原因是不清楚交换回来的值是否在正确的位置上
            while (nums[i] > 0 && nums[i] < len + 1 && nums[nums[i] - 1] != nums[i]) {
                Arrays.swap(nums,nums[i] - 1, i);
            }
        }
        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return len + 1;
    }
}
