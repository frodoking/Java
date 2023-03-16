package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.BinaryTreeNode;
import cn.com.frodo.algorithm.IAlgorithm;

/**
 * 101. 对称二叉树
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *
 */
@Deprecated
@LCPoint(difficulty = LCPoint.Difficulty.easy)
public class LC101IsSymmetric implements IAlgorithm {
    @Override
    public void exec() {

    }

    public boolean isSymmetric(BinaryTreeNode root) {
        if (root == null) {
            return true;
        }

        return dfs(root.left, root.right);
    }

    public boolean dfs(BinaryTreeNode l, BinaryTreeNode r) {
        if (l==null && r==null) {
            return true;
        }
        if (l==null || r==null) {
            return false;
        }

        if (l.data != r.data) {
            return false;
        }

        return dfs(l.left, r.right) && dfs(l.right, r.left);
    }
}
