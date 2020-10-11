package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;

/**
 * 121. 买卖股票的最佳时机
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 注意：你不能在买入股票前卖出股票。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 * <p>
 * 重点就是保证单调递增的往上加即可
 * 当前两点之间降下去的数据不能用之前的递增来弥补的时候需要从0开始计算
 *
 * @author frodoking
 * @ClassName: LC121MaxProfit
 * @date 2020/10/11
 */
public class LC121MaxProfit implements IAlgorithm {
    @Override
    public void exec() {
//        int[] array = {7, 1, 5, 3, 6, 4};
        int[] array = {7, 6, 4, 3, 1};
        System.out.println(maxProfit(array));
    }

    private int maxProfit(int[] array) {
        int last = 0;
        int profit = 0;
        for (int i = 0; i < array.length - 1; i++) {
            last = Math.max(0, last + array[i + 1] - array[i]);
            profit = Math.max(last, profit);
        }
        return profit;
    }
}
