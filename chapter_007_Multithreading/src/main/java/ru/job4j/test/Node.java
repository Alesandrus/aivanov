package ru.job4j.test;

/**
 * Node.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 06.08.2017
 */
public class Node {
    /**
     * Next node.
     */
    private Node next;

    /**
     * Node value.
     */
    private final int value;

    /**
     * Constructor for node.
     * @param value of node.
     */
    public Node(int value) {
        this.value = value;
    }

    /**
     * Adding next node.
     * @param next for adding.
     */
    public void addNext(Node next) {
        this.next = next;
    }

    /**
     * Show all linked nodes.
     * @param head for start showing.
     */
    public static void displayAll(Node head) {
        while (head != null) {
            if (head.next != null) {
                System.out.print(head.value + " -> ");
            } else {
                System.out.println(head.value);
            }
            head = head.next;
        }
    }

    /**
     * Reverse links for all nodes.
     * @param head for starting reverse.
     * @return last node and this become first node.
     */
    public static Node reverse(Node head) {
        Node newHead = head;
        if (head != null) {
            while (head.next != null) {
                Node temp = newHead;
                newHead = head.next;
                head.next = head.next.next;
                newHead.next = temp;
            }
        }
        return newHead;
    }
}
