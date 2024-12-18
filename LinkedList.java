public class LinkedList {
    private Node head;

    public class Node { // Make Node public
        Object data;
        Node next;

        public Node(Object data) {
            this.data = data;
        }
    }

    public void add(Object data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public Object remove() {
        if (head == null) {
            return null;
        }
        Object removedData = head.data;
        head = head.next;
        return removedData;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Node getHead() { // Provide access to the head
        return head;
    }
}
