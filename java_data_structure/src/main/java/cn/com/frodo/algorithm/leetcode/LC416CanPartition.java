package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.Algorithm;
import cn.com.frodo.algorithm.AlgorithmPoint;
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
@AlgorithmPoint(tag = {AlgorithmPoint.Tag.leetcode, AlgorithmPoint.Tag.frequently, AlgorithmPoint.Tag.unlearn},
        difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category._byte,
        company = AlgorithmPoint.Company.bytedance,
        algorithm = @Algorithm(value = Algorithm.AlgorithmEnum.dp))
public class LC416CanPartition implements IAlgorithm {

    @Override
    public void exec() {
        int[] nums = {1, 5, 10, 6};
        Assert.assertTrue(canPartition(nums));
        Assert.assertTrue(canPartitionWithOneRow(nums));
    }

    /**
     * 0,1 背包，问题，size是备选队列，target+1表示背包的大小，从0开始
     * dp[i][j] 表示是否能在0..i之间找到和为j的数
     * 当前值是否选择进入背包只有有两个情况：
     *  1. 不选择 那么dp[i][j]=dp[i-1][j]
     *  2. 选择 如果num[i]==j, 那么肯定dp[i][j]=true;如果小于则dp[i][j]=dp[i-1][j-nums[i]]
     *
     * @see LC322CoinChange
     */
    public boolean canPartition(int[] nums) {
        int size = nums.length;
        int sum = 0;
        int max = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }
        if ((sum & 1) == 1) {
            return false;
        }

        int target = sum / 2;
        if (max > target) {
            return false;
        }

        boolean[][] dp = new boolean[size][target + 1];

        // 先填表格第 0 行，第 1 个数只能让容积为它自己的背包恰好装满
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }

        for (int i = 1; i < size; i++) {
            for (int j = 0; j < target + 1; j++) {
                // 直接从上一行先把结果抄下来，然后再修正
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

    public boolean canPartitionWithOneRow(int[] nums) {
        int size = nums.length;
        int sum = 0;
        int max = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }
        if ((sum & 1) == 1) {
            return false;
        }

        int target = sum / 2;
        if (max > target) {
            return false;
        }

        boolean[] dp = new boolean[target + 1];
        dp[nums[0]] = true;
        for (int i = 1; i < size; i++) {
            for (int j = target; j >0 && nums[i] <= j; j--) {
                dp[j] |= dp[j - nums[i]];
            }
        }
        return dp[target];
    }
}
