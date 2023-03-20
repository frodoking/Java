package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.LinkedNode;
import cn.com.frodo.algorithm.IAlgorithm;

/**
 * @author frodoking
 * @ClassName: LC23MergeKLists
 * @date 2022/3/13
 */
@Deprecated
public class LC23MergeKLists implements IAlgorithm {
    @Override
    public void exec() {
        LinkedNode[] lists = {LinkedNode.link(2, 3, 8), LinkedNode.link(4), LinkedNode.link(7)};
        LinkedNode linkedNode = mergeKLists(lists);
        linkedNode.print();
    }

    public LinkedNode mergeKLists(LinkedNode[] lists) {
        LinkedNode head = new LinkedNode(-1);
        LinkedNode next = head;
        while (true) {
            int minIndex = 0;
            int minData = Integer.MAX_VALUE;
            LinkedNode minNode = null;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null && lists[i].data <= minData) {
                    minIndex = i;
                    minData = lists[i].data;
                    minNode = lists[i];
                }
            }

            if (minNode == null) {
                break;
            }

            lists[minIndex] = minNode.next;

            next.next = minNode;
            next = next.next;

        }

        return head.next;

    }

}
