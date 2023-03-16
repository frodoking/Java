package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.LinkedNode;
import cn.com.frodo.algorithm.IAlgorithm;

import java.util.Objects;

/**
 * 2. 两数相加
 * <p>
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * @author frodoking
 * @ClassName: LCAddTwoNumbers
 * @date 2022/3/7
 */
public class LC2AddTwoNumbers implements IAlgorithm {
    @Override
    public void exec() {
        LinkedNode l1 = new LinkedNode(9);
        l1.next = new LinkedNode(9);
        l1.next.next = new LinkedNode(9);

        LinkedNode l2 = new LinkedNode(9);
        l2.next = new LinkedNode(9);
        l2.next.next = new LinkedNode(9);
        l2.next.next.next = new LinkedNode(9);

        LinkedNode l = addTwoNumbers(l1, l2);
       while (Objects.nonNull(l)) {
           System.out.println(l.data);
           l = l.next;
       }
    }

    public LinkedNode addTwoNumbers(LinkedNode l1, LinkedNode l2) {
        LinkedNode head = new LinkedNode((l1.data + l2.data) % 10);
        LinkedNode nextNode = head;
        int next = (l1.data + l2.data) / 10;
        l1 = l1.next;
        l2 = l2.next;
        while (Objects.nonNull(l1) || Objects.nonNull(l2)) {
            LinkedNode currNode;
            int total = 0;
            if (Objects.isNull(l1)) {
                total =l2.data + next;
            } else if (Objects.isNull(l2)) {
                total =l1.data + next;
            }else {
                total =l1.data + l2.data + next;
            }
            currNode = new LinkedNode(total % 10);
            nextNode.next = currNode;
            nextNode = currNode;
            next = total / 10;

            if (Objects.nonNull(l1))
                l1 = l1.next;

            if (Objects.nonNull(l2))
                l2 = l2.next;
        }
        if (next!=0) {
            nextNode.next =  new LinkedNode(next);
        }
        return head;
    }
}
