package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;

/**
 * @author frodoking
 * @ClassName: LC62UniquePaths
 * @date 2022/3/8
 */
public class LC62UniquePaths implements IAlgorithm {

    @Override
    public void exec() {
        int result = uniquePaths(3,2);
        System.out.println(result);
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j];
            }
        }

        return dp[m][n];
    }

}
