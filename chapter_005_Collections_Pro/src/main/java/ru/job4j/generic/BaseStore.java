package ru.job4j.generic;

import java.util.NoSuchElementException;

/**
 * BaseStore.
 *
 * @author Alexander Ivanov
 * @since 31.03.2017
 * @version 1.0
 */
public abstract class BaseStore<T extends Base> implements Store<T> {

    /**
     *
     */
    private SimpleArray<T> simpleArray = new SimpleArray<>();

    @Override
    public void add(T element) {
        simpleArray.add(element);
    }

    @Override
    public void update(String id, T element) {
        int index = getIndexById(id);
        simpleArray.update(index, element);
    }

    @Override
    public void delete(String id) {
        int index = getIndexById(id);
        simpleArray.delete(index);
    }

    private int getIndexById(String id) {
        int index = -1;
        boolean hasID = false;
        for (int i = 0; i < simpleArray.getSize(); i++) {
            if (id != null && simpleArray.get(i).getId().equals(id)) {
                index = i;
                hasID = true;
                i = simpleArray.getSize();
            }
        }
        if (!hasID) {
            throw new NoSuchElementException();
        }
        return index;
    }
}
