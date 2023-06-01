package cn.com.frodo.algorithm.other;

import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

/**
 * 岛屿上有0 水，1 银矿，2金矿， 求最大价值岛屿
 */
@AlgorithmPoint(
        tag = AlgorithmPoint.Tag.interview,
        difficulty = AlgorithmPoint.Difficulty.hard,
        category = AlgorithmPoint.Category.thread,
        company = AlgorithmPoint.Company.huawei)
public class MaxGoldArea implements IAlgorithm {
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
                if (visited[i][j] == 0 && grid[i][j] != 0) {
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

        int area = grid[i][j];

        for (int k = 0; k < dir.length; k++) {
            int nexti = i + dir[k][0];
            int nextj = j + dir[k][1];
            if (nexti >= 0 && nexti < row && nextj >= 0 && nextj < col
                    && grid[nexti][nextj] != 0 && visited[nexti][nextj] == 0) {
                area += dfs(grid, visited, nexti, nextj);
            }
        }
        return area;
    }
}
