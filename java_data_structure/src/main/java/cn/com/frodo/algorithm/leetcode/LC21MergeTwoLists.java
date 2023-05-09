package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.LinkedNode;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

/**
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 示例 2：
 * <p>
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 *
 * @author frodoking
 * @ClassName: LC21MergeTwoLists
 * @date 2023/3/8
 */
@Deprecated
@AlgorithmPoint(
        tag = {AlgorithmPoint.Tag.leetcode, AlgorithmPoint.Tag.interview, AlgorithmPoint.Tag.frequently},
        difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category.linklist)
public class LC21MergeTwoLists implements IAlgorithm {
    @Override
    public void exec() {
        LinkedNode link1 = LinkedNode.link(1, 2, 4);
        LinkedNode link2 = LinkedNode.link(1, 3, 4);

        LinkedNode linkedNode = mergeTwoLists(link1, link2);

        linkedNode.print();
    }

    public LinkedNode mergeTwoLists(LinkedNode list1, LinkedNode list2) {
        LinkedNode head = new LinkedNode(-1);

        LinkedNode next = head;
        while (list1 != null && list2 != null) {
            if (list1.data < list2.data) {
                next.next = list1;
                list1 = list1.next;
            } else {
                next.next = list2;
                list2 = list2.next;
            }
            next = next.next;
        }

        if (list1 != null) {
            next.next = list1;
        }

        if (list2 != null) {
            next.next = list2;
        }

        return head.next;
    }
}
