package cn.com.frodo.knowledge.linklist;

public class SingleLinkList {
    private Node head;

    public SingleLinkList() {
        head = new Node(0);
        Node tail = head;
        for (int i = 0; i < 10; ++i) {
            Node p = new Node(i);
            tail.next = p;
            tail = p;
        }
        head = Node.reverse(head);
        while (head.next != null) {
            System.out.println(head.data);
            head = head.next;
        }
    }

    public Node getLinkList() {
        return head;
    }
}
