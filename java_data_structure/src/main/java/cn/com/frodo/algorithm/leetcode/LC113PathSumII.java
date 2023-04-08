package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.BinaryTreeNode;
import cn.com.frodo.algorithm.Algorithm;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

import java.util.List;

/**
 * 113. 路径总和 II
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 * 示例 2：
 *
 *
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [1,2], targetSum = 0
 * 输出：[]
 */
@AlgorithmPoint(tag = {AlgorithmPoint.Tag.leetcode, AlgorithmPoint.Tag.frequently},
        difficulty = AlgorithmPoint.Difficulty.medium,
        company = AlgorithmPoint.Company.bytedance,
        category = AlgorithmPoint.Category.tree,
        algorithm = @Algorithm(value = Algorithm.AlgorithmEnum.dfs))
public class LC113PathSumII implements IAlgorithm {

    @Override
    public void exec() {

    }

    public List<List<Integer>> pathSum(BinaryTreeNode root, int targetSum) {

        return null;
    }
}
