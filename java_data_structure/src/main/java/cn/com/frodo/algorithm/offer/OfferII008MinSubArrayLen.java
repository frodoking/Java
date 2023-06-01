package cn.com.frodo.algorithm.offer;

import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

/**
 * 剑指 Offer II 008. 和大于等于 target 的最短子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * <p>
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 * <p>
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 */
public class OfferII008MinSubArrayLen implements IAlgorithm {

    @Override
    public void exec() {
        int[] nums = {2, 3, 1, 2, 4, 3};

        Assert.assertEquals(2, minSubArrayLen(7, nums));

        int[] nums2 = {1, 2, 3, 4, 5};

        Assert.assertEquals(3, minSubArrayLen(11, nums2));
    }

    public int minSubArrayLen(int target, int[] nums) {
        int len = nums.length;
        int left = 0;
        int right = 0;
        int sum = 0;
        int count = len + 1;
        while (right < len) {
            sum += nums[right];
            while (sum >= target) {
                count = Math.min(count, right - left + 1);
                sum -= nums[left++];
            }
            right++;
        }

        return count == len + 1 ? 0 : count;
    }
}
