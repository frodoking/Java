package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.BinaryTreeNode;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

/**
 * 538. 把二叉搜索树转换为累加树
 * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
 * <p>
 * 提醒一下，二叉搜索树满足下列约束条件：
 * <p>
 * 节点的左子树仅包含键 小于 节点键的节点。
 * 节点的右子树仅包含键 大于 节点键的节点。
 * 左右子树也必须是二叉搜索树。
 * 注意：本题和 1038: https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/ 相同
 * <p>
 * 示例 1：
 * <p>
 * 输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 * 输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 * 示例 2：
 * <p>
 * 输入：root = [0,null,1]
 * 输出：[1,null,1]
 * 示例 3：
 * <p>
 * 输入：root = [1,0,2]
 * 输出：[3,3,2]
 * 示例 4：
 * <p>
 * 输入：root = [3,2,4,1]
 * 输出：[7,9,4,10]
 */
@Deprecated
@AlgorithmPoint(difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category.tree)
public class LC538ConvertBST implements IAlgorithm {

    @Override
    public void exec() {

    }

    int num = 0;

    /**
     * 反中序遍历
     */
    public BinaryTreeNode convertBST(BinaryTreeNode root) {
        if (root == null) {
            return null;
        }
        convertBST(root.right);
        num += root.data;
        root.data = num;
        convertBST(root.left);

        return root;
    }

}
