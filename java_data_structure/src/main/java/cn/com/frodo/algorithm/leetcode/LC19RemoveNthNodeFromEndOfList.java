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
@Deprecated
public class LC19RemoveNthNodeFromEndOfList implements IAlgorithm {
    @Override
    public void exec() {
        LinkedNode head = LinkedNode.link(1, 2, 3, 4, 5);

        head = removeNthFromEnd(head, 3);
        head.print();
    }

    public LinkedNode removeNthFromEnd(LinkedNode head, int n) {
        LinkedNode nextNode = head;
        int size = 0;
        while (nextNode != null) {
            size++;
            nextNode = nextNode.next;
        }

        if (size == 0) {
            return null;
        }

        if (size == 1) {
            return null;
        }

        if (size == n) {
            return head.next;
        }

        LinkedNode currNode = head;
        for (int i = 0; i < size - n - 1; i++) {
            currNode = currNode.next;
        }
        LinkedNode preNode = currNode;

        LinkedNode skipNode = preNode.next.next;
        preNode.next.next = null;
        preNode.next = skipNode;

        return head;
    }
}
