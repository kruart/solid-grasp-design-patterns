package app;

public class Node {
    public Node next;
    public int val;

    public Node(Node next, int val) {
        this.next = next;
        this.val = val;
    }

    /**
     * A shallow copy(c) means the first level is copied, deeper levels are referenced(r).
     *
     * input  = node1 -> node2 -> node3
     * return = node1(c) -> node2(r) -> node3(r)
     */
    public Node shallowCopy(Node node) {
        return new Node(node.next, node.val);
    }

    /**
     * A deep copy(c) means the all levels is copied.
     *
     * input  = node1 -> node2 -> node3
     * return = node1(c) -> node2(c) -> node3(c)
     */
    public Node deepCopy(Node node) {
        Node head = new Node(node.next, node.val);
        Node current = head;

        while (current.next != null) {
            Node next = current.next;
            current.next = new Node(next.next, next.val);
            current = current.next;
        }
        return head;
    }
}
