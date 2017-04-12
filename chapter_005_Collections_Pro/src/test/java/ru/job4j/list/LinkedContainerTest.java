package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for ArrayContainer.
 *
 * @author Alexander Ivanov
 * @since 12.04.2017
 * @version 1.0
 */
public class LinkedContainerTest {
    /**
     * Test method add() and get().
     * Adding element and get it by index.
     */
    @Test
    public void whenAddElementThenGetIt() {
        LinkedContainer<Integer> container = new LinkedContainer<>();
        for (int i = 0; i < 100; i++) {
            container.add(i);
        }
        final Integer resultElement = container.get(50);
        final Integer checkElement = 50;
        assertThat(resultElement, is(checkElement));
    }

    /**
     * Test method delete() and contains().
     * Delete element from container, get it and check for contains.
     */
    @Test
    public void whenDeleteElementThenReturnElementAndContainerReturnFalse() {
        LinkedContainer<Integer> container = new LinkedContainer<>();
        for (int i = 0; i < 15; i++) {
            container.add(i);
        }
        final Integer resultElement = container.remove(10);
        final Integer checkElement = 10;
        final boolean resultHasElement = container.contains(10);
        final boolean checkHasElement = false;
        assertThat(resultElement, is(checkElement));
        assertThat(resultHasElement, is(checkHasElement));
    }
}