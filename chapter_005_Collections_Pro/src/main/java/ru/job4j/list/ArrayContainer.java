package ru.job4j.list;

import ru.job4j.generic.SimpleArray;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ArrayContainer.
 *
 * @author Alexander Ivanov
 * @since 31.03.2017
 * @version 1.0
 */
public class ArrayContainer<E> extends SimpleArray<E> implements Iterable<E> {

    private int arrayMod = 0;

    @Override
    public void add(E element) {
        super.add(element);
        arrayMod++;
    }

    @Override
    public E delete(int index) {
        arrayMod++;
        return super.delete(index);
    }

    public boolean contains(E elem) {
        boolean isE = false;
        for (int i = 0; i < getSize(); i++) {
            if (elem.equals(get(i))) {
                isE = true;
                i = getSize();
            }
        }
        return isE;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<E> {

        int cursor = 0;
        int iteratorMod = arrayMod;

        @Override
        public boolean hasNext() {
            int size = getSize();
            return cursor < size;
        }

        @Override
        public E next() {
            checkMod();
            E elem;
            if (hasNext()) {
                elem = get(cursor++);
            } else {
                throw new NoSuchElementException();
            }
            return elem;
        }

        private void checkMod() {
            if (iteratorMod != arrayMod) {
                throw new ConcurrentModificationException();
            }
        }
    }

    //psvm delete
    public static void main(String[] args) {
        ArrayContainer<Integer> container = new ArrayContainer<>();
        container.add(1);
        container.add(2);
        for (Integer i : container) {
            System.out.println(i);
        }
    }
}
