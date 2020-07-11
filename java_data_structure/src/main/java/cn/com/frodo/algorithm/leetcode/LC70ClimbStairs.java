package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;

/**
 * @author frodoking
 * @ClassName: ClimbStairs
 * @date 2020/7/10
 */
public class LC70ClimbStairs implements IAlgorithm {

    @Override
    public void exec() {
        int result = climbStairs(3);
        System.out.println(result);
    }

    private int climbStairs(int n) {
        int[] dp = new int[n+2];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            int cur = dp[0] + dp[1];
            dp[0] = dp[1];
            dp[1] = cur;
        }
        return dp[1];
    }
}
