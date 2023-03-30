package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.Algorithm;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;


/**
 * 221. 最大正方形
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：4
 * 示例 2：
 * <p>
 * <p>
 * 输入：matrix = [["0","1"],["1","0"]]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：matrix = [["0"]]
 * 输出：0
 */
@AlgorithmPoint(difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category.matrix,
        algorithm = @Algorithm(value = Algorithm.AlgorithmEnum.dp))
public class LC221MaximalSquare implements IAlgorithm {
    @Override
    public void exec() {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}

        };

        Assert.assertEquals(4, maximalSquare(matrix));
        Assert.assertEquals(4, maximalSquareWithDP(matrix));
    }

    @Algorithm(value = Algorithm.AlgorithmEnum.force)
    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int slideMaxLen = 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '0') {
                    continue;
                }

                int maxLen = Math.min(row - i, col - j);
                for (int k = 1; k < maxLen; k++) {
                    if (matrix[i + k][j + k] == '0') {
                        break;
                    }

                    boolean flag = true;
                    for (int l = 0; l < k; l++) {
                        if (matrix[i + k][j + l] == '0' || matrix[i + l][j + k] == '0') {
                            flag = false;
                        }
                    }
                    if (flag) {
                        slideMaxLen = Math.max(slideMaxLen, k + 1);
                    }
                }
            }
        }

        return slideMaxLen * slideMaxLen;
    }

    /**
     * 因为从左上角往下填写，根据木桶原理，最小的值才满足都满足的情况
     * 状态转移方程：dp(i,j)=min(dp(i−1,j),dp(i−1,j−1),dp(i,j−1))+1
     */
    @Algorithm(value = Algorithm.AlgorithmEnum.dp)
    public int maximalSquareWithDP(char[][] matrix) {
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
