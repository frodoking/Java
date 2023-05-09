package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 76. 最小子串覆盖
 * 中文English
 * 给定两个字符串 source 和 target. 求 source 中最短的包含 target 中每一个字符的子串.
 * <p>
 * 样例
 * 样例 1:
 * <p>
 * 输入: source = "abc", target = "ac"
 * 输出: "abc"
 * 样例 2:
 * <p>
 * 输入: source = "adobecodebanc", target = "abc"
 * 输出: "banc"
 * 解释: "banc" 是 source 的包含 target 的每一个字符的最短的子串.
 * 样例 3:
 * <p>
 * 输入: source = "abc", target = "aa"
 * 输出: ""
 * 解释: 没有子串包含两个 'a'.
 * 挑战
 * O(n) 时间复杂度
 * <p>
 * 注意事项
 * 如果没有答案, 返回 "".
 * 保证答案是唯一的.
 * target 可能包含重复的字符, 而你的答案需要包含至少相同数量的该字符.
 *
 * @author frodoking
 * @ClassName: LC32MinWindow
 * @date 2020/7/19
 */
@AlgorithmPoint(tag = {AlgorithmPoint.Tag.leetcode, AlgorithmPoint.Tag.frequently},
        difficulty = AlgorithmPoint.Difficulty.hard,
        company = AlgorithmPoint.Company.bytedance,
        category = AlgorithmPoint.Category.str)
public class LC76MinWindow implements IAlgorithm {

    @Override
    public void exec() {
        String source = "adobecodebanc", target = "abc";
        Assert.assertEquals(minWindow(source, target), "banc");
    }

    /**
     * 参考： https://leetcode.cn/problems/minimum-window-substring/solution/shu-ju-jie-gou-he-suan-fa-hua-dong-chuan-p6ip/
     * 思路：
     *  窗口内不包含目标字符，那么指针右移动
     *  窗口包含所有目标字符，指针左移动
     *  比较每一次包含的情况，字符长度和字符具体数据
     *
     */
    private String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0 || s.length() < t.length()) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }

        int left = 0;
        int right = 0;

        String res = "";

        //用来记录最短需要多少个字符。
        int minLength = s.length() + 1;

        while (right < s.length()) {
            char ch = s.charAt(right);
            if (map.containsKey(ch)) {
                map.put(ch, map.getOrDefault(ch, 0) - 1);
            }

            //移动到不满足条件为止
            // count == t.length 表示窗口内部刚好够满足匹配串，这个时候需要移动左边界
            while (map.values().stream().allMatch(v -> v <= 0)) {
                ch = s.charAt(left);
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    res = s.substring(left, right + 1);
                }
                // 需要把窗口的元素还回去
                if (map.containsKey(ch)) {
                    map.put(ch, map.getOrDefault(ch, 0) + 1);
                }
                left++;
            }
            right++;
        }
        return res;
    }

}
