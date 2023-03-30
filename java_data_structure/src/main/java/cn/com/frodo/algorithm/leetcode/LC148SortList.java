package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.LinkedNode;
import cn.com.frodo.algorithm.Algorithm;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

/**
 * 148. 排序链表
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * 示例 2：
 *
 *
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * 示例 3：
 *
 * 输入：head = []
 * 输出：[]
 *
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 5 * 104] 内
 * -105 <= Node.val <= 105
 *
 *
 * 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 */
@Deprecated
@AlgorithmPoint(tag = {AlgorithmPoint.Tag.leetcode, AlgorithmPoint.Tag.frequently},
        difficulty = AlgorithmPoint.Difficulty.medium,
        company = AlgorithmPoint.Company.tencent,
        category = AlgorithmPoint.Category.linklist,
        algorithm = @Algorithm(value = Algorithm.AlgorithmEnum.mergeSort))
public class LC148SortList implements IAlgorithm {
    @Override
    public void exec() {
        LinkedNode linkedNode = LinkedNode.link(4,2,1,3);
        sortList2(linkedNode).print();
    }

    /**
     * 递归实现 O(n*n)
     */
    @Algorithm(value = Algorithm.AlgorithmEnum.dfs)
    public LinkedNode sortList(LinkedNode head) {
        if (head == null) {
            return null;
        }
        LinkedNode next = sortList(head.next);

        if (next == null) {
            return head;
        }
        if (head.data < next.data) {
            head.next = next;
            return head;
        }

        LinkedNode newHead = next;

        while (next.next != null && next.next.data < head.data) {
            next = next.next;
        }
        head.next = next.next;
        next.next = head;
        return newHead;
    }

    /**
     * 归并实现 O(n log n)
     */
    @Algorithm(value = Algorithm.AlgorithmEnum.mergeSort)
    public LinkedNode sortList2(LinkedNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        LinkedNode fastNode = head;
        LinkedNode slowNode = head;
        LinkedNode brkNode = head;
        while (fastNode != null && fastNode.next != null) {
            fastNode = fastNode.next.next;
            if (fastNode == null || fastNode.next == null) {
                brkNode = slowNode;
            }
            slowNode = slowNode.next;
        }

        brkNode.next = null;

        LinkedNode firstNode = sortList2(head);
        LinkedNode secondNode = sortList2(slowNode);

        LinkedNode newHead0 = new LinkedNode(-1);

        LinkedNode newHead = newHead0;
        while (firstNode != null && secondNode != null) {
            if (firstNode.data < secondNode.data) {
                newHead.next = firstNode;
                firstNode = firstNode.next;
            } else {
                newHead.next = secondNode;
                secondNode = secondNode.next;
            }
            newHead = newHead.next;
        }

        if (firstNode != null) {
            newHead.next = firstNode;
        }

        if (secondNode != null) {
            newHead.next = secondNode;
        }
        return newHead0.next;
    }


}
