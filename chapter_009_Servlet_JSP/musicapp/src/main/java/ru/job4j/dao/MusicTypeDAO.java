package ru.job4j.dao;

import ru.job4j.model.MusicType;

import java.util.List;

/**
 * Абстрактный класс DAO для музыкального типа.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 25.12.2017
 */
public abstract class MusicTypeDAO implements EntityDAO<MusicType> {

    /**
     * Создание музыкального типа.
     *
     * @param entity музыкальный тип.
     * @return true если музыкальный тип создался успешно.
     */
    @Override
    public abstract boolean create(MusicType entity);

    /**
     * Получение всех музыкальных типов.
     *
     * @return список музыкальных типов.
     */
    @Override
    public abstract List<MusicType> getAll();

    /**
     * Получение музыкального типа по ID.
     *
     * @param id музыкального типа.
     * @return музыкальный тип.
     */
    @Override
    public abstract MusicType getByID(int id);

    /**
     * Обновить музыкальный тип.
     *
     * @param entity музыкальный тип.
     * @return true если музыкальный тип успешно обновлен.
     */
    @Override
    public abstract boolean update(MusicType entity);

    /**
     * Удалить музыкальный тип.
     *
     * @param entity музыкальный тип.
     * @return true если музыкальный тип успешно удален.
     */
    @Override
    public abstract boolean delete(MusicType entity);
}
