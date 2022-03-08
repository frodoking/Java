package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.LinkedNode;
import cn.com.frodo.algorithm.IAlgorithm;

import java.util.Objects;

/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * @author frodoking
 * @ClassName: LC19
 * @date 2022/3/7
 */
public class LC19RemoveNthNodeFromEndOfList implements IAlgorithm {
    @Override
    public void exec() {
        LinkedNode head = new LinkedNode(1);
        head.next = new LinkedNode(2);
//        head.next.next = new LinkedNode(3);
//        head.next.next.next = new LinkedNode(4);
//        head.next.next.next.next = new LinkedNode(5);

        LinkedNode linkedNode = removeNthFromEnd(head, 1);
    }

    public LinkedNode removeNthFromEnd(LinkedNode head, int n) {
        LinkedNode prevNode = head;
        LinkedNode currNode = head;
        for (int i = 0; i < n; i++) {
            currNode = currNode.next;
        }

        if (Objects.isNull(currNode)) return head.next;

        while (Objects.nonNull(currNode)) {
            prevNode = prevNode.next;
            currNode = currNode.next;
        }

        prevNode.next = prevNode.next.next;

        return head;
    }
}
