package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. 螺旋矩阵
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 * <p>
 * <p>
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 */
@Deprecated
@AlgorithmPoint(difficulty = AlgorithmPoint.Difficulty.medium, company = AlgorithmPoint.Company.tencent,
        category = AlgorithmPoint.Category.array)
public class LC54SpiralOrder implements IAlgorithm {
    @Override
    public void exec() {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};

        Integer[] expected = {1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7};

        Integer[] act = spiralOrder(matrix).toArray(new Integer[0]);

        Assert.assertArrayEquals(expected, act);
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int row = matrix.length;
        int column = matrix[0].length;

        int circle = (Math.min(row, column) + 1) / 2;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < circle; i++) {
            spiralOrder(matrix, i, list);
        }
        return list;
    }

    public void spiralOrder(int[][] matrix, int circle, List<Integer> list) {
        int row = matrix.length;
        int column = matrix[0].length;

        for (int l = circle; l < column - circle; l++) {
            list.add(matrix[circle][l]);
        }
        if (circle == row - 1 - circle) {
            return;
        }
        for (int r = circle + 1; r < row - circle; r++) {
            list.add(matrix[r][column - circle - 1]);
        }

        if (circle == column - 1 - circle) {
            return;
        }

        for (int l = column - 1 - circle - 1; l >= circle; l--) {
            list.add(matrix[row - circle - 1][l]);
        }

        for (int r = row - 1 - circle - 1; r >= circle + 1; r--) {
            list.add(matrix[r][circle]);
        }
    }
}
