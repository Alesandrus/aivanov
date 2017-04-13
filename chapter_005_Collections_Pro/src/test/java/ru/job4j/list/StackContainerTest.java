package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for StackContainer.
 *
 * @author Alexander Ivanov
 * @since 13.04.2017
 * @version 1.0
 */
public class StackContainerTest {
    /**
     * Test method push() and pop().
     * Adding and getting elements.
     */
    @Test
    public void whenPushElementAndPopThenGetIt() {
        StackContainer<Integer> container = new StackContainer<>();
        container.push(150);
        container.push(200);
        container.push(500);
        final Integer resultElement = container.pop();
        final Integer checkElement = 500;
        assertThat(resultElement, is(checkElement));
    }
}