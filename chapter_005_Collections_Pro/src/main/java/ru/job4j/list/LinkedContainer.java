package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * LinkedContainer.
 *
 * @author Alexander Ivanov
 * @since 01.04.2017
 * @version 1.0
 */
public class LinkedContainer<E> implements SimpleContainer<E> {

    private int size = 0;

    private Knot<E> first;

    private Knot<E> last;

    private int linkMod = 0;


    private class Knot<E> {
        E elem;
        Knot<E> previous;
        Knot<E> next;

        Knot(Knot<E> prevvious, E elem, Knot<E> next) {
            this.previous = prevvious;
            this.elem = elem;
            this.next = next;
        }
    }

    @Override
    public void add(E element) {
        final Knot<E> l = this.last;
        final Knot<E> newKnot = new Knot<>(l, element, null);
        this.last = newKnot;
        if (l == null) {
            this.first = newKnot;
        } else {
            l.next = newKnot;
        }
        size++;
        linkMod++;
    }

    @Override
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Knot<E> x = first;
        for (int i = 0; i <index; i++) {
            x = x.next;
        }

        return x.elem;
    }

    public E remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Knot<E> x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        Knot<E> prevKnot = x.previous;
        Knot<E> nextKnot = x.next;
        if (prevKnot == null) {
            this.first = nextKnot;
        } else {
            prevKnot.next = nextKnot;
            x.previous = null;
        }
        if (nextKnot == null) {
            this.last = prevKnot;
        } else {
            nextKnot.previous = prevKnot;
            x.next = null;
        }
        size--;
        linkMod++;
        return x.elem;
    }

    public void add(int index, E elem) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index != size) {
            Knot<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            Knot<E> prevKnot = x.previous;
            Knot<E> elemKnot = new Knot<>(null, elem, null);
            if (prevKnot == null) {
                this.first = elemKnot;
                elemKnot.next = x;
                x.previous = elemKnot;
            } else {
                prevKnot.next = elemKnot;
                elemKnot.previous = prevKnot;
                elemKnot.next = x;
                x.previous = elemKnot;
            }
            size++;
            linkMod++;
        } else {
            add(elem);
        }
    }

    public boolean contains(E elem) {
        boolean isE = false;
        for (E e : this) {
            if (e.equals(elem)) {
                isE = true;
                break;
            }
        }
        return isE;
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr<E> implements Iterator<E> {

        private Knot<E> lastReturned;
        private Knot<E> next = (Knot<E>) first;
        private int index = 0;
        private int iteratorMod = linkMod;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public E next() {
            checkMod();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = next;
            next = next.next;
            index++;
            return lastReturned.elem;
        }

        private void checkMod() {
            if (iteratorMod != linkMod) {
                throw new ConcurrentModificationException();
            }
        }
    }

    public static void main(String[] args) {
        LinkedContainer<String> container = new LinkedContainer<>();
        container.add("111");
        container.add("222");
        container.add("333");
        container.add(3, "444");
        Iterator<String> iterator = container.iterator();
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(container.contains("444"));
    }
}
