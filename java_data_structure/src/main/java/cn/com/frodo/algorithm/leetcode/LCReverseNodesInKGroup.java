package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.BinaryTreeNode;
import cn.com.frodo.LinkedNode;
import cn.com.frodo.algorithm.Algorithm;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

import java.util.Objects;

/**
 * 25. K 个一组翻转链表
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 * <p>
 * <p>
 * 提示：
 * 链表中的节点数目为 n
 * 1 <= k <= n <= 5000
 * 0 <= Node.val <= 1000
 * <p>
 * <p>
 * 进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？
 */
@AlgorithmPoint(tag = {AlgorithmPoint.Tag.leetcode, AlgorithmPoint.Tag.frequently},
        difficulty = AlgorithmPoint.Difficulty.hard,
        company = AlgorithmPoint.Company.bytedance,
        category = AlgorithmPoint.Category.linklist)
public class LCReverseNodesInKGroup implements IAlgorithm {

    @Override
    public void exec() {
        LinkedNode linkedNode = LinkedNode.link(1, 2, 3, 4, 5);
        linkedNode.print();

        LinkedNode linkedNode1 = reverseKGroup(linkedNode, 1);
        linkedNode1.print();
    }

    /**
     * 剩余不反转
     */
    public LinkedNode reverseKGroup(LinkedNode head, int k) {
        if (k == 1) {
            return head;
        }
        int len = 0;
        LinkedNode curr = head;
        // 计算节点数目
        while (curr != null) {
            curr = curr.next;
            len++;
        }

        int index = 1;
        // 分组使用
        LinkedNode newHead = null;
        LinkedNode groupTail = null;
        LinkedNode lastGroupTail = null;

        // 反转使用
        curr = head;
        LinkedNode pre = null;
        while (curr != null) {
            LinkedNode newNext = curr.next;
            if (index == k) {
                newHead = curr;
            }

            // 如果是当前组最后一个，或者结点最后一个元素，让上一组尾部指向当前节点，同时更新上一个组的尾部是自己组
            if (index % k == 0) {
                if (lastGroupTail != null) {
                    lastGroupTail.next = curr;
                }
                lastGroupTail = groupTail;
            }

            if (len % k != 0 &&  index > (len / k ) * k) {
                if (lastGroupTail != null) {
                    lastGroupTail.next = curr;
                }
                break;
            }

            // 每次走到第一个的时候
            if (index % k == 1) {
                // 断开之前的连接
                curr.next = null;
                // 当前组的尾部记录下来
                groupTail = curr;
            } else {
                // 非第一个节点，正常反转
                curr.next = pre;
            }
            pre = curr;
            curr = newNext;
            index++;
        }
        return newHead;
    }

    /**
     * 剩余也反转
     */
    public LinkedNode reverseKGroup0(LinkedNode head, int k) {
        int index = 1;
        // 分组使用
        LinkedNode newHead = null;
        LinkedNode groupTail = null;
        LinkedNode lastGroupTail = null;

        // 反转使用
        LinkedNode curr = head;
        LinkedNode pre = null;
        while (curr != null) {
            LinkedNode newNext = curr.next;
            if (index == k) {
                newHead = curr;
            }

            // 如果是当前组最后一个，或者结点最后一个元素，让上一组尾部指向当前节点，同时更新上一个组的尾部是自己组
            if (index % k == 0 || newNext == null) {
                if (lastGroupTail != null) {
                    lastGroupTail.next = curr;
                }
                lastGroupTail = groupTail;
            }

            // 每次走到第一个的时候
            if (index % k == 1) {
                // 断开之前的连接
                curr.next = null;
                // 当前组的尾部记录下来
                groupTail = curr;
            } else {
                // 非第一个节点，正常反转
                curr.next = pre;
            }
            pre = curr;
            curr = newNext;
            index++;
        }
        return newHead;
    }

    /**
     *  按照分组反转实现
     */
    public LinkedNode reverseGroup3(LinkedNode head, int group) {
        LinkedNode dum = new LinkedNode(0);
        dum.next = head;

        LinkedNode pre = dum;
        LinkedNode end = dum;

        while (Objects.nonNull(end.next)) {
            for (int i = 0; i < group; i++) {
                if (Objects.nonNull(end)) end = end.next;
            }
            if (Objects.isNull(end)) break;

            LinkedNode start = pre.next;
            LinkedNode next = end.next;

            end.next = null;

            pre.next = start.reverse();
            start.next = next;
            pre = start;
            end = pre;
        }

        return dum.next;
    }
}
