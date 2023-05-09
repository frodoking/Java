package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

/**
 * 695. 岛屿的最大面积
 * 给你一个大小为 m x n 的二进制矩阵 grid 。
 * <p>
 * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 * <p>
 * 岛屿的面积是岛上值为 1 的单元格的数目。
 * <p>
 * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 */
@AlgorithmPoint(tag = {AlgorithmPoint.Tag.leetcode, AlgorithmPoint.Tag.frequently},
        difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category.array,
        company = AlgorithmPoint.Company.bytedance)
public class LC695MaxAreaOfIsland implements IAlgorithm {

    @Override
    public void exec() {

    }

    public int maxAreaOfIsland(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[][] visited = new int[row][col];
        int max = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (visited[i][j] == 0 && grid[i][j] == 1) {
                    max = Math.max(max, dfs(grid, visited, i, j));
                }
            }
        }
        return max;
    }

    int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int dfs(int[][] grid, int[][] visited, int i, int j) {
        visited[i][j] = 1;
        int row = grid.length;
        int col = grid[0].length;

        int area = 1;

        for (int k = 0; k < dir.length; k++) {
            int nexti = i + dir[k][0];
            int nextj = j + dir[k][1];
            if (nexti >= 0 && nexti < row && nextj >= 0 && nextj < col
                    && grid[nexti][nextj] == 1 && visited[nexti][nextj] == 0) {
                area += dfs(grid, visited, nexti, nextj);
            }
        }
        return area;
    }

}
