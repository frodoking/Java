package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.BinaryTreeNode;
import cn.com.frodo.algorithm.IAlgorithm;
import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author frodoking
 * @ClassName: LC102LevelOrder
 * @date 2020/7/11
 */
public class LC102LevelOrder implements IAlgorithm {
    @Override
    public void exec() {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(4);
        root.right = new BinaryTreeNode(2);
        root.right.left = new BinaryTreeNode(3);
        Object o = levelOrder(root);
        System.out.println(o.toString());
    }

    public List<List<BinaryTreeNode>> levelOrder(BinaryTreeNode binaryTreeNode) {
        List<List<BinaryTreeNode>> result = Lists.newArrayList();
        if (binaryTreeNode == null) {
            return result;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(binaryTreeNode);
        while (!queue.isEmpty()) {
            int count = queue.size();
            List<BinaryTreeNode> levelList = Lists.newArrayList();
            while (count > 0) {
                BinaryTreeNode tmpNode = queue.poll();
                levelList.add(tmpNode);
                if (tmpNode.left != null) {
                    queue.add(tmpNode.left);
                }
                if (tmpNode.right != null) {
                    queue.add(tmpNode.right);
                }
                count--;
            }
            result.add(levelList);
        }

        return result;
    }
}
