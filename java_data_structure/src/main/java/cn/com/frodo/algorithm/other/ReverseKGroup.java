package cn.com.frodo.algorithm.other;

import cn.com.frodo.LinkedNode;
import cn.com.frodo.algorithm.IAlgorithm;

/**
 * @author frodoking
 * @ClassName: ReverseKGroup
 * @date 2020/7/4
 */
public class ReverseKGroup implements IAlgorithm {

    @Override
    public void exec() {
        LinkedNode head = null;
        LinkedNode pre = null;
        for (int i = 0; i < 10; i++) {
            LinkedNode node = new LinkedNode(i);
            if (pre != null) pre.next = node;
            else head = node;
            pre = node;

            System.out.printf(node.data + " -> ");
        }

        LinkedNode node = reverseGroup(head, 4);
        System.out.println();
        while (node.next != null) {
            System.out.printf(node.data + " -> ");
            node = node.next;
        }
    }

    public LinkedNode reverseGroup(LinkedNode node, int group) {
        if (node == null) return null;
        // 区间 [a, b) 包含 k 个待反转元素
        LinkedNode a, b;
        a = b = node;
        for (int i = 0; i < group; i++) {
            // 不足 k 个，不需要反转，base case
            if (b == null) return node;
            b = b.next;
        }
        // 反转前 k 个元素
        LinkedNode newHead = reverse(a, b);
        // 递归反转后续链表并连接起来
        a.next = reverseGroup(b, group);
        return newHead;
    }

    public LinkedNode reverse(LinkedNode node, LinkedNode endNode) {
        LinkedNode pre = null;
        LinkedNode current = node;
        LinkedNode next = node.next;
        while (next != endNode) {
            current.next = pre;

            pre = current;
            current = next;
            next = next.next;
        }
        return pre;
    }
}
