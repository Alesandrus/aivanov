package ru.job4j.dao;

import ru.job4j.model.User;

import java.util.List;

/**
 * Абстрактный класс DAO для адреса.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 25.12.2017
 */
public abstract class UserDAO implements EntityDAO<User> {
    /**
     * Создание пользователя.
     *
     * @param entity пользователь.
     * @return true если пользователь создался успешно.
     */
    @Override
    public abstract boolean create(User entity);

    /**
     * Получение всех пользователей.
     *
     * @return список пользователей.
     */
    @Override
    public abstract List<User> getAll();

    /**
     * Получение пользователя по ID.
     *
     * @param id пользователя.
     * @return пользователя.
     */
    @Override
    public abstract User getByID(int id);

    /**
     * Обновить пользователя.
     *
     * @param entity пользователь.
     * @return true если пользователь успешно обновлен.
     */
    @Override
    public abstract boolean update(User entity);

    /**
     * Удалить пользователя.
     *
     * @param entity пользователь.
     * @return true если пользователь успешно удален.
     */
    @Override
    public abstract boolean delete(User entity);
}