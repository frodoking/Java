package cn.com.frodo;

/**
 * @author frodoking
 * @ClassName: DoubleList
 * @date 2020/9/8
 */
public class DoubleLinkedList {

    private DoubleNode head = null;
    private DoubleNode tail = null;
    private int size;

    public DoubleLinkedList() {
        head = new DoubleNode(0,0);
        tail = new DoubleNode(0,0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public void addLast(DoubleNode node) {
        tail.next = node;
        node.prev = tail;
        tail = node;
        size++;
    }

    public void remove(DoubleNode node) {
        DoubleNode pre = node.prev;
        DoubleNode next = node.next;

        pre.next = next;
        next.prev = pre;
        size--;
    }


    public DoubleNode removeFirst() {
        DoubleNode pre = head;
        head = head.next;
        head.prev = null;
        size--;
        return pre;
    }

    public int size() {
        return size;
    }
}
