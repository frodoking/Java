package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.Algorithm;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 139. 单词拆分
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 * <p>
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
 * 示例 2：
 * <p>
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
 * 注意，你可以重复使用字典中的单词。
 * 示例 3：
 * <p>
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 */
@AlgorithmPoint(tag = {AlgorithmPoint.Tag.leetcode, AlgorithmPoint.Tag.frequently},
        difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category.digit,
        algorithm = @Algorithm(value = Algorithm.AlgorithmEnum.dp))
public class LC139WordBreak implements IAlgorithm {

    @Override
    public void exec() {
        String s = "catsandog";
        List<String> wordDict = Lists.newArrayList("cats", "dog", "sand", "and", "cat");
        int wordWeight = 0;
        for (String word : wordDict) {
            wordWeight = Math.max(wordWeight, word.length());
        }
//        System.out.println(dfs(s, new HashSet<>(wordDict), wordWeight));
        System.out.println(wordBreak(s, wordDict));
    }

    private HashSet<String> noExistSet = new HashSet<>();

    public boolean dfs(String s, Set<String> wordSet, int wordWeight) {
        System.out.println("s = " + s);
        if (s.length() == 0) {
            return true;
        }

        if (noExistSet.contains(s)) {
            return false;
        }

        boolean result = false;
        for (int i = 0; i < Math.min(s.length(), wordWeight); i++) {
            if (wordSet.contains(s.substring(0, i + 1))) {
                result |= dfs(s.substring(i + 1), wordSet, wordWeight);
                if (!result) {
                    noExistSet.add(s.substring(i + 1));
                }
            }
        }

        return result;
    }

    /**
     * 动态规划方案
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int size = s.length();
        int[] dp = new int[size + 1];
        Arrays.fill(dp, 0);
        dp[0] = 1;
        for (int i = 1; i <= size; i++) {
            for (int j = i - 1; j >= 0; j--) {
                // 这里核心意义在于，比如tan，tans，在有重叠词的情况下，n/s两个位置其实都能跑通。
                // 然后后边再加进来一位，都会全部再跟每一个位置联合确认是否能适配
                if (wordDict.contains(s.substring(j, i)) && dp[j] == 1) {
                    dp[i] = 1;
                    break;
                }
            }
        }
        return dp[size] == 1;
    }
}
