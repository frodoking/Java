package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

/**
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * 输出：3
 *
 * @author frodoking
 * @ClassName: LC200NumIslands
 * @date 2023/3/17
 */
@Deprecated
@AlgorithmPoint(difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category.array)
public class LC200NumIslands implements IAlgorithm {

    @Override
    public void exec() {
        String[][] grid = {
                {"1", "1", "1", "1", "0"},
                {"1", "1", "0", "1", "0"},
                {"1", "1", "0", "0", "0"},
                {"0", "0", "0", "0", "0"}
        };

        Assert.assertEquals(1, numIslands(grid));
    }

    public int numIslands(String[][] grid) {
        int row = grid.length;
        int colum = grid[0].length;

        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colum; j++) {
                if (grid[i][j] == "1") {
                    dfsGrid(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    public void dfsGrid(String[][] grid, int r, int l) {
        int row = grid.length;
        int colum = grid[0].length;
        if (r < 0 || r > row - 1 || l < 0 || l > colum - 1 || grid[r][l] == "0") {
            return;
        }

        grid[r][l] = "0";

        dfsGrid(grid, r - 1, l);
        dfsGrid(grid, r + 1, l);
        dfsGrid(grid, r, l - 1);
        dfsGrid(grid, r, l + 1);
    }
}
