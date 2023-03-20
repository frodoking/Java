package cn.com.frodo.algorithm.leetcode;


import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

/**
 * 240. 搜索二维矩阵 II
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * 输出：false
 */
@Deprecated
@LCPoint(difficulty = LCPoint.Difficulty.medium,
        category = LCPoint.Category.array)
public class LC240SearchMatrix implements IAlgorithm {
    @Override
    public void exec() {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int target = 6;

        int[][] matrix2 = {{5},{6}};

        Assert.assertTrue(searchMatrix(matrix2, target));
    }

    /**
     * 思路：从右上角开始找，因为始终会有大，又有小刚好在一个区间
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int column = matrix[0].length;
        int i = 0;
        int j = column - 1;

        while (i < row && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }
}
