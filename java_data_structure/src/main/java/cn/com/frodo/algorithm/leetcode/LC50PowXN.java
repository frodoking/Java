package cn.com.frodo.algorithm.leetcode;


import cn.com.frodo.algorithm.Algorithm;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

/**
 * 50. Pow(x, n)
 * 实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，xn ）。
 * <p>
 * 示例 1：
 * <p>
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * 示例 2：
 * <p>
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * 示例 3：
 * <p>
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 */
@Deprecated
@AlgorithmPoint(tag = {AlgorithmPoint.Tag.leetcode, AlgorithmPoint.Tag.frequently},
        difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category.digit,
        algorithm = @Algorithm(value = Algorithm.AlgorithmEnum.dfs))
public class LC50PowXN implements IAlgorithm {

    @Override
    public void exec() {
        double x = 2.00000;
        int n = 10;

        Assert.assertEquals(1024.00000, myPow(x, n), 2.0);
    }

    public double myPow(double x, int n) {
        return n > 0 ? quickMul(x, n) : 1.0 / quickMul(x, -n);
    }

    private double quickMul(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        double mul = quickMul(x, n >> 1);
        return n % 2 == 0 ? mul * mul : mul * mul * x;
    }

}
