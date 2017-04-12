package ru.job4j.list;

/**
 * SimpleContainer.
 *
 * @author Alexander Ivanov
 * @since 31.03.2017
 * @version 1.0
 */
public interface SimpleContainer<E> extends Iterable<E> {
    /**
     * Add element to container.
     * @param element for adding.
     */
    void add(E element);

    /**
     * Get element from container.
     * @param index of element.
     * @return element.
     */
    E get(int index);
}
