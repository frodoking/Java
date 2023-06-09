package cn.com.frodo.algorithm.acm;

import cn.com.frodo.algorithm.AlgorithmPoint;

import java.util.Scanner;


/**
 * 统计监控数量
 * https://blog.csdn.net/hihell/article/details/130818022
 */
@AlgorithmPoint(
        tag = AlgorithmPoint.Tag.interview,
        difficulty = AlgorithmPoint.Difficulty.easy,
        category = AlgorithmPoint.Category.matrix,
        company = AlgorithmPoint.Company.huawei)
public class StatisticalMonitoring {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            int[][] matrix = new int[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    matrix[i][j] = scanner.nextInt();
                }
            }

            System.out.println(statistical(row, col, matrix));
        }
    }

    public static int statistical(int row, int col, int[][] matrix) {
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 1) {
                    res++;
                    continue;
                }

                if ((i > 0 && matrix[i - 1][j] == 1)
                        || (i < row - 1 && matrix[i + 1][j] == 1)
                        || (j > 0 && matrix[i][j - 1] == 1)
                        || (j < col - 1 && matrix[i][j + 1] == 1)) {
                    res++;
                }
            }
        }
        return res;
    }
}
