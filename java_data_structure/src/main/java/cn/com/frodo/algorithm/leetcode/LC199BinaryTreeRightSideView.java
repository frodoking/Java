package cn.com.frodo.algorithm.leetcode;


import cn.com.frodo.BinaryTreeNode;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

import java.util.*;

/**
 * 199. 二叉树的右视图
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * <p>
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1,3,4]
 * 示例 2:
 * <p>
 * 输入: [1,null,3]
 * 输出: [1,3]
 * 示例 3:
 * <p>
 * 输入: []
 * 输出: []
 */
@AlgorithmPoint(
        difficulty = AlgorithmPoint.Difficulty.medium,
        company = {AlgorithmPoint.Company.bytedance},
        category = AlgorithmPoint.Category.tree)
public class LC199BinaryTreeRightSideView implements IAlgorithm {
    @Override
    public void exec() {
        int[] nums = {1, 2, 3, BinaryTreeNode._null.data, 5, BinaryTreeNode._null.data, 4};

        BinaryTreeNode root = BinaryTreeNode.build(nums);

        Integer[] expected = {1, 3, 4};

        List<Integer> integers = rightSideView(root);

        Assert.assertArrayEquals(expected, integers.toArray(new Integer[0]));
    }

    public List<Integer> rightSideView(BinaryTreeNode root) {
        if (root == null) return Collections.emptyList();
        Deque<BinaryTreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);

        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            res.add(queue.peekLast().data);

            int levelSize = queue.size();
            while (levelSize > 0) {
                BinaryTreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                levelSize--;
            }
        }

        return res;

    }

}
