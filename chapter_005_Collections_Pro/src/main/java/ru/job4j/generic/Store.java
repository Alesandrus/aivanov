package ru.job4j.generic;

/**
 * Store Interface.
 *
 * @author Alexander Ivanov
 * @since 31.03.2017
 * @version 1.0
 */
public interface Store<T extends Base> {
    void add(T element);
    void update(String id, T element);
    void delete(String id);
}
