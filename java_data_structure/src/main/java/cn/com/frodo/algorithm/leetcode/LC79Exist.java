package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;

/**
 * 79. 单词搜索
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * 输出：true
 * 示例 3：
 * <p>
 * <p>
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * 输出：false
 *
 * @author frodoking
 * @ClassName: LC55CanJump
 * @date 2023/3/14
 */
@LCPoint(difficulty = LCPoint.Difficulty.medium)
@Deprecated
public class LC79Exist implements IAlgorithm {

    @Override
    public void exec() {
        char[][] board ={
                {'a','a','b','a','a','b'},
                {'a','a','b','b','b','a'},
                {'a','a','a','a','b','a'},
                {'b','a','b','b','a','b'},
                {'a','b','b','a','b','a'},
                {'b','a','a','a','a','b'}
        };
        String word = "bbbaabbbbbab";

        boolean exist = exist(board, word);
        System.out.println(exist);
    }

    public boolean exist(char[][] board, String word) {
        if (word.length() > board.length * board[0].length) {
            return false;
        }
        int[][] repeat = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    System.out.println(i + "-------" + j);
                    for (int k = 0; k < board.length; k++) {
                        for (int t = 0; t < board[k].length; t++) {
                            repeat[k][t] = 0;
                        }
                    }
                    if(dfs(word, 0, board, board.length, i, j, repeat)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean dfs(String word, int index, char[][] board, int matrixLen, int i, int j, int[][] repeat) {
        if (i < 0 || i > matrixLen - 1 || j < 0 || j > board[i].length - 1) {
            return false;
        }

        char ch = word.charAt(index);
        if (board[i][j] != ch) {
            return false;
        }
        if (repeat[i][j] == 1) {
            return false;
        }

        System.out.println("index = " + index + ", i = " + i + ", j = " + j);
        if (index == word.length() - 1) {
            return true;
        } else {
            repeat[i][j] = 1;
            boolean exist = dfs(word, index + 1, board, matrixLen, i - 1, j, repeat)
                    || dfs(word, index + 1, board, matrixLen, i + 1, j, repeat)
                    || dfs(word, index + 1, board, matrixLen, i, j - 1, repeat)
                    || dfs(word, index + 1, board, matrixLen, i, j + 1, repeat);
            repeat[i][j] = 0;
            return exist;
        }
    }
}
