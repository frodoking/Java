package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.BinaryTreeNode;
import cn.com.frodo.algorithm.IAlgorithm;

/**
 * 114. 二叉树展开为链表
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * <p>
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 * 示例 2：
 * <p>
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：root = [0]
 * 输出：[0]
 */
@Deprecated
@LCPoint(difficulty = LCPoint.Difficulty.medium, category = LCPoint.Category.tree)
public class LC114Flatten implements IAlgorithm {
    @Override
    public void exec() {
//        int[] root = {1, 2, 5, 3, 4, BinaryTreeNode._null.data, 6};
        int[] root = {1, BinaryTreeNode._null.data, 2, 3};
        BinaryTreeNode node = BinaryTreeNode.build(root);
        System.out.println(node);

        flatten(node);

        System.out.println(node);
    }

    public void flatten(BinaryTreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        flatten(root.left);

        if (root.left != null) {
            if (root.right == null) {
                root.right = root.left;
            } else {
                BinaryTreeNode right = root.right;

                BinaryTreeNode next = root.left;
                while (next.right != null) {
                    next = next.right;
                }
                next.right = right;

                root.right = root.left;
            }
            root.left = null;
        }

        flatten(root.right);
    }
}
