package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.Algorithm;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

import java.util.*;

/**
 * 763. 划分字母区间
 * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
 * <p>
 * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
 * <p>
 * 返回一个表示每个字符串片段的长度的列表。
 * <p>
 * 示例 1：
 * 输入：s = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca"、"defegde"、"hijhklij" 。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 这样的划分是错误的，因为划分的片段数较少。
 * 示例 2：
 * <p>
 * 输入：s = "eccbbbbdec"
 * 输出：[10]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 500
 * s 仅由小写英文字母组成
 */
@AlgorithmPoint(tag = {AlgorithmPoint.Tag.leetcode, AlgorithmPoint.Tag.frequently},
        difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category.str)
public class LC763PartitionLabels implements IAlgorithm {
    @Override
    public void exec() {
        String s = "ababcbacadefegdehijhklij";
        List<Integer> res = partitionLabels2(s);
        Integer[] resA = res.toArray(new Integer[0]);
        System.out.println(Arrays.toString(resA));
    }

    private Set<Character> set = new HashSet<>();
    /**
     * 不好的方法
     */
    @Algorithm(value = Algorithm.AlgorithmEnum.force)
    public List<Integer> partitionLabels(String s) {
        set.clear();
        int len = s.length();
        if (len == 0) {
            return new ArrayList<>();
        }
        int nextIndex = 0;
        Set<Character> mset = new HashSet<>();
        for (int i = 0; i < len; i++) {
            char cur = s.charAt(i);
            if (set.isEmpty()) {
                set.add(cur);
                continue;
            }
            mset.add(cur);
            if (set.contains(cur)) {
                nextIndex = i;
                set.addAll(mset);
                mset.clear();
            }
        }

        System.out.println(s.substring(0, nextIndex + 1));

        List<Integer> res = partitionLabels(s.substring(nextIndex + 1));
        res.add(0, nextIndex + 1);
        return res;
    }

    /**
     * 存储某一个字符的最远下标
     */
    public List<Integer> partitionLabels2(String s) {
        int len = s.length();
        if (len == 0) {
            return new ArrayList<>();
        }

        int[] dp = new int[26];
        for (int i = 0; i < len; i++) {
            dp[s.charAt(i) - 'a'] = i;
        }

        int index = 0;
        int start = 0;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            int maxIndex = dp[s.charAt(i) - 'a'];
            index = Math.max(index, maxIndex);
            if (index == i) {
                res.add(index - start + 1);
                start = index + 1;
            }
        }

        return res;
    }

}
