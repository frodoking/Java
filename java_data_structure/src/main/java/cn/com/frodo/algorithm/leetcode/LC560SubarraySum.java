package cn.com.frodo.algorithm.leetcode;


import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

/**
 * 560. 和为 K 的子数组
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的连续子数组的个数 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 */
@Deprecated
@AlgorithmPoint(difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category.array)
public class LC560SubarraySum implements IAlgorithm {
    @Override
    public void exec() {
        int[] nums = {1};
        int k = 1;

        Assert.assertEquals(1, subarraySum(nums, k));
    }

    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        int[] prefixSumArray = new int[len];
        prefixSumArray[0] = 0;
        for (int i = 1; i < len; i++) {
            prefixSumArray[i] = prefixSumArray[i - 1] + nums[i - 1];
        }

        int count = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (prefixSumArray[j] - prefixSumArray[i] + nums[j] == k) {
                    count++;
                }
            }
        }
        return count;
    }

}
