package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.BinaryTreeNode;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

/**
 * 98. 验证二叉搜索树
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 有效 二叉搜索树定义如下：
 * <p>
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 */
@Deprecated
@AlgorithmPoint(difficulty = AlgorithmPoint.Difficulty.medium)
public class LC98IsValidBST implements IAlgorithm {

    @Override
    public void exec() {

    }

    long pre = Long.MIN_VALUE;

    public boolean isValidBST2(BinaryTreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST2(root.left)) {
            return false;
        }

        // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
        if (pre >= root.data) {
            return false;
        }

        pre = root.data;

        return isValidBST2(root.right);
    }

    public boolean isValidBST(BinaryTreeNode root) {
        if (root.left != null) {
            if (isValidBST(root.left)) {
                if (max(root.left) >= root.data) return false;
            } else {
                return false;
            }
        }
        if (root.right != null) {
            if (isValidBST(root.right)) {
                if (min(root.right) <= root.data) return false;
            } else {
                return false;
            }
        }
        return true;
    }

    public int min(BinaryTreeNode root) {
        if (root.left != null) {
            return Math.min(min(root.left), root.data);
        }
        return root.data;
    }

    public int max(BinaryTreeNode root) {
        if (root.right != null) {
            return Math.max(max(root.right), root.data);
        }
        return root.data;
    }


}
