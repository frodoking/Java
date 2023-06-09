package cn.com.frodo.algorithm.acm;

import cn.com.frodo.algorithm.AlgorithmPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 200分题
 * 寻找最大价值的矿堆
 *
 * https://dream.blog.csdn.net/article/details/130861674
 */
@AlgorithmPoint(
        tag = AlgorithmPoint.Tag.interview,
        difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category.thread,
        company = AlgorithmPoint.Company.huawei)
public class FindingTheMostValuableOrePiles {

    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int rowIndex = 0;
            int weight = 0;
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 300; i++) {
                String inputLine = scanner.nextLine();
                if (inputLine == null || inputLine.isEmpty()) break;
                list.add(inputLine);
                weight = Math.max(weight, inputLine.length());
                rowIndex++;
            }

            int[][] matrix = new int[rowIndex][weight];
            for (int i = 0; i < rowIndex; i++) {
                for (int j = 0; j < list.get(i).length(); j++) {
                    matrix[i][j] = Character.getNumericValue(list.get(i).charAt(j));
                }
            }

            boolean[][] visited = new boolean[rowIndex][weight];
            int maxValue = 0;

            for (int i = 0; i < rowIndex; i++) {
                for (int j = 0; j < weight; j++) {
                    if (matrix[i][j] > 0 && !visited[i][j]) {
                        int value = dfs(i, j, matrix, visited);
                        maxValue = Math.max(maxValue, value);
                    }
                }
            }

            System.out.println(maxValue);
        }
    }

    private static int dfs(int x, int y, int[][] matrix, boolean[][] visited) {
        if (x < 0 || x > matrix.length-1 || y < 0 || y > matrix[0].length-1 || visited[x][y] ||
                matrix[x][y] == 0) {
            return 0;
        }

        visited[x][y] = true;
        int value = matrix[x][y];

        for (int[] direction : DIRECTIONS) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            value += dfs(newX, newY, matrix, visited);
        }

        return value;
    }
}



