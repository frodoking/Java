package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;

/**
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 子数组 是数组中的一个连续部分。
 *
 * 示例 1：
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 *
 * @author frodoking
 * @ClassName: LC53MaximumSubarray
 * @date 2022/3/8
 */
@Deprecated
public class LC53MaximumSubarray  implements IAlgorithm {
    @Override
    public void exec() {

    }

    public int maxSubArray(int[] nums) {
        int max = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i-1] > 0) {
                nums[i] += nums[i-1];
            }
            max = Math.max(max, nums[i]);
        }
        return max;
    }

}
