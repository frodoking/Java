package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

/**
 * 416. 分割等和子集
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 *
 * @author frodoking
 * @ClassName: LC416CanPartition
 * @date 2023/3/21
 */
@LCPoint(difficulty = LCPoint.Difficulty.medium,
        category = LCPoint.Category._byte, company = LCPoint.Company.bytedance)
public class LC416CanPartition implements IAlgorithm {

    @Override
    public void exec() {
        int[] nums = {1, 5, 10, 6};
        Assert.assertTrue(canPartition(nums));
    }

    public boolean canPartition(int[] nums) {
        int size = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }

        int target = sum / 2;

        boolean[][] dp = new boolean[size][target + 1];

        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }

        for (int i = 1; i < size; i++) {
            for (int j = 0; j < target + 1; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j == nums[i]) {
                    dp[i][j] = true;
                } else if(nums[i] < j) {
                    dp[i][j] = dp[i - 1][j - nums[i]] || dp[i - 1][j];
                }
            }
        }
        return dp[size - 1][target];
    }
}
