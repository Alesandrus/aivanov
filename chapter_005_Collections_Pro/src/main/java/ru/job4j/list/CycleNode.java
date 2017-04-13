package ru.job4j.list;

/**
 * Class for check cycle.
 *
 * @author Alexander Ivanov
 * @since 02.04.2017
 * @version 1.0
 */
public class CycleNode {

    /**
     * Check cycle.
     * @param first Node.
     * @return true if list of node has cycle.
     */
    public boolean hasCycle(Node first) {
        Node tortoise = first;
        Node hare = first;
        do {
            if (hare == null || hare.next == null) {
                return false;
            }
            tortoise = tortoise.next;
            hare = hare.next.next;
        } while (!tortoise.equals(hare));
        return true;
    }

//delete main
    public static void main(String[] args) {
        Node<Integer> first = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;

        CycleNode cycleNode = new CycleNode();
        System.out.println(cycleNode.hasCycle(first));
    }
}

/**
 * Node.
 * @param <T> element.
 */
class Node<T> {
    /**
     * Value that contains Node.
     */
    private T value;

    /**
     * Next Node.
     */
    Node<T> next;

    /**
     * Constructor for Node.
     * @param value for Node.
     */
    Node (T value) {
        this.value = value;
    }
}

