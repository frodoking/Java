package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;

/**
 * @author frodoking
 * @ClassName: LC5LongestPalindrome2
 * @date 2020/7/13
 */
public class LC5LongestPalindrome2 implements IAlgorithm {

    @Override
    public void exec() {
        String s = "babad";
        System.out.println(longestPalindrome(s));
    }

    private String longestPalindrome(String s) {
        int n = s.length();
        if (n < 2) {
            return s;
        }

        int maxLen = 0;
        int begin = 0;
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = true;
            }
        }
        char[] arrays = s.toCharArray();
        for (int i = n - 2; i > 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (arrays[i] != arrays[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }


        return s.substring(begin, maxLen + begin);
    }
}
