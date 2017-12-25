package ru.job4j.dao;

import java.util.List;

/**
 * Интерфейс сущности.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 25.12.2017
 * @param <E> element.
 */
public interface EntityDAO<E> {
    /**
     * Создание сущности.
     *
     * @param entity сущность.
     * @return true если сущность создалась успешно.
     */
    boolean create(E entity);

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
    E getByID(int id);

    /**
     * Обновить сущность.
     *
     * @param entity сущность.
     * @return true если сущность успешно обновлена.
     */
    boolean update(E entity);

    /**
     * Удалить сущность.
     *
     * @param entity сущность.
     * @return true если сущность успешно удалена.
     */
    boolean delete(E entity);
}
