package cn.com.frodo.algorithm.leetcode;


import cn.com.frodo.algorithm.Algorithm;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 564. 寻找最近的回文数
 * 给定一个表示整数的字符串 n ，返回与它最近的回文整数（不包括自身）。如果不止一个，返回较小的那个。
 * <p>
 * “最近的”定义为两个整数差的绝对值最小。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: n = "123"
 * 输出: "121"
 * 示例 2:
 * <p>
 * 输入: n = "1"
 * 输出: "0"
 * 解释: 0 和 2是最近的回文，但我们返回最小的，也就是 0。
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= n.length <= 18
 * n 只由数字组成
 * n 不含前导 0
 * n 代表在 [1, 1018 - 1] 范围内的整数
 */
@AlgorithmPoint(
        tag = AlgorithmPoint.Tag.leetcode,
        difficulty = AlgorithmPoint.Difficulty.hard,
        company = {AlgorithmPoint.Company.bytedance},
        category = AlgorithmPoint.Category.digit,
        algorithm = @Algorithm(value = Algorithm.AlgorithmEnum.dfs))
public class LC564FindTheClosestPalindrome implements IAlgorithm {

    @Override
    public void exec() {
        Assert.assertEquals("121", nearestPalindromic("123"));
        Assert.assertEquals("121", nearestPalindromic("120"));
        Assert.assertEquals("1221", nearestPalindromic("1213"));
        Assert.assertEquals("9", nearestPalindromic("10"));
        Assert.assertEquals("9", nearestPalindromic("11"));
    }

    /**
     * 核心思路：
     * 1.比当前数字长度小一位的最大值，比当前数字大一位的最小值，比如123的99，1001
     * 2. 找到前一半的前一个数、当前、后一个数的回文数字。联合这5个数的差值，得到最小差值的结果
     */
    public String nearestPalindromic(String n) {
        long selfNumber = Long.parseLong(n), ans = -1;
        List<Long> candidates = getCandidates(n);
        for (long candidate : candidates) {
            if (candidate != selfNumber) {
                if (ans == -1 ||
                        Math.abs(candidate - selfNumber) < Math.abs(ans - selfNumber) ||
                        Math.abs(candidate - selfNumber) == Math.abs(ans - selfNumber) && candidate < ans) {
                    ans = candidate;
                }
            }
        }
        return Long.toString(ans);
    }

    public List<Long> getCandidates(String n) {
        int len = n.length();
        List<Long> candidates = new ArrayList<Long>() {{
            add((long) Math.pow(10, len - 1) - 1);
            add((long) Math.pow(10, len) + 1);
        }};
        long selfPrefix = Long.parseLong(n.substring(0, (len + 1) / 2));
        for (long i = selfPrefix - 1; i <= selfPrefix + 1; i++) {
            StringBuffer sb = new StringBuffer();
            String prefix = String.valueOf(i);
            sb.append(prefix);
            StringBuffer suffix = new StringBuffer(prefix).reverse();
            sb.append(suffix.substring(len & 1));
            String candidate = sb.toString();
            try {
                candidates.add(Long.parseLong(candidate));
            } catch (NumberFormatException ex) {
                continue;
            }
        }
        return candidates;
    }
}
