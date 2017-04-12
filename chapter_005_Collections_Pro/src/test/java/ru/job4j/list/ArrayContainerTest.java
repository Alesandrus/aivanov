package ru.job4j.list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for ArrayContainer.
 *
 * @author Alexander Ivanov
 * @since 12.04.2017
 * @version 1.0
 */
public class ArrayContainerTest {
    /**
     * Test method add() and contains().
     * Adding element and check container.
     */
    @Test
    public void whenAddElementThenCheckIt() {
        ArrayContainer<Integer> container = new ArrayContainer<>();
        for (int i = 0; i < 100; i++) {
            container.add(i);
        }
        final boolean resultHasElement = container.contains(55);
        final boolean checkHasElement = true;
        assertThat(resultHasElement, is(checkHasElement));
    }

    /**
     * Test method contains().
     * Check container if it hasn't element.
     */
    @Test
    public void whenContainerHasNotElementThenContainsReturnFalse() {
        ArrayContainer<Integer> container = new ArrayContainer<>();
        for (int i = 0; i < 100; i++) {
            container.add(i);
        }
        final boolean resultHasElement = container.contains(101);
        final boolean checkHasElement = false;
        assertThat(resultHasElement, is(checkHasElement));
    }

    /**
     * Test method delete().
     * Delete element from container, get it element and check for contains.
     */
    @Test
    public void whenDeleteElementThenReturnElementAndContainerReturnFalse() {
        ArrayContainer<Integer> container = new ArrayContainer<>();
        for (int i = 0; i < 15; i++) {
            container.add(i);
        }
        final Integer resultElement = container.delete(10);
        final Integer checkElement = 10;
        final boolean resultHasElement = container.contains(10);
        final boolean checkHasElement = false;
        assertThat(resultElement, is(checkElement));
        assertThat(resultHasElement, is(checkHasElement));
    }

    /**
     * Test iterator method hasNext().
     * If iterator has elements then return true.
     */
    @Test
    public void whenIteratorHasElementsThenReturnTrue() {
        ArrayContainer<Integer> container = new ArrayContainer<>();
        for (int i = 0; i < 15; i++) {
            container.add(i);
        }
        Iterator<Integer> iterator = container.iterator();
        iterator.next();
        final boolean resultElement = iterator.hasNext();
        final boolean checkElement = true;
        assertThat(resultElement, is(checkElement));
    }

    /**
     * Test iterator method hasNext().
     * If iterator has not elements then return false.
     */
    @Test
    public void whenIteratorHasNotElementsThenReturnFalse() {
        ArrayContainer<Integer> container = new ArrayContainer<>();
        for (int i = 0; i < 3; i++) {
            container.add(i);
        }
        Iterator<Integer> iterator = container.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        final boolean resultElement = iterator.hasNext();
        final boolean checkElement = false;
        assertThat(resultElement, is(checkElement));
    }

    /**
     * Test iterator method next().
     * Get element from iterator.
     */
    @Test
    public void whenGetElementFromIteratorThenReturnElement() {
        ArrayContainer<Integer> container = new ArrayContainer<>();
        for (int i = 0; i < 3; i++) {
            container.add(i);
        }
        Iterator<Integer> iterator = container.iterator();
        iterator.next();
        iterator.next();
        final Integer resultElement = iterator.next();
        final Integer checkElement = 2;
        assertThat(resultElement, is(checkElement));
    }

    /**
     * Test iterator method next().
     * When invoke next() fo iterator that has not elements get NoSuchElementException.
     */
    @Test
    public void whenIteratorHasNotElementsAndInvokeNextThenReturnNoSuchElementException() {
        ArrayContainer<Integer> container = new ArrayContainer<>();
        for (int i = 0; i < 3; i++) {
            container.add(i);
        }
        Iterator<Integer> iterator = container.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        String resultException = null;
        try {
            iterator.next();
        } catch (NoSuchElementException e) {
            resultException = "No more elements";
        }
        final String checkException = "No more elements";
        assertThat(resultException, is(checkException));
    }

    /**
     * Test for getting ConcurrentModificationException.
     */
    @Test
    public void whenAddElementsAndInvokeNextFromIteratorThenGetConcurrentModificationException() {
        ArrayContainer<Integer> container = new ArrayContainer<>();
        for (int i = 0; i < 3; i++) {
            container.add(i);
        }
        Iterator<Integer> iterator = container.iterator();
        iterator.next();
        String resultException = null;
        container.add(100);
        try {
            iterator.next();
        } catch (ConcurrentModificationException e) {
            resultException = "ConcurrentMod";
        }
        final String checkException = "ConcurrentMod";
        assertThat(resultException, is(checkException));
    }
}