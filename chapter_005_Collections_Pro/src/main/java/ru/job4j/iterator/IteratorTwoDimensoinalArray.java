package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator for two-dimensional array.
 *
 * @author Alexander Ivanov
 * @since 29.03.2017
 * @version 1.0
 */
public class IteratorTwoDimensoinalArray<E> implements Iterator<E> {

    private final E[][] array;
    private int cursorX = 0;
    private int cursorY = 0;

    public IteratorTwoDimensoinalArray(final E[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return cursorY < array.length;
    }

    @Override
    public E next() {
        E element;
        if (cursorY < array.length) {
            element = array[cursorY][cursorX++];
        } else {
            throw new NoSuchElementException();
        }
        if (cursorX >= array[cursorY].length) {
            cursorX = 0;
            cursorY++;
        }
        return element;
    }
    //delete main
    public static void main(String[] args) {
        String[][] arr = {
            {"1", "2", "5"},
            {"3", "4"}};

        IteratorTwoDimensoinalArray<String> it = new IteratorTwoDimensoinalArray<>(arr);
        System.out.println(it.hasNext());
        while(it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println(it.hasNext());
    }
}
