package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

import java.util.Arrays;

/**
 *  490. 迷宫
 *  
 * 题目描述:
 * 由空地和墙组成的迷宫中有一个球。球可以向上下左右四个方向滚动，但在遇到墙壁前不会停止滚动。当球停下时，可以选择下一个方向。
 *
 * 给定球的起始位置，目的地和迷宫，判断球能否在目的地停下。
 *
 * 迷宫由一个0和1的二维数组表示。 1表示墙壁，0表示空地。你可以假定迷宫的边缘都是墙壁。起始位置和目的地的坐标通过行号和列号给出。
 *
 * 示例:
 * 示例 1:
 *
 * 输入 1: 迷宫由以下二维数组表示
 *
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 *
 * 输入 2: 起始位置坐标 (rowStart, colStart) = (0, 4)
 * 输入 3: 目的地坐标 (rowDest, colDest) = (4, 4)
 *
 * 输出: true
 *
 * 解析: 一个可能的路径是 : 左 -> 下 -> 左 -> 下 -> 右 -> 下 -> 右。
 *
 *
 *
 *
 *
 * 示例 2:
 *
 * 输入 1: 迷宫由以下二维数组表示
 *
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 *
 * 输入 2: 起始位置坐标 (rowStart, colStart) = (0, 4)
 * 输入 3: 目的地坐标 (rowDest, colDest) = (3, 2)
 *
 * 输出: false
 *
 * 解析: 没有能够使球停在目的地的路径。
 *
 *
 *
 *
 *
 * 注意:
 *
 * 迷宫中只有一个球和一个目的地。
 * 球和目的地都在空地上，且初始时它们不在同一位置。
 * 给定的迷宫不包括边界 (如图中的红色矩形), 但你可以假设迷宫的边缘都是墙壁。
 * 迷宫至少包括2块空地，行数和列数均不超过100。
 */
@AlgorithmPoint(tag = {AlgorithmPoint.Tag.leetcode, AlgorithmPoint.Tag.frequently},
        difficulty = AlgorithmPoint.Difficulty.medium,
        company = {AlgorithmPoint.Company.bytedance},
        category = AlgorithmPoint.Category.matrix)
public class LC490TheMaze implements IAlgorithm {

    public int[][] directions = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    @Override
    public void exec() {
        int[][] maze = {
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 0, 0}
        };
        int[] start = {0, 4};
        int[] destination = {4, 4};

        Assert.assertTrue(hasPath(maze, start, destination));
    }

    public boolean hasPath(int[][] maze,int[] start, int[] destination) {
        int sX = start[0];
        int sY = start[1];
        int eX = destination[0];
        int eY = destination[1];

        if (sX == eX && sY == eY) {
            System.out.println("maze = " + Arrays.deepToString(maze));
            return true;
        }

        int row = maze.length;
        int col = maze[0].length;
        for (int[] direction : directions) {
            int nX = sX + direction[0];
            int nY = sY + direction[1];
            while (nX >= 0 && nX < row && nY >= 0 && nY < col && maze[nX][nY] == 0) {
                maze[nX][nY] = 2;
                nX += direction[0];
                nY += direction[1];
            }
            nX -= direction[0];
            nY -= direction[1];
            if (nX == sX && nY == sY) {
                continue;
            }

            start[0] = nX;
            start[1] = nY;

            maze[nX][nY] = 2;
            boolean flag = hasPath(maze, start, destination);
            if (flag) {
                return true;
            }
            // 不能访问的路径需要重置回去，相当于把之前的标识符还回去
            nX = sX + direction[0];
            nY = sY + direction[1];
            while (nX >= 0 && nX < row && nY >= 0 && nY < col && maze[nX][nY] == 2) {
                maze[nX][nY] = 0;
                nX += direction[0];
                nY += direction[1];
            }
        }

        return false;
    }
}
