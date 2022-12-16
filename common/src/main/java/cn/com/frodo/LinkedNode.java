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

    public static LinkedNode link(int[] array) {
        LinkedNode head = null;
        LinkedNode linkedNode = null;
        for (int i = 0; i < array.length; i++) {
            LinkedNode ln = new LinkedNode(array[i]);
            if (Objects.isNull(linkedNode)) {
                head = ln;
                linkedNode = head;
            } else {
                linkedNode.next = ln;
                linkedNode = linkedNode.next;
            }
        }
        return head;
    }

    public LinkedNode(int data) {
        this.data = data;
    }

    public LinkedNode reverse() {
        LinkedNode curr = this;
        LinkedNode pre = null;
        LinkedNode tmp;
        while (curr != null) {
            tmp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tmp;
        }
        return pre;
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
