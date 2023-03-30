package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.BinaryTreeNode;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 94. 二叉树的中序遍历
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [1]
 * 输出：[1]
 *
 * @author frodoking
 * @ClassName: InorderTraversal
 * @date 2020/7/11
 */
@AlgorithmPoint(difficulty = AlgorithmPoint.Difficulty.easy)
@Deprecated
public class LC94InorderTraversal implements IAlgorithm {
    @Override
    public void exec() {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.right = new BinaryTreeNode(2);
        root.right.left = new BinaryTreeNode(3);
        inorderTraversal(root);
    }

    public List<Integer> inorderTraversal(BinaryTreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderTraversal(root, list);
        return list;
    }

    public void inorderTraversal(BinaryTreeNode node, List<Integer> result) {
        if (node.left == null && node.right == null) {
            result.add(node.data);
            return;
        }
        if (node.left != null) {
            inorderTraversal(node.left, result);
        }
        result.add(node.data);
        if (node.right != null) {
            inorderTraversal(node.right, result);
        }
    }

    public void inorderTraversal2(BinaryTreeNode binaryTreeNode) {
        BinaryTreeNode currentNode = binaryTreeNode;
        Stack<BinaryTreeNode> stack = new Stack<>();
        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }

            currentNode = stack.pop();
            System.out.println(currentNode.data);
            currentNode = currentNode.right;
        }
    }
}
