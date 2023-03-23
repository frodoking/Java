package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.BinaryTreeNode;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;


/**
 * 543. 二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *
 *
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 */
@Deprecated
@AlgorithmPoint(difficulty = AlgorithmPoint.Difficulty.easy,
        category = AlgorithmPoint.Category.tree)
public class LC543DiameterOfBinaryTree implements IAlgorithm {
    @Override
    public void exec() {

    }

    public int diameterOfBinaryTree(BinaryTreeNode root) {
        if (root == null) return 0;
        dfs(root);

        return max;
    }

    int max = 0;
    public int dfs(BinaryTreeNode root) {
        if (root == null) return 0;

        int left = dfs(root.left);
        int right = dfs(root.right);

        int path =  left + right;

        max = Math.max(path, max);

        return Math.max(left, right) + 1;
    }

}
