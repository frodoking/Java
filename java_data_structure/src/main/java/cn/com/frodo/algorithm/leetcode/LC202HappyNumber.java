package cn.com.frodo.algorithm.leetcode;


import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

/**
 * 202. 快乐数
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * <p>
 * 「快乐数」 定义为：
 * <p>
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果这个过程 结果为 1，那么这个数就是快乐数。
 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 19
 * 输出：true
 * 解释：
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * 示例 2：
 * <p>
 * 输入：n = 2
 * 输出：false
 */
@Deprecated
@AlgorithmPoint(difficulty = AlgorithmPoint.Difficulty.easy,
        company = {AlgorithmPoint.Company.bytedance},
        category = AlgorithmPoint.Category.array)
public class LC202HappyNumber implements IAlgorithm {
    @Override
    public void exec() {
        Assert.assertTrue(isHappy(19));
    }

    public boolean isHappy(int n) {
        int slow = n;
        int fast = n;
        do {
            slow = getNext(slow);
            fast = getNext(fast);
            fast = getNext(fast);
        } while (slow != fast);

        return slow == 1;
    }

    private int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            int pop = n % 10;
            sum = pop * pop;
            n = n / 10;
        }
        return sum;
    }
}
