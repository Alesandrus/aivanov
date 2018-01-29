package ru.job4j.dao;

import java.util.List;

/**
 * Интерфейс сущности.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 29.01.2018
 * @param <E> element.
 */
public interface EntityDAO<E> {
    /**
     * Создание сущности.
     *
     * @param entity сущность.
     */
    void create(E entity);

    /**
     * Получение всех сущностей.
     *
     * @return список сущностей.
     */
    List<E> getAll();

    /**
     * Получение сущности по ID.
     *
     * @param id сущности.
     * @return сущность.
     */
    E getByID(long id);

    /**
     * Обновить сущность.
     *
     * @param entity сущность.
     */
    void update(E entity);

    /**
     * Удалить сущность.
     *
     * @param entity сущность.
     */
    void delete(E entity);
}
