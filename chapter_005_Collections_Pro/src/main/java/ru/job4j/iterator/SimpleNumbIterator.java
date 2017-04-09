package ru.job4j.iterator;

import java.util.Iterator;

/**
 * Iterator for return simple numbers.
 *
 * @author Alexander Ivanov
 * @since 30.03.2017
 * @version 1.0
 */
public class SimpleNumbIterator implements Iterator<Integer> {

    private final int[] array;
    private int cursor = 0;

    public SimpleNumbIterator(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        boolean nextIsSimple = false;
        if (cursor >= array.length) {
            return false;
        }
        int index = cursor;
        while (index < array.length) {
            if (SimpleN.checkSimple(array[index])) {
                nextIsSimple = true;
                index = array.length;
            } else {
                index++;
            }
        }
        return nextIsSimple;
    }

    @Override
    public Integer next() {
        Integer element = null;
        while (cursor < array.length) {
            if (SimpleN.checkSimple(array[cursor])) {
                element = array[cursor];
                break;
            }
            cursor++;
        }
        if (cursor >= array.length) {
            throw new NoSuchSimpleElementException();
        }
        cursor++;
        return element;
    }
    //delete main
    public static void main(String[] args) {
        int[] arr = {4, 1, 157, 6, 4, 101, 5, 5, 9};
        SimpleNumbIterator it = new SimpleNumbIterator(arr);
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println(it.hasNext());
        it.next();
    }
}
