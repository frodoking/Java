package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

import java.util.Arrays;

/**
 * 309. 最佳买卖股票时机含冷冻期
 * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​
 * <p>
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * <p>
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: prices = [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 * 示例 2:
 * <p>
 * 输入: prices = [1]
 * 输出: 0
 */
@AlgorithmPoint(difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category.digit)
public class LC309MaxProfit implements IAlgorithm {

    @Override
    public void exec() {
        int[] array = {1, 2, 3, 0, 2};
        Assert.assertEquals(3, maxProfit(array));
    }

    /**
     * f[i][0]: 手上持有股票的最大收益
     *      f[i][0] = max(f[i-1][0], f[i-1][2] - prices[i]); 第i天还持有股票，要么来至于前一天的，要么是前一天非持有且非冷冻期
     * f[i][1]: 手上不持有股票，并且处于冷冻期中的累计最大收益
     *      f[i][1] = f[i-1][0] + prices[i]; 前一天肯定是卖掉了，那么需要加回价格
     * f[i][2]: 手上不持有股票，并且不在冷冻期中的累计最大收益
     *      f[i][2] = max(f[i-1][1], f[i-1][2]);
     */
    public int maxProfit(int[] prices) {
        int size = prices.length;
        int[][] dp = new int[size][3];
        for (int i = 0; i < size; i++) {
            Arrays.fill(dp[i], 0);
        }
        // 第一天的价格肯定是负数
        dp[0][0] = -prices[0];
        for (int i = 1; i < size; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
            dp[i][1] = dp[i - 1][0] + prices[i];
            dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]);
        }

        return Math.max(dp[size - 1][1], dp[size - 1][2]);
    }
}
