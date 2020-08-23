package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.BinaryTreeNode;
import cn.com.frodo.algorithm.IAlgorithm;
import com.google.common.collect.Lists;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 103. 二叉树的锯齿形层次遍历
 * <p>
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回锯齿形层次遍历如下：
 * <p>
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author frodoking
 * @ClassName: LC71ZigzagLevelOrder
 * @date 2020/7/19
 */
public class LC103ZigzagLevelOrder implements IAlgorithm {
    @Override
    public void exec() {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(4);
        root.right = new BinaryTreeNode(2);
        root.right.left = new BinaryTreeNode(3);
        Object o = zigzagLevelOrder(root);
        System.out.println(o.toString());
    }

    public List<List<BinaryTreeNode>> zigzagLevelOrder(BinaryTreeNode binaryTreeNode) {
        List<List<BinaryTreeNode>> result = Lists.newArrayList();
        if (binaryTreeNode == null) {
            return result;
        }
        Deque<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(binaryTreeNode);
        int dept = 0;
        while (!queue.isEmpty()) {
            List<BinaryTreeNode> list = Lists.newArrayList();
            int count = queue.size();
            while (count > 0) {
                BinaryTreeNode node = queue.poll();
                if (dept % 2 == 0) {
                    list.add(node);
                } else {
                    list.add(0, node);
                }
                if (node.left != null) queue.push(node.left);
                if (node.right != null) queue.push(node.right);
                count--;
            }
            result.add(list);
            dept++;
        }
        return result;
    }
}
