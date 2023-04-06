package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

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
        System.out.println(minWindow(source, target));
    }

    /**
     * Java中将一个字符与对应Ascii码互转
     * 1 byte = 8bit 可以表示 0-127
     * <p>
     * 0-9对应Ascii 48-57
     * A-Z 65-90
     * a-z 97-122
     * 第33～126号(共94个)是字符，其中第48～57号为0～9十个阿拉伯数字
     */
    private String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0 || s.length() < t.length()) {
            return "";
        }
        //用来统计t中每个字符出现次数
        int[] needs = new int[128];
        //用来统计滑动窗口中每个字符出现次数
        int[] window = new int[128];

        for (int i = 0; i < t.length(); i++) {
            needs[t.charAt(i)]++;
        }

        int left = 0;
        int right = 0;

        String res = "";

        //目前有多少个字符
        int count = 0;

        //用来记录最短需要多少个字符。
        int minLength = s.length() + 1;

        while (right < s.length()) {
            char ch = s.charAt(right);
            window[ch]++;
            if (needs[ch] > 0 && needs[ch] >= window[ch]) {
                count++;
            }

            //移动到不满足条件为止
            // count == t.length 表示窗口内部刚好够满足匹配串，这个时候需要移动左边界
            while (count == t.length()) {
                ch = s.charAt(left);
                // 这里移动左边界，直到while条件
                if (needs[ch] > 0 && needs[ch] >= window[ch]) {
                    count--;
                }
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    res = s.substring(left, right + 1);
                }
                window[ch]--;
                left++;
            }
            right++;

        }
        return res;
    }

}
