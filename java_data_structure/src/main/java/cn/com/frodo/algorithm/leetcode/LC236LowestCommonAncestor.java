package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.BinaryTreeNode;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

/**
 * 236. 二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出：5
 * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
 * 示例 3：
 * <p>
 * 输入：root = [1,2], p = 1, q = 2
 * 输出：1
 */
@AlgorithmPoint(
        tag = {AlgorithmPoint.Tag.leetcode,AlgorithmPoint.Tag.frequently},
        difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category.tree)
public class LC236LowestCommonAncestor implements IAlgorithm {
    @Override
    public void exec() {
        int[] nums = {3, 5, 1, 6, 2, 0, 8, BinaryTreeNode._null.data, BinaryTreeNode._null.data, 7, 4};
        BinaryTreeNode root = BinaryTreeNode.build(nums);
        root.print();

        Assert.assertEquals(3, lowestCommonAncestor(root, root.left, root.right).data);
        Assert.assertEquals(5, lowestCommonAncestor(root, root.left, root.left.right.left).data);
    }

    /**
     * 非常重要的思路是如果左右最小公共祖先节点都不为空的时候，则当前节点就是最近公共祖先节点
     * 即：若其左子节点 root.left 和右子节点 root.right 都不是 p,q 的公共祖先，则称 root 是 “最近的公共祖先” 。
     * <p>
     * 如果有一个不在这颗树上，那这个方法就不奏效
     */
    public BinaryTreeNode lowestCommonAncestor(BinaryTreeNode root, BinaryTreeNode p, BinaryTreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        BinaryTreeNode left = lowestCommonAncestor(root.left, p, q);
        BinaryTreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null) return right;
        if (right == null) return left;

        return root;
    }
}
