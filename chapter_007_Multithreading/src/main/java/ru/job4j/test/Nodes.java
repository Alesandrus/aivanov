package ru.job4j.test;

/**
 * Created by user on 02.09.2017.
 */
public class Nodes {
    /**
     * Show all linked nodes.
     * @param head for start showing.
     */
    public void displayAll(Node head) {
        while (head != null) {
            if (head.getNext() != null) {
                System.out.print(head.getValue() + " -> ");
            } else {
                System.out.println(head.getValue());
            }
            head = head.getNext();
        }
    }

    /**
     * Reverse links for all nodes.
     * @param head for starting reverse.
     * @return last node and this become first node.
     */
    public Node reverse(Node head) {
        Node newHead = head;
        if (head != null) {
            while (head.getNext() != null) {
                Node temp = newHead;
                newHead = head.getNext();
                head.setNext(head.getNext().getNext());
                newHead.setNext(temp);
            }
        }
        return newHead;
    }
}
