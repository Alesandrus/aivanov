package ru.job4j.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * NodeTest.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 06.08.2017
 */
public class NodeTest {
    /**
     * Output for test.
     */
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    /**
     * Method for setting stream.
     */
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    /**
     * Method for cleaning stream.
     */
    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    /**
     * Test method addNext() and displayAll().
     */
    @Test
    public void whenAddNodesThenShowingIt() {
        Node first = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);
        Node fourth = new Node(4);

        first.addNext(second);
        second.addNext(third);
        third.addNext(fourth);

        Node.displayAll(first);

        String result = "1 -> 2 -> 3 -> 4" + System.lineSeparator();

        assertThat(result, is(output.toString()));
    }

    /**
     * Test method reverse() and displayAll().
     */
    @Test
    public void whenAddNodesAndReverseAllLinksThenShowAllNodes() {
        Node first = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);
        Node fourth = new Node(4);

        first.addNext(second);
        second.addNext(third);
        third.addNext(fourth);

        Node last = Node.reverse(first);
        Node.displayAll(last);

        String result = "4 -> 3 -> 2 -> 1" + System.lineSeparator();

        assertThat(result, is(output.toString()));
    }
}