package cn.com.frodo.algorithm.other;

import cn.com.frodo.LinkedNode;
import cn.com.frodo.algorithm.IAlgorithm;

import java.util.Objects;

/**
 * 将链表拆成4个节点，主要是做分组的两个接口4个点，每次翻转先做分离，反转后再做链接
 *
 * @author frodoking
 * @ClassName: ReverseKGroup
 * @date 2020/7/4
 */
public class ReverseKGroup implements IAlgorithm {

    @Override
    public void exec() {
        LinkedNode head = new LinkedNode(1);
        LinkedNode next = head;
        for (int i = 2; i <= 10; i++) {
            next.next = new LinkedNode(i);
            next = next.next;
        }

        head.print();

        LinkedNode node = reverseGroup(head, 3);

        node.print();
    }

    public LinkedNode reverseGroup(LinkedNode head, int group) {
        LinkedNode dum = new LinkedNode(0);
        dum.next = head;

        LinkedNode pre = dum;
        LinkedNode end = dum;

        while (Objects.nonNull(end.next)) {
            for (int i = 0; i < group; i++) {
                if (Objects.nonNull(end)) end = end.next;
            }
            if (Objects.isNull(end)) break;

            LinkedNode start = pre.next;
            LinkedNode next = end.next;

            end.next = null;

            pre.next = start.reverse();
            start.next = next;
            pre = start;
            end = pre;
        }

        return dum.next;
    }
}
