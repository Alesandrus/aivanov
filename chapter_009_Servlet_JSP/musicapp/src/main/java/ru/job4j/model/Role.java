package ru.job4j.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Класс, обозначающий сущность - роль.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 25.12.2017
 */
public class Role implements Serializable {
    /**
     * ID роли.
     */
    private int id;

    /**
     * Имя роли.
     */
    private String name;

    /**
     * Геттер для ID.
     * @return ID роли.
     */
    public int getId() {
        return id;
    }

    /**
     * Сеттер для ID роли.
     * @param id роли.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Геттер для имения роли.
     * @return имя роли.
     */
    public String getName() {
        return name;
    }

    /**
     * Сеттер для имени роли.
     * @param name роли.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Оценивает роли. Равны если названия ролей одинаковые.
     *
     * @param o другой объект.
     * @return true если условия равенства удовлетворены.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return Objects.equals(name, role.name);
    }

    /**
     * Получает хэшкод по названию роли.
     *
     * @return хэшкод.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
