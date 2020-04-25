package cn.com.frodo.knowledge.linklist;

public class Node {
    int data;
    Node next = null;

    public Node(int data) {
        this.data = data;
    }

    /**
     * 头插法
     *
     * @param head
     * @return
     */
    public static Node reverse(Node head) {
        Node p = null;
        Node q = head;
        while (head.next != null) {
            System.out.print(head.next.data + " -->  ");
            p = head.next;
            head.next = p.next;
            p.next = q;
            q = p;
        }
        return q;
    }

    /**
     * 普通逆置
     *
     * @param current
     * @return
     */
    public static Node reverseNormal(Node current) {
        Node pre = null;
        Node head = current;
        Node next = head.next;
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
     *
     * @param current
     * @return
     */
    public static Node reverseBy(Node current) {
        if (current == null || current.next == null)
            return current;
        Node nextNode = current.next;
        current.next = null;
        Node reverseRest = reverseBy(nextNode);
        nextNode.next = current;
        return reverseRest;
    }

    public static void main(String[] args) {
        Node head = new Node(0);
        Node tail = head;
        for (int i = 1; i < 10; i++) {
            Node p = new Node(i);
            tail.next = p;
            tail = p;
        }
        head = reverseBy(head);
        System.out.println();
        System.out.println("           ******************************");
        while (head.next != null) {
            System.out.print(head.data + " <--| ");
            head = head.next;
        }
    }
}
