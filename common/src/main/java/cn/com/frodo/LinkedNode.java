package cn.com.frodo;

import java.util.Objects;

/**
 * @author frodoking
 * @ClassName: LinkedNode
 * @date 2020/7/4
 */
public class LinkedNode {
    public int data;
    public LinkedNode next = null;

    public static LinkedNode link(int... array) {
        LinkedNode head = null;
        LinkedNode linkedNode = null;
        for (int i = 0; i < array.length; i++) {
            LinkedNode ln = new LinkedNode(array[i]);
            if (Objects.isNull(linkedNode)) {
                head = ln;
                linkedNode = head;
            } else {
                linkedNode.next = ln;
                linkedNode = linkedNode.next ;
            }
        }
        return head;
    }

    public LinkedNode(int data) {
        this.data = data;
    }

    public LinkedNode reverse() {
        LinkedNode head = this;
        LinkedNode p = null;
        LinkedNode q = head;
        while (head.next != null) {
            p = head.next;
            head.next = p.next;
            p.next = q;
            q = p;
        }
        return q;
    }

    /**
     * 普通逆置
     */
    public static LinkedNode reverseNormal(LinkedNode current) {
        LinkedNode pre = null;
        LinkedNode head = current;
        LinkedNode next = head.next;
        while (next != null) {
            System.out.print(head.next.data + " -->  ");
            head.next = pre;
            pre = head;
            head = next;
            next = next.next;
        }

        head.next = pre;

        return head;
    }

    /**
     * 递归的方式
     */
    public static LinkedNode reverseBy(LinkedNode current) {
        if (current == null || current.next == null)
            return current;
        LinkedNode nextNode = current.next;
        current.next = null;
        LinkedNode reverseRest = reverseBy(nextNode);
        nextNode.next = current;
        return reverseRest;
    }

    public void print() {
        LinkedNode head = this;
        while (Objects.nonNull(head)) {
            System.out.printf(head.data + " -> ");
            head = head.next;
        }
        System.out.println("\t");
    }

}
