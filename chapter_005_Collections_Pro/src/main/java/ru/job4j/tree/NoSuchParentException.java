package ru.job4j.tree;

/**
 * Class NoSuchParentException.
 *
 * @author Alexander Ivanov
 * @since 14.04.2017
 * @version 1.0
 */
public class NoSuchParentException extends RuntimeException {
    /**
     * toString.
     * @return string.
     */
    @Override
    public String toString() {
        return "Tree has not this parent";
    }
}
