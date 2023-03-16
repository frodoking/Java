package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.Arrays;
import cn.com.frodo.algorithm.IAlgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 48. 旋转图像
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * <p>
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 * 示例 2：
 * <p>
 * <p>
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 *
 * @author frodoking
 * @ClassName: LC48Rotate
 * @date 2023/3/14
 */
@Deprecated
public class LC48Rotate implements IAlgorithm {

    @Override
    public void exec() {
        int[][] matrix = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        Arrays.show(matrix, "LC48Rotate");
        rotate(matrix);
        Arrays.show(matrix, "LC48Rotate");
    }

    public void rotate(int[][] matrix) {
        int size = matrix.length;
        for (int i = 0; i < size / 2; i++) {
            for (int j = 0; j < size; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[size - i - 1][j];
                matrix[size - i - 1][j] = temp;
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
