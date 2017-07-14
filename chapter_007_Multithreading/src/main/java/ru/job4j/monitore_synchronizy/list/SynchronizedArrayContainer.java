package ru.job4j.monitore_synchronizy.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SynchronizedArrayContainer.
 *
 * @author Alexander Ivanov
 * @since 31.03.2017
 * @version 1.0
 * @param <E> type of elements.
 */
public class SynchronizedArrayContainer<E> extends SimpleArray<E> implements Iterable<E> {

    /**
     * Array of objects.
     */
    private volatile Object[] arr;

    /**
     * Number of array elements.
     */
    private volatile int size = 0;

    /**
     * Load capacity.
     */
    private volatile int capacity;

    /**
     * Class constructor.
     */
    public SynchronizedArrayContainer() {
        capacity = 10;
        this.arr = new Object[capacity];
    }

    /**
     * method for increase array's capacity.
     * @return true if array is increased.
     */
    private boolean increaseCapacity() {
        if (size > capacity * 0.7) {
            Object[] newArr = new Object[capacity * 2];
            System.arraycopy(arr, 0, newArr, 0, size);
            this.arr = newArr;
            capacity *= 2;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Getting element from array.
     * @param index of element for getting.
     * @return element.
     */
    @SuppressWarnings(value = "unchecked")
    public E get(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return (E) arr[index];
    }

    /**
     * Update reference by index to new element.
     * @param index for updating.
     * @param element for setting.
     */
    public synchronized void update(int index, E element) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        arr[index] = element;
    }

    /**
     * Getter for size.
     * @return size.
     */
    public int getSize() {
        return size;
    }

    /**
     * Add element.
     * @param element element for adding.
     */
    public synchronized void add(E element) {
        increaseCapacity();
        arr[size++] = element;
    }

    /**
     * delete element.
     * @param index of element for delete it.
     * @return deleted element.
     */
    @SuppressWarnings(value = "unchecked")
    public synchronized E delete(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        E delElement = (E) arr[index];
        System.arraycopy(arr, index + 1, arr, index, this.size - index - 1);
        arr[--size] = null;
        return delElement;
    }

    /**
     * Check array for contain element.
     * @param elem for check.
     * @return true if array contains element.
     */
    public boolean contains(E elem) {
        Object[] copyArray;
        synchronized (this) {
            copyArray = Arrays.copyOf(arr, arr.length);
        }
        boolean isE = false;
        for (int i = 0; i < copyArray.length; i++) {
            if (elem.equals(copyArray[i])) {
                isE = true;
                break;
            }
        }
        return isE;
    }

    /**
     * Get iterator for passing container.
     * @return iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator();
    }

    /**
     * Inner class implements Iterator.
     */
    private class ArrayIterator implements Iterator<E> {
        /**
         * Copy of arr.
         */
        Object[] copyArray;

        /**
         * Constructor for ArrayIterator.
         * Copy array for iterate.
         */
        public ArrayIterator() {
            synchronized (SynchronizedArrayContainer.this) {
                copyArray = Arrays.copyOf(arr, arr.length);
            }
        }

        /**
         * Cursor for passing.
         */
        private int cursor = 0;

        /**
         * Check next element.
         * @return true if iterator has next number.
         */
        @Override
        public boolean hasNext() {
            return cursor < copyArray.length;
        }

        /**
         * Get next element.
         * @return next element.
         */
        @Override
        @SuppressWarnings(value = "unchecked")
        public E next() {
            E elem;
            if (hasNext()) {
                elem = (E) copyArray[cursor++];
            } else {
                throw new NoSuchElementException();
            }
            return elem;
        }
    }
}
