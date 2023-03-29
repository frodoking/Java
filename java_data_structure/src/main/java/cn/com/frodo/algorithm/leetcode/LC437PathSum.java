package cn.com.frodo.algorithm.leetcode;


import cn.com.frodo.BinaryTreeNode;
import cn.com.frodo.algorithm.Algorithm;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 437. 路径总和 III
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * <p>
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * 输出：3
 * 解释：和等于 8 的路径有 3 条，如图所示。
 * 示例 2：
 * <p>
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：3
 */
@AlgorithmPoint(difficulty = AlgorithmPoint.Difficulty.medium,
        company = AlgorithmPoint.Company.bytedance,
        category = AlgorithmPoint.Category.tree,
        algorithm = @Algorithm(value = Algorithm.AlgorithmEnum.dfs))
public class LC437PathSum implements IAlgorithm {
    @Override
    public void exec() {
        int[] array = {5, 4, 8, 11, BinaryTreeNode._null.data, 13, 4, 7, 2, BinaryTreeNode._null.data, BinaryTreeNode._null.data, 5, 1};
        BinaryTreeNode root = BinaryTreeNode.build(array);
        root.print();
        Assert.assertEquals(3, pathSum(root, 22));

        array = new int[]{1, BinaryTreeNode._null.data,
                2, BinaryTreeNode._null.data,
                3, BinaryTreeNode._null.data};
        root = BinaryTreeNode.build(array);
        root.print();
        counter.set(0);
        Assert.assertEquals(2, pathSum(root, 3));
    }

    private AtomicInteger counter = new AtomicInteger();

    public int pathSum(BinaryTreeNode root, int targetSum) {
        if (root == null) return 0;
        dfs(root, targetSum, counter);
        pathSum(root.left, targetSum);
        pathSum(root.right, targetSum);

        return counter.get();
    }

    public void dfs(BinaryTreeNode root, int targetSum, AtomicInteger counter) {
        if (root == null) {
            return;
        }

        if (root.data == targetSum) {
            counter.incrementAndGet();
        }

        dfs(root.left, targetSum - root.data, counter);
        dfs(root.right, targetSum - root.data, counter);
    }
}
