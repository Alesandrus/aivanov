package ru.job4j.set;


import ru.job4j.list.ArrayContainer;

import java.util.Iterator;

/**
 * Class SimpleArraySet.
 *
 * @author Alexander Ivanov
 * @since 06.04.2017
 * @version 1.0
 */
public class SimpleArraySet<E> implements SimpleSet<E>{

    /**
     * ArrayContainer for storage elements.
     */
    private ArrayContainer<E> container = new ArrayContainer<E>();

    /**
     * Add element to set.
     * @param elem for adding.
     */
    @Override
    public void add(E elem) {
        if (elem != null && !container.contains(elem)) {
            container.add(elem);
        }
    }

    /**
     * Getting iterator.
     * @return iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return container.iterator();
    }
}
