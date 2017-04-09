package ru.job4j.set;

/**
 * Interface SimpleSet.
 *
 * @author Alexander Ivanov
 * @since 03.04.2017
 * @version 1.0
 */
public interface SimpleSet<E> extends Iterable<E> {
    void add(E elem);
}
