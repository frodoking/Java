package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.Algorithm;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

/**
 * 72. 编辑距离
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 * <p>
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 *
 * @author frodoking
 * @ClassName: MinEditDistance
 * @date 2020/7/4
 */
@Deprecated
@AlgorithmPoint(tag = {AlgorithmPoint.Tag.leetcode, AlgorithmPoint.Tag.offer, AlgorithmPoint.Tag.frequently},
        difficulty = AlgorithmPoint.Difficulty.hard,
        category = AlgorithmPoint.Category.array,
        algorithm = @Algorithm(value = Algorithm.AlgorithmEnum.dp))
public class LC72MinEditDistance implements IAlgorithm {

    @Override
    public void exec() {
        String word1 = "horse", word2 = "ros";

        Assert.assertEquals(3, minDistance(word1, word2));
        Assert.assertEquals(3, minDistanceWithDP(word1, word2));
    }

    /**
     * 思路：
     * 插入：s1(0..i),s2(0..j) 在s1中s1[i+1]中插入s2[j], 此时s1[i+1]==s2[j],那么回退化为s1[i],s2[j-1]的子问题
     * 删除：s1(0..i),s2(0..j) 在s1中s1[i]删除, 那么会退化为s1[i-1],s2[j]的子问题
     * 替换：s1(0..i),s2(0..j) 在s1中s1[i]中替换为s2[j], 此时s1[i]==s2[j],那么回退化为s1[i-1],s2[j-1]的子问题
     */
    @Algorithm(value = Algorithm.AlgorithmEnum.dfs)
    private int minDistance(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 == 0 || len2 == 0) {
            return Math.max(len1, len2);
        }

        if (s1.charAt(len1 - 1) == s2.charAt(len2 - 1)) {
            return minDistance(s1.substring(0, len1 - 1), s2.substring(0, len2 - 1));
        }

        int insert = minDistance(s1, s2.substring(0, len2 - 1));
        int del = minDistance(s1.substring(0, len1 - 1), s2);
        int replace = minDistance(s1.substring(0, len1 - 1), s2.substring(0, len2 - 1));

        return min(insert, del, replace) + 1;
    }

    @Algorithm(value = Algorithm.AlgorithmEnum.dp)
    private int minDistanceWithDP(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        // base case
        for (int i = 1; i <= m; i++)
            dp[i][0] = i;
        for (int j = 1; j <= n; j++)
            dp[0][j] = j;

        // 自底向上求解
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = min(
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1,
                            dp[i - 1][j - 1] + 1
                    );
        // 储存着整个 s1 和 s2 的最小编辑距离
        return dp[m][n];
    }

    int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
