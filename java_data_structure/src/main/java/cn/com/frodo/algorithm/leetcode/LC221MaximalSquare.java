package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;


/**
 * 221. 最大正方形
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：4
 * 示例 2：
 *
 *
 * 输入：matrix = [["0","1"],["1","0"]]
 * 输出：1
 * 示例 3：
 *
 * 输入：matrix = [["0"]]
 * 输出：0
 */
@Deprecated
@LCPoint(difficulty = LCPoint.Difficulty.medium,
        category = LCPoint.Category.matrix)
public class LC221MaximalSquare implements IAlgorithm {
    @Override
    public void exec() {
       char[][] matrix = {
               {'1','0','1','0','0'},
               {'1','0','1','1','1'},
               {'1','1','1','1','1'},
               {'1','0','0','1','0'}

       };

        Assert.assertEquals(4 ,maximalSquare(matrix));
    }

    /**
     * 状态转移方程：dp(i,j)=min(dp(i−1,j),dp(i−1,j−1),dp(i,j−1))+1
     */
    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        int[][] dp = new int[row][col];
        int maxSlide = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i - 1][j - 1]), dp[i][j - 1]) + 1;
                    }

                    maxSlide = Math.max(maxSlide, dp[i][j]);
                }
            }
        }

        return maxSlide * maxSlide;
    }
}
