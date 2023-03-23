package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.AlgorithmPoint;
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
@AlgorithmPoint
@Deprecated
public class LC53MaximumSubarray  implements IAlgorithm {
    @Override
    public void exec() {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int max = maxSubArray(nums);
        System.out.println(max);
    }

    /**
     * 重点思路就是，如果前面总和为负数就不要加入到后边序列中
     */
    public int maxSubArray(int[] nums) {
        int pre = nums[0];
        int max = pre;
        int size = nums.length;
        for (int i = 1; i < size; i++) {
            if (pre > 0) {
                pre += nums[i];
            } else {
                pre = nums[i];
            }
            max = Math.max(max, pre);
        }
        return max;
    }

}
