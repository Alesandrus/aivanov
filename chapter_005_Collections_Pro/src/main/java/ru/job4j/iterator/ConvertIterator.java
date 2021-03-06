package ru.job4j.iterator;

import java.util.Iterator;

/**
 * Interface for convert iterator of iterators.
 *
 * @author Alexander Ivanov
 * @since 31.03.2017
 * @version 1.0
 */
public interface ConvertIterator extends Iterator<Integer> {
    /**
     * Abstract method for convert Iterator<Iterator<>>.
     * @param it Iterator<Iterator<Integer>>.
     * @return Iterator<Integer>.
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it);
}
