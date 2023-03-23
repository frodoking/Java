package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 279. 完全平方数
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 * <p>
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 * 示例 2：
 * <p>
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 */
@Deprecated
@AlgorithmPoint(difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category.array)
public class LC279NumSquares implements IAlgorithm {

    @Override
    public void exec() {
        Assert.assertEquals(3, numSquares(12));
    }

    private Map<Integer, Integer> cache = new HashMap<>();
    public int numSquares(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        int sqrt = (int) Math.sqrt(n);
        int minLevel = n;
        for (int i = 1; i <= sqrt; i++) {
            minLevel = Math.min(minLevel, numSquares(n - i * i) + 1);
        }

        cache.put(n, minLevel);

        return minLevel;
    }

}
