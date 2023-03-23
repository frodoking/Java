package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.LinkedNode;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

/**
 * 61. 旋转链表
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 * 示例 2：
 *
 *
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 */
@Deprecated
@AlgorithmPoint(difficulty = AlgorithmPoint.Difficulty.medium, company = AlgorithmPoint.Company.tencent,
        category = AlgorithmPoint.Category.linklist)
public class LC61RotateRight implements IAlgorithm {
    @Override
    public void exec() {
        LinkedNode linkedNode = LinkedNode.link(1, 2);
        LinkedNode newLinkedNode = rotateRight(linkedNode, 2);
        newLinkedNode.print();
    }

    public LinkedNode rotateRight(LinkedNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        int len = 0;
        LinkedNode next = head;
        while (next != null) {
            len++;
            next = next.next;
        }

        if (len == 1 || len == k) {
            return head;
        }

        if (k > len) {
            k = k % len;
            return rotateRight(head, k);
        }

        next = head;
        for (int i = 0; i < len - k - 1; i++) {
            next = next.next;
        }
        LinkedNode newHead = next.next;
        next.next = null;
        next = newHead;
        while (next != null && next.next != null) {
            next = next.next;
        }
        next.next = head;
        return newHead;
    }

}
