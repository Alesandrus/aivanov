package ru.job4j.dao;

import ru.job4j.model.Role;

import java.util.List;

/**
 * Класс DAO для CRUD операций с ролью в базе данных Postgres.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 25.12.2017
 */
public abstract class RoleDAO implements EntityDAO<Role> {
    /**
     * Создание роли.
     *
     * @param entity роли.
     * @return true если роль создалася успешно.
     */
    @Override
    public abstract boolean create(Role entity);

    /**
     * Получение всех ролей.
     *
     * @return список ролей.
     */
    @Override
    public abstract List<Role> getAll();

    /**
     * Получение роли по ID.
     *
     * @param id роли.
     * @return роль.
     */
    @Override
    public abstract Role getByID(int id);

    /**
     * Обновить роль.
     *
     * @param entity роль.
     * @return true если роль успешно обновлена.
     */
    @Override
    public abstract boolean update(Role entity);

    /**
     * Удалить роль.
     *
     * @param entity роль.
     * @return true если роль успешно удалена.
     */
    @Override
    public abstract boolean delete(Role entity);
}
