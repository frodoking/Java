package cn.com.frodo.algorithm.leetcode;


import cn.com.frodo.algorithm.Algorithm;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

/**
 * 85. 最大矩形
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：6
 * 解释：最大矩形如上图所示。
 * 示例 2：
 * <p>
 * 输入：matrix = []
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：matrix = [["0"]]
 * 输出：0
 * 示例 4：
 * <p>
 * 输入：matrix = [["1"]]
 * 输出：1
 * 示例 5：
 * <p>
 * 输入：matrix = [["0","0"]]
 * 输出：0
 * <p>
 * https://leetcode.cn/problems/maximal-rectangle/
 */
@AlgorithmPoint(
        tag = AlgorithmPoint.Tag.leetcode,
        difficulty = AlgorithmPoint.Difficulty.hard,
        category = AlgorithmPoint.Category.matrix,
        algorithm = @Algorithm(value = Algorithm.AlgorithmEnum.monotonicStack))
public class LC85MaximalRectangle implements IAlgorithm {

    @Override
    public void exec() {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

        Assert.assertEquals(6, maximalRectangle(matrix));
    }

    public int maximalRectangle(char[][] matrix) {
        int row = matrix.length;
        int column = matrix[0].length;
        int[][] newMatrix = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                newMatrix[i][j] = matrix[i][j] - '0';
            }
        }

        return maximalRectangle(newMatrix);
    }

    /**
     * @see LC84LargestRectangleInHistogram
     */
    @Algorithm(value = Algorithm.AlgorithmEnum.monotonicStack)
    public int maximalRectangle(int[][] matrix) {
        int row = matrix.length;
        int column = matrix[0].length;

        LC84LargestRectangleInHistogram largestRectangleInHistogram = new LC84LargestRectangleInHistogram();
        int ans = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (i == 0) {
                    continue;
                }
                matrix[i][j] += matrix[i - 1][j];
            }
            int maxArea = largestRectangleInHistogram.largestRectangleArea2(matrix[i]);
            ans = Math.max(ans, maxArea);
        }
        return ans;
    }
}
