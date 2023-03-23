package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.BinaryTreeNode;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

/**
 * 337. 打家劫舍 III
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
 * <p>
 * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 * <p>
 * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * <p>
 * 输入: root = [3,2,3,null,3,null,1]
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
 * 示例 2:
 * <p>
 * <p>
 * <p>
 * 输入: root = [3,4,5,1,3,null,1]
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
 */
@AlgorithmPoint(difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category.tree)
public class LC337Rob implements IAlgorithm {

    @Override
    public void exec() {

    }

    public int rob(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        // 偷当前节点，那么最大收益
        int profit = root.data;
        if (root.left != null) {
            profit += rob(root.left.left) + rob(root.left.right);
        }

        if (root.right != null) {
            profit += rob(root.right.left) + rob(root.right.right);
        }

        // 不偷当前节点，那么最大收益
        int profit2 = rob(root.left) + rob(root.right);

        return Math.max(profit, profit2);
    }

    public int rob2(BinaryTreeNode root) {
        int[] a = rob20(root);
        return Math.max(a[0],a[1]);
    }

    private int[] rob20(BinaryTreeNode root) {
        if (root==null) {
            return new int[]{0,0};
        }

        int[] l = rob20(root.left);
        int[] r = rob20(root.right);

        int selected = root.data + l[1] + r[1];
        int notSelected = Math.max(l[0],l[1]) + Math.max(r[0], r[1]);

        return new int[]{selected, notSelected};
    }
}
