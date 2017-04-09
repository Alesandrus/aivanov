package ru.job4j.list;

/**
 * SimpleContainer.
 *
 * @author Alexander Ivanov
 * @since 31.03.2017
 * @version 1.0
 */
public interface SimpleContainer<E> extends Iterable<E> {
    void add(E element);
    E get(int index);
}
