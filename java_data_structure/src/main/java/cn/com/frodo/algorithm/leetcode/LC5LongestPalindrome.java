package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 用状态来表示，用下三角形来表示开始点和结束点之间是否时回文，如果子串是回文，i，j相等则当前外部也是回文，对角线上一定是回文，ij距离不超过2时也是回文
 *
 * @author frodoking
 * @ClassName: LC5LongestPalindrome
 * @date 2020/7/13
 */
@Deprecated
public class LC5LongestPalindrome implements IAlgorithm {

    @Override
    public void exec() {
        String s = "ababa";
        System.out.println(longestPalindrome(s));
    }

    /**
     * 核心思想是当前规模和子模块规模
     * d[i][j] = s[i] == s[j] && d[i+1][j-1]
     * <p>
     * j-1 - (i+1) + 1 < 2 >> j-i<3 (表示子数组必须满足大于2才有意义)
     * 写得太复杂了
     */
    private String longestPalindrome(String s) {
        int len = s.length();
        // 特判
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;

        // 1. 状态定义
        // dp[i][j] 表示s[i...j] 是否是回文串

        // 2. 初始化
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] chars = s.toCharArray();
        // 3. 状态转移
        // 注意：先填左下角
        // 填表规则：先一列一列的填写，再一行一行的填，保证左下方的单元格先进行计算
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                // 头尾字符不相等，不是回文串
                if (chars[i] != chars[j]) {
                    dp[i][j] = false;
                } else {
                    // 相等的情况下
                    // 考虑头尾去掉以后没有字符剩余，或者剩下一个字符的时候，肯定是回文串
                    // 如果下一步个数小于2个则不用再比较
                    // j-1 - (i+1) + 1 < 2
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        // 状态转移
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要dp[i][j] == true 成立，表示s[i...j] 是否是回文串
                // 此时更新记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        // 4. 返回值
        return s.substring(begin, begin + maxLen);
    }

    private String longestPalindrome2(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int maxLen = 1;
        int begin = 0;
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    if (j - i + 1 > maxLen) {
                        maxLen = j - i + 1;
                        begin = i;
                    }
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
}
