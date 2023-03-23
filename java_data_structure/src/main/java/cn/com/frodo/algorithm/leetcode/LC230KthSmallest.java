package cn.com.frodo.algorithm.leetcode;


import cn.com.frodo.BinaryTreeNode;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

/**
 * 230. 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [3,1,4,null,2], k = 1
 * 输出：1
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [5,3,6,2,4,null,null,1], k = 3
 * 输出：3
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中的节点数为 n 。
 * 1 <= k <= n <= 104
 * 0 <= Node.val <= 104
 * <p>
 * <p>
 * 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
 */
@Deprecated
@AlgorithmPoint(difficulty = AlgorithmPoint.Difficulty.medium, company = AlgorithmPoint.Company.tencent,
        category = AlgorithmPoint.Category.stack)
public class LC230KthSmallest implements IAlgorithm {
    @Override
    public void exec() {

    }

    public int kthSmallest(BinaryTreeNode root, int k) {
        dfs(root, k);
        return count;
    }

    int count = 0;
    int ans = 0;

    public void dfs(BinaryTreeNode root, int k) {
        if (root == null || k <= count) return;

        dfs(root.left, k);
        count++;
        if (count == k) {
            ans = root.data;
        }
        dfs(root.right, k);
    }
}
