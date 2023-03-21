package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

import java.util.*;

/**
 * 438. 找到字符串中所有字母异位词
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * <p>
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * 示例 2:
 * <p>
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 */
@Deprecated
@LCPoint(difficulty = LCPoint.Difficulty.medium, company = LCPoint.Company.bytedance,
        category = LCPoint.Category._byte)
public class LC438FindAnagrams implements IAlgorithm {
    @Override
    public void exec() {
        String s = "cbaebabacd";
        String p = "abc";
        System.out.println(findAnagrams(s, p));
    }

    /**
     * 思路: 维护一个滑动窗口，同时如果滑动窗口内字符类型个数和目标一致，那么就成立
     */
    public List<Integer> findAnagrams(String s, String p) {
        int windowSize = p.length();
        int sSize = s.length();
        if (windowSize > sSize) {
            return new ArrayList<>();
        }
        int[] targetArray = new int[26];
        Arrays.fill(targetArray, 0);
        for (Character c : p.toCharArray()) {
            targetArray[c - 'a'] += 1;
        }

        int[] windowArray = new int[26];
        Arrays.fill(windowArray, 0);
        List<Integer> res = new ArrayList<>();

        // 构建window的初始数据
        for (int j = 0; j < windowSize - 1; j++) {
            windowArray[s.charAt(j) - 'a'] += 1;
        }

        // 从S的window第一个位置开始
        for (int i = windowSize - 1; i < sSize; i++) {
            // 添加第一个进入window的字符
            windowArray[s.charAt(i) - 'a'] += 1;

            boolean isEqual = true;
            for (int j = 0; j < 26; j++) {
                isEqual &= targetArray[j] == windowArray[j];
            }
            if (isEqual) {
                res.add(i - windowSize + 1);
            }

            // 从window里移除首位字符
            windowArray[s.charAt(i - windowSize + 1) - 'a'] -= 1;
        }

        return res;
    }

}
