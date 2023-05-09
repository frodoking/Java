package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.Algorithm;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

import java.util.HashMap;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * @author frodoking
 * @ClassName: LC3LengthOfLongestSubstring
 * @date 2020/7/13
 */
@Deprecated
@AlgorithmPoint(
        tag = {AlgorithmPoint.Tag.leetcode, AlgorithmPoint.Tag.interview, AlgorithmPoint.Tag.frequently},
        difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category.str,
        algorithm = @Algorithm(value = Algorithm.AlgorithmEnum.slidingWindow))
public class LC3LengthOfLongestSubstring implements IAlgorithm {

    @Override
    public void exec() {
        String s = "pwwke";
        Assert.assertEquals(3, lengthOfLongestSubstring(s));
    }

    /**
     * 思路：滑动窗口, 当字符出现，那么左指针左移，否则右边指针右移
     */
    private int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int res = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            char t = s.charAt(i);
            if (map.containsKey(t)) {
                left = Math.max(map.get(t) + 1, left);
            }
            map.put(t, i);
            res = Math.max(res, i - left + 1);
        }
        return res;
    }
}
