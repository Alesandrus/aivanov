package ru.job4j.iterator;

import java.util.Iterator;

/**
 * Iterator for return even numbers.
 *
 * @author Alexander Ivanov
 * @since 30.03.2017
 * @version 1.0
 */
public class EvenIterator implements Iterator<Integer> {

    private final int[] array;
    private int cursor = 0;


    public EvenIterator(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        boolean isEven = false;
        if (cursor >= array.length) {
            return false;
        }
        int evenIndex = cursor;
        while (evenIndex < array.length) {
            if (array[evenIndex] % 2 == 0) {
                isEven = true;
                evenIndex = array.length;
            } else {
                evenIndex++;
            }
        }
        return isEven;
    }

    @Override
    public Integer next() {
        Integer element = null;
        while (cursor < array.length) {
            if (array[cursor] % 2 == 0) {
                element = array[cursor];
                break;
            }
            cursor++;
        }
        if (cursor >= array.length) {
            throw new NoSuchEvenElementException();
        }
        cursor++;
        return element;
    }
    //delete main
    public static void main(String[] args) {
        int[] arr = {4, 1, 6, 4, 5, 5, 9};
        EvenIterator it = new EvenIterator(arr);
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println(it.hasNext());
        it.next();
    }
}
