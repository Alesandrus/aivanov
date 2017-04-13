package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for CycleNode.
 *
 * @author Alexander Ivanov
 * @since 13.04.2017
 * @version 1.0
 */
public class CycleNodeTest {
    /**
     * Test method hasCycle().
     * If list of Nodes has cycle.
     */
    @Test
    public void whenListOfNodesHasCycleThenReturnTrue() {
        Node<Integer> firstNode = new Node<>(1);
        Node<Integer> twoNode = new Node<>(2);
        Node<Integer> thirdNode = new Node<>(3);
        Node<Integer> fourNode = new Node<>(4);

        firstNode.next = twoNode;
        twoNode.next = thirdNode;
        thirdNode.next = fourNode;
        fourNode.next = firstNode;

        CycleNode cycleNode = new CycleNode();
        final boolean resultCycle = cycleNode.hasCycle(firstNode);
        final boolean checkCycle = true;
        assertThat(resultCycle, is(checkCycle));
    }

    /**
     * Test method hasCycle().
     * If list of Nodes hasn't cycle.
     */
    @Test
    public void whenListOfNodesHasNotCycleThenReturnFalse() {
        Node<Integer> firstNode = new Node<>(1);
        Node<Integer> twoNode = new Node<>(2);
        Node<Integer> thirdNode = new Node<>(3);
        Node<Integer> fourNode = new Node<>(4);

        firstNode.next = twoNode;
        twoNode.next = thirdNode;
        thirdNode.next = fourNode;

        CycleNode cycleNode = new CycleNode();
        final boolean resultCycle = cycleNode.hasCycle(firstNode);
        final boolean checkCycle = false;
        assertThat(resultCycle, is(checkCycle));
    }
}