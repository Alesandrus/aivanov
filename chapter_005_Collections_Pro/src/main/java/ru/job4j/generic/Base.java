package ru.job4j.generic;

import java.util.Objects;

/**
 * Base.
 *
 * @author Alexander Ivanov
 * @since 31.03.2017
 * @version 1.0
 */
public abstract class Base {


    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
