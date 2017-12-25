package ru.job4j.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Класс, обозначающий сущность - музыкальный тип.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 25.12.2017
 */
public class MusicType implements Serializable, Comparable<MusicType> {
    /**
     * ID музыкального типа.
     */
    private int id;

    /**
     * Название музыкального типа.
     */
    private String type;

    /**
     * Геттер для ID.
     *
     * @return ID типа.
     */
    public int getId() {
        return id;
    }

    /**
     * Сеттер для ID.
     *
     * @param id типа.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Геттер для типа.
     *
     * @return название типа.
     */
    public String getType() {
        return type;
    }

    /**
     * Сеттер для типа.
     *
     * @param type название музыкального типа.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Оценивает музыкальные типы. Равны если названия типов одинаковые.
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
        MusicType musicType = (MusicType) o;
        return Objects.equals(type, musicType.type);
    }

    /**
     * Получает хэшкод по названию музыкального типа.
     *
     * @return хэшкод.
     */
    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    /**
     * Метод для сравнения двух типов. Срванение производится по названию типа (String).
     *
     * @param o другой музыкальный тип.
     * @return the value {@code 0} if the argument string is equal to
     * this string; a value less than {@code 0} if this string
     * is lexicographically less than the string argument; and a
     * value greater than {@code 0} if this string is
     * lexicographically greater than the string argument.
     */
    @Override
    public int compareTo(MusicType o) {
        return this.type.compareTo(o.type);
    }
}
