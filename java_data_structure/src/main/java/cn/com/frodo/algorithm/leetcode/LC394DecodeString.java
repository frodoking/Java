package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 394. 字符串解码
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * <p>
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * <p>
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * <p>
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * 示例 2：
 * <p>
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * 示例 3：
 * <p>
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 * 示例 4：
 * <p>
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 *
 * @author frodoking
 * @ClassName: LC394DecodeString
 * @date 2023/3/20
 */
@AlgorithmPoint(difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category._byte, company = AlgorithmPoint.Company.bytedance)
public class LC394DecodeString implements IAlgorithm {
    @Override
    public void exec() {
        String s = "3[a2[bc]]";
        Assert.assertEquals("abcbcabcbcabcbc", decodeString(s));
    }

    public String decodeString(String s) {
        Queue<Character> queue = new LinkedList<>();
        for(char c:s.toCharArray()) {
            queue.add(c);
        }
        return dfs(queue);
    }

    /**
     * 思路，不借助辅助栈实现
     */
    private String[] dfs(String s, int index) {
        int multi = 0;
        StringBuilder sb = new StringBuilder();
        while (index < s.length()) {
            if (s.charAt(index) == '[') {
                String[] str = dfs(s, index + 1);
                index = Integer.parseInt(str[0]);
                while (multi > 0) {
                    sb.append(str[1]);
                    multi--;
                }
            } else if (s.charAt(index) == ']') {
                return new String[]{String.valueOf(index), sb.toString()};
            } else if (s.charAt(index) >= '0' && s.charAt(index) <= '9') {
                multi = multi * 10 + Integer.parseInt(String.valueOf(s.charAt(index)));
            } else {
                sb.append(s.charAt(index));
            }
            index++;
        }

        return new String[]{sb.toString()};
    }

    /**
     * 利用队列辅助更清晰
     */
    private String dfs(Queue<Character> queue) {
        int multi = 0;
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            char ch = queue.poll();
            if (ch == '[') {
                String next = dfs(queue);
                while (multi > 0) {
                    sb.append(next);
                    multi--;
                }
            } else if (ch == ']') {
                return sb.toString();
            } else if (ch >= '0' && ch <= '9') {
                multi = multi * 10 + Integer.parseInt(String.valueOf(ch));
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

}
