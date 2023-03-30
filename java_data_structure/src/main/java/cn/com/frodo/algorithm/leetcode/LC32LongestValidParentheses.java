package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;

import java.util.ArrayDeque;

/**
 * 32. 最长有效括号
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * 示例 2：
 * <p>
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * 示例 3：
 * <p>
 * 输入：s = ""
 * 输出：0
 *
 * @author frodoking
 * @ClassName: LC32LongestValidParentheses
 * @date 2022/3/13
 */
@Deprecated
public class LC32LongestValidParentheses implements IAlgorithm {
    @Override
    public void exec() {
        String s = "()(())";
        int size = longestValidParentheses(s);
        System.out.println(size);
    }

    public int longestValidParentheses(String s) {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.push(-1);
        int maxSize = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                arrayDeque.push(i);
            } else {
                arrayDeque.pop();
                if (arrayDeque.isEmpty()) {
                    arrayDeque.push(i);
                } else {
                    maxSize = Math.max(maxSize, i - arrayDeque.peek());
                }
            }
        }

        return maxSize;
    }
}
