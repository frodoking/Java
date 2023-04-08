package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.BinaryTreeNode;
import cn.com.frodo.algorithm.Algorithm;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 113. 路径总和 II
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * <p>
 * 叶子节点 是指没有子节点的节点。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：root = [1,2], targetSum = 0
 * 输出：[]
 */
@AlgorithmPoint(tag = {AlgorithmPoint.Tag.leetcode, AlgorithmPoint.Tag.frequently},
        difficulty = AlgorithmPoint.Difficulty.medium,
        company = AlgorithmPoint.Company.bytedance,
        category = AlgorithmPoint.Category.tree,
        algorithm = @Algorithm(value = Algorithm.AlgorithmEnum.backtrack))
public class LC113PathSumII implements IAlgorithm {

    @Override
    public void exec() {
//        int[] array = {5, 4, 8, 11, BinaryTreeNode._null.data, 13, 4, 7, 2, BinaryTreeNode._null.data, BinaryTreeNode._null.data, 5, 1};
        int[] array = {1,2};
        BinaryTreeNode root = BinaryTreeNode.build(array);
        root.print();

        List<List<Integer>> res = pathSum(root, 22);
        System.out.println(res);
    }

    public List<List<Integer>> pathSum(BinaryTreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), root, targetSum);
        return res;
    }

    public void dfs(List<List<Integer>> res, List<Integer> path, BinaryTreeNode root, int targetSum) {
        if (root == null) {
            return;
        }
        path.add(root.data);
        if (root.left == null && root.right == null && targetSum == root.data) {
            res.add(new ArrayList<>(path));
        }
        dfs(res, path, root.left, targetSum - root.data);
        dfs(res, path, root.right, targetSum - root.data);
        path.remove(path.size() - 1);
    }
}
