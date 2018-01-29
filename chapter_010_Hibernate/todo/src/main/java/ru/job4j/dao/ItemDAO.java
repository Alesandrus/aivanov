package ru.job4j.dao;

import ru.job4j.model.Item;

import java.util.List;

/**
 * Абстрактный класс DAO для Item.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 29.01.2018
 */
public abstract class ItemDAO implements EntityDAO<Item> {
    /**
     * Создание задания.
     *
     * @param entity задание.
     */
    @Override
    public abstract void create(Item entity);

    /**
     * Получение всех задний.
     *
     * @return список заданий.
     */
    @Override
    public abstract List<Item> getAll();

    /**
     * Получение задания по ID.
     *
     * @param id задания.
     * @return задание.
     */
    @Override
    public abstract Item getByID(long id);

    /**
     * Обновить задание.
     *
     * @param entity задание.
     */
    @Override
    public abstract void update(Item entity);

    /**
     * Удалить задание.
     *
     * @param entity задание.
     */
    @Override
    public abstract void delete(Item entity);

    /**
     * Обновить статус задания.
     *
     * @param item задание.
     */
    public abstract void updateStatus(Item item);

    /**
     * Получение всех невыполненных задний.
     *
     * @return список невыполненных заданий.
     */
    public abstract List<Item> getAllUnDone();
}