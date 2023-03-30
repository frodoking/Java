package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.Algorithm;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

/**
 * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 * <p>
 * 请你找出并返回只出现一次的那个数。
 * <p>
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: nums =  [3,3,7,7,10,11,11]
 * 输出: 10
 * @see LC136SingleNumber
 */
@AlgorithmPoint(
        tag = AlgorithmPoint.Tag.leetcode,
        difficulty = AlgorithmPoint.Difficulty.medium,
        company = {AlgorithmPoint.Company.bytedance},
        category = AlgorithmPoint.Category.array,
        algorithm = @Algorithm(value = Algorithm.AlgorithmEnum.dfs))
public class LC540SingleElementInASortedArray implements IAlgorithm {
    @Override
    public void exec() {
        int[] nums = {1, 1, 2, 3, 3};

        Assert.assertEquals(2, singleNonDuplicate(nums));
    }

    public int singleNonDuplicate(int[] nums) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 如果这个位置是奇数位置，那么它肯定和后边一个位置相同
            if (mid % 2 == 0) {
                // 如果和后边一个位置相同，那么表示前边部分都是成对出现
                if (nums[mid] == nums[mid + 1]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
                // 如果是偶数位置，那么肯定和前边一个位置相同
            } else {
                // 如果和前边一个位置相同，那么表示前边部分都是成对出现
                if (nums[mid] == nums[mid - 1]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
        }

        return nums[left];
    }

}
