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
     * Getter for next.
     * @return next node.
     */
    public Node getNext() {
        return next;
    }

    /**
     * Getter for value.
     * @return node's value.
     */
    public int getValue() {
        return value;
    }

    /**
     * Setter for next.
     * @param next node.
     */
    public void setNext(Node next) {
        this.next = next;
    }
}
