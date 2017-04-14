package ru.job4j.set;

/**
 * Interface SimpleSet.
 *
 * @author Alexander Ivanov
 * @since 03.04.2017
 * @version 1.0
 * @param <E> type of elements.
 */
public interface SimpleSet<E> extends Iterable<E> {
    /**
     * Add element to set.
     * @param elem for adding.
     */
    void add(E elem);
}
