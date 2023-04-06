package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.BinaryTreeNode;
import cn.com.frodo.LinkedNode;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

/**
 * 143. 重排链表
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * <p>
 * L0 → L1 → … → Ln - 1 → Ln
 * 请将其重新排列后变为：
 * <p>
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：head = [1,2,3,4]
 * 输出：[1,4,2,3]
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：head = [1,2,3,4,5]
 * 输出：[1,5,2,4,3]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 链表的长度范围为 [1, 5 * 104]
 * 1 <= node.val <= 1000
 */
@AlgorithmPoint(tag = {AlgorithmPoint.Tag.leetcode, AlgorithmPoint.Tag.unlearn},
        difficulty = AlgorithmPoint.Difficulty.medium,
        company = {AlgorithmPoint.Company.bytedance},
        category = AlgorithmPoint.Category.linklist)
public class LC143ReorderList implements IAlgorithm {
    @Override
    public void exec() {
        LinkedNode linkedNode = LinkedNode.link(1, 2, 3, 4);

        linkedNode.print();
        reorderList(linkedNode);

        linkedNode.print();
    }

    public void reorderList(LinkedNode head) {
        LinkedNode midNode = findMid(head);
        LinkedNode nextNode = midNode.next;
        midNode.next = null;

        nextNode = reverse(nextNode);

        merge(head, nextNode);
    }

    public void merge(LinkedNode node1, LinkedNode node2) {
        LinkedNode head = node1;
        while (node1 != null || node2 != null) {
            if (node1 != null) {
                head.next = node1;
                node1 = node1.next;
                head = head.next;
            }
            if (node2 != null) {
                head.next = node2;
                node2 = node2.next;
                head = head.next;
            }
        }
    }

    public LinkedNode findMid(LinkedNode head) {
        LinkedNode slow = head;
        LinkedNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public LinkedNode reverse(LinkedNode head) {
        LinkedNode newHead = null;
        LinkedNode curr = head;
        while (curr != null) {
            LinkedNode n = curr.next;
            curr.next = newHead;
            newHead = curr;
            curr = n;
        }
        return newHead;
    }
}
