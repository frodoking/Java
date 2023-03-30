package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.LinkedNode;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

/**
 * 142. 环形链表 II
 * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 *
 * 不允许修改 链表。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 示例 2：
 *
 *
 *
 * 输入：head = [1,2], pos = 0
 * 输出：返回索引为 0 的链表节点
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * 示例 3：
 *
 *
 *
 * 输入：head = [1], pos = -1
 * 输出：返回 null
 * 解释：链表中没有环。
 */
@Deprecated
@AlgorithmPoint(tag = {AlgorithmPoint.Tag.leetcode, AlgorithmPoint.Tag.frequently},
        difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category.linklist)
public class LC142DetectCycle implements IAlgorithm {
    @Override
    public void exec() {

    }

    /**
     * 主要思路，是快慢指针终究会在环上相遇。
     *  假设起点到环的距离 A，环入口到相遇的部分分为 B，C。那么快指针走了 A + B + C + B、慢指针走了 A + B。
     *  那么 A + B + C + B = 2 * （A + B）。所以A=C，所以相遇后，让快指针重新一步一步走，那么他们一定会在入口相遇。
     */
    public LinkedNode detectCycle(LinkedNode head) {
        LinkedNode nodeS = head;
        LinkedNode nodeF = head;
        do {
            if (nodeF==null || nodeF.next == null)
                return null;
            nodeS = nodeS.next;
            nodeF = nodeF.next.next;
        } while (nodeS != nodeF);

        nodeF = head;
        while (nodeS!=nodeF) {
            nodeS = nodeS.next;
            nodeF = nodeF.next;
        }

        return nodeF;
    }

}
