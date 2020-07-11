package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.BinaryTreeNode;
import cn.com.frodo.algorithm.IAlgorithm;

import java.util.Stack;

/**
 * @author frodoking
 * @ClassName: InorderTraversal
 * @date 2020/7/11
 */
public class LC94InorderTraversal implements IAlgorithm {
    @Override
    public void exec() {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.right = new BinaryTreeNode(2);
        root.right.left = new BinaryTreeNode(3);
        inorderTraversal2(root);
    }

    public void inorderTraversal(BinaryTreeNode binaryTreeNode) {
        if (binaryTreeNode.left == null && binaryTreeNode.right == null) {
            System.out.println(binaryTreeNode.data);
            return;
        }
        if (binaryTreeNode.left != null) {
            inorderTraversal(binaryTreeNode.left);
            System.out.println(binaryTreeNode.data);
        }
        if (binaryTreeNode.right != null) {
            System.out.println(binaryTreeNode.data);
            inorderTraversal(binaryTreeNode.right);
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
