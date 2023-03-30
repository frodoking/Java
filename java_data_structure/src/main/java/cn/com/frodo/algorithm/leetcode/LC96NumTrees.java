package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 96. 不同的二叉搜索树
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 *
 * 示例 1：
 *
 *
 * 输入：n = 3
 * 输出：5
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：1
 */
@AlgorithmPoint(difficulty = AlgorithmPoint.Difficulty.medium)
public class LC96NumTrees implements IAlgorithm {

    private Map<Integer, Integer> map= new HashMap<>();

    @Override
    public void exec() {
        System.out.println(numTrees(3));
    }

    public int numTrees(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int count = 0;
        for (int i = 1; i <= n; i++) {

            int left = numTrees(i - 1);

            int right = numTrees(n - i);

            count += left * right;
        }
        map.put(n, count);
        return count;
    }
}
