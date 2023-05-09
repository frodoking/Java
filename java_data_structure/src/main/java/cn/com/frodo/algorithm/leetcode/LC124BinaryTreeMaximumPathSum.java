package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.BinaryTreeNode;
import cn.com.frodo.algorithm.Algorithm;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

/**
 * 124. 二叉树中的最大路径和
 * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 *
 * 路径和 是路径中各节点值的总和。
 *
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 * 示例 2：
 *
 *
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 *
 *
 * 提示：
 *
 * 树中节点数目范围是 [1, 3 * 104]
 * -1000 <= Node.val <= 1000
 */
@AlgorithmPoint(
        tag = {AlgorithmPoint.Tag.leetcode, AlgorithmPoint.Tag.frequently},
        difficulty = AlgorithmPoint.Difficulty.hard,
        category = AlgorithmPoint.Category.array,
        company = {AlgorithmPoint.Company.bytedance},
        algorithm = @Algorithm(value = Algorithm.AlgorithmEnum.backtrack))
public class LC124BinaryTreeMaximumPathSum implements IAlgorithm {

    @Override
    public void exec() {
        int[] array = {1,2,3};
        BinaryTreeNode root = BinaryTreeNode.build(array);

        Assert.assertEquals(6, maxPathSum(root));
    }


    public int maxPathSum(BinaryTreeNode root) {
        maxPathSum0(root);
        return max;
    }

    private int max = Integer.MIN_VALUE;

    /**
     * 思路：当前节点只有两种情况：
     * 1 如果当前节点同左右节点一起是最大路径，那么当前值应该是root + left.max + right.max
     * 2 如果当前节点左右不是最大路径，那么一定是当前值和左边，或者当前值和右边root + left.max, root + right.max
     *
     * 每次都获取这两种情况的最大值即可
     */
    public int maxPathSum0(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxPathSum0(root.left);
        int right = maxPathSum0(root.right);

        // 如果当前节点就是最大和的话，当前节点就是root节点，那么和一定是左右加跟
        int lmr = root.data + Math.max(left, 0) + Math.max(right, 0);
        // 当前节点非跟节点，那么需要寻找左链，或者右链的最大值
        int ret = root.data + Math.max(0, Math.max(left, right));

        // 计算当次最大值，那么可以避免再次便利节点
        max = Math.max(max, Math.max(lmr, ret));
        return ret;
    }
}
