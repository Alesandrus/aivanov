package ru.job4j.iterator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for EvenIteratorTest.
 *
 * @author Alexander Ivanov
 * @since 11.04.2017
 * @version 1.0
 */
public class EvenIteratorTest {

    /**
     * Test method hasNext().
     * If iterator has next even number then method should return true.
     */
    @Test
    public void whenIteratorHasNextEvenNumberThenTrue() {
        final int[] array = {1, 6, 4, 5, 9};
        EvenIterator iterator = new EvenIterator(array);
        final boolean resultNext = iterator.hasNext();
        final boolean checkNext = true;
        assertThat(resultNext, is(checkNext));
    }

    /**
     * Test method hasNext().
     * If iterator hasn't next even number then method should return false.
     */
    @Test
    public void whenIteratorHasNotNextEvenNumberThenFalse() {
        final int[] array = {1, 3, 4, 5, 9};
        EvenIterator iterator = new EvenIterator(array);
        iterator.next();
        final boolean resultNext = iterator.hasNext();
        final boolean checkNext = false;
        assertThat(resultNext, is(checkNext));
    }

    /**
     * Test method next().
     * Check iterator for return right number.
     */
    @Test
    public void whenInvokeNextThenGetRightNumber() {
        final int[] array = {1, 2, 101, 5, 8};
        EvenIterator iterator = new EvenIterator(array);
        iterator.next();
        final int resultElement = iterator.next();
        final int checkElement = 8;
        assertThat(resultElement, is(checkElement));
    }

    /**
     * Test method next().
     * If iterator hasn't even numbers and invoking next() then getting NoSuchEvenElementException.
     */
    @Test
    public void whenInvokeNextFromIteratorWithoutEvenNumbersThenGetNoSuchEvenElementException() {
        final int[] array = {1, 3, 101, 5, 9};
        EvenIterator iterator = new EvenIterator(array);
        String resultException = null;
        try {
            iterator.next();
        } catch (NoSuchEvenElementException e) {
            resultException = "No even elements";
        }
        final String checkException = "No even elements";
        assertThat(resultException, is(checkException));
    }
}