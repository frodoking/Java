package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.LinkedNode;
import cn.com.frodo.algorithm.IAlgorithm;

/**
 * @author frodoking
 * @ClassName: LC23MergeKLists
 * @date 2022/3/13
 */
public class LC23MergeKLists implements IAlgorithm {
    @Override
    public void exec() {
        LinkedNode[] lists = {LinkedNode.link(new int[]{2, 3, 8}), LinkedNode.link(new int[]{4}), LinkedNode.link(new int[]{7})};
        LinkedNode linkedNode = mergeKLists(lists);
        linkedNode.print();
    }

    public LinkedNode mergeKLists(LinkedNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public LinkedNode merge(LinkedNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    public LinkedNode mergeTwoLists(LinkedNode a, LinkedNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        LinkedNode head = new LinkedNode(0);
        LinkedNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.data < bPtr.data) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }
}
