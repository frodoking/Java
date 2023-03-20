package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.BinaryTreeNode;
import cn.com.frodo.algorithm.IAlgorithm;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 输出: [3,9,20,null,null,15,7]
 * 示例 2:
 * <p>
 * 输入: preorder = [-1], inorder = [-1]
 * 输出: [-1]
 */
@Deprecated
@LCPoint(difficulty = LCPoint.Difficulty.medium, category = LCPoint.Category.tree)
public class LC105BuildTree implements IAlgorithm {

    @Override
    public void exec() {
        int[] preorder = new int[]{1, 2, 3};
        int[] inorder = new int[]{1, 2, 3};
        BinaryTreeNode binaryTreeNode = buildTree(preorder, inorder);
        System.out.println(binaryTreeNode);
    }

    public BinaryTreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public BinaryTreeNode buildTree(int[] preorder, int ps, int pe, int[] inorder, int is, int ie) {
        BinaryTreeNode node = new BinaryTreeNode(preorder[ps]);
        // 如果搜索完了，即后续不再有节点，就是叶子节点
        if (ps == pe || is == ie) {
            return node;
        }
        // 找中序的位置
        int im = 0;
        for (int i = is; i <= ie; i++) {
            if (inorder[i] == preorder[ps]) {
                im = i;
            }
        }

        // 通过中序的下标，确定前部分个数
        int pSize = im - is;

        // 如果前部分有值，那么继续搜索
        if (im > is) {
            node.left = buildTree(preorder, ps + 1, ps + pSize, inorder, is, im - 1);
        }

        // 如果后部分有值，那么继续搜索
        if (ie > im) {
            node.right = buildTree(preorder, ps + pSize + 1, pe, inorder, im + 1, ie);
        }

        return node;
    }

}
