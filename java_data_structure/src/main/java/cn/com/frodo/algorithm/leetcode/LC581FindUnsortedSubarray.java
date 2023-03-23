package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

/**
 * 581. 最短无序连续子数组
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * <p>
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,6,4,8,10,9,15]
 * 输出：5
 * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3,4]
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：nums = [1]
 * 输出：0
 *
 * 进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？
 */
@AlgorithmPoint(difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category.array)
public class LC581FindUnsortedSubarray implements IAlgorithm {
    @Override
    public void exec() {
        int[] nums = {2, 6, 4, 8, 10, 9, 15};
        Assert.assertEquals(5, findUnsortedSubarray(nums));
    }

    /**
     * 进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？
     * 那说明要求顺序遍历一次就能得到答案
     */
    public int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        int max = nums[0];
        int min = nums[len - 1];
        int s = -1;
        int e = -1;
        for (int i = 0; i < len; i++) {
            if (nums[i] >= max) {
                max = nums[i];
            } else {
                e = i;
            }
            if (nums[len - i - 1] <= min) {
                min = nums[len - i - 1];
            } else {
                s = len - i - 1;
            }
        }
        System.out.println("nums = " + s + " -- " + e);
        return e - s + 1;
    }
}
