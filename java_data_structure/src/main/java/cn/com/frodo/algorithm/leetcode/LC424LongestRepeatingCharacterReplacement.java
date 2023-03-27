package cn.com.frodo.algorithm.leetcode;


import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

/**
 * 424. 替换后的最长重复字符
 * 给你一个字符串 s 和一个整数 k 。你可以选择字符串中的任一字符，并将其更改为任何其他大写英文字符。该操作最多可执行 k 次。
 * <p>
 * 在执行上述操作后，返回包含相同字母的最长子字符串的长度。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ABAB", k = 2
 * 输出：4
 * 解释：用两个'A'替换为两个'B',反之亦然。
 * 示例 2：
 * <p>
 * 输入：s = "AABABBA", k = 1
 * 输出：4
 * 解释：
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 */
@AlgorithmPoint(difficulty = AlgorithmPoint.Difficulty.medium,
        company = {AlgorithmPoint.Company.bytedance},
        category = AlgorithmPoint.Category.array)
public class LC424LongestRepeatingCharacterReplacement implements IAlgorithm {
    @Override
    public void exec() {
        String s = "AABABBA";

        Assert.assertEquals(4, characterReplacement(s, 1));
    }

    /**
     * 核心要点：
     * <p>
     * 1、map记录的是【窗口里的】字符出现的次数，是窗口里的！窗口里的！所以左窗口右移后，记得将移出去的元素个数-1
     * 2、我们的目的就是让窗口尽可能扩张，有K个字符的容错机会，容错机会肯定要用给map中出现次数最多的字符才有机会让窗口扩张
     * 3、就算某一刻你发现框里元素都不一样，但不要怀疑，因为它曾经辉煌过,它会一直呈现它窗口最大时候的状态
     * <p>
     * 现在我是这样：aaab【cdef】，是因为曾经我：【aaab】cdef （容错次数K=1）
     */
    public int characterReplacement(String s, int k) {
        int len = s.length();

        int i = 0;
        int maxCount = 0;
        int maxLen = 0;
        int[] freq = new int[26];

        for (int j = 0; j < len; j++) {
            int index = s.charAt(j) - 'A';
            freq[index]++;

            maxCount = Math.max(maxCount, freq[index]);
            if (j - i > maxCount + k) {
                freq[s.charAt(i) - 'A']--;
                i++;
            }
            maxLen = Math.max(maxLen, j - i);
        }
        return maxLen;
    }

}
