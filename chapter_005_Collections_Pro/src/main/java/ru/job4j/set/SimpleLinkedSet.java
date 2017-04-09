package ru.job4j.set;

import ru.job4j.list.LinkedContainer;

import java.util.Iterator;

/**
 * Class SimpleLinkedSet.
 *
 * @author Alexander Ivanov
 * @since 06.04.2017
 * @version 1.0
 */
public class SimpleLinkedSet<E> implements SimpleSet<E> {

    private LinkedContainer<E> container = new LinkedContainer<E>();

    @Override
    public void add(E elem) {
        if (elem != null && !container.contains(elem)) {
            container.add(elem);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return container.iterator();
    }

    public static void main(String[] args) {
        SimpleLinkedSet<Integer> set = new SimpleLinkedSet<>();
        set.add(1);
        set.add(5);
        set.add(10);
        set.add(1);
        set.add(10);
        set.add(15);
        for (Integer i : set) {
            System.out.println(i);
        }
    }
}
