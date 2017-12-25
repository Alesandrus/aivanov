package ru.job4j.dao;

import ru.job4j.model.Address;

import java.util.List;

/**
 * Абстрактный класс DAO для адреса.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 25.12.2017
 */
public abstract class AddressDAO implements EntityDAO<Address> {
    /**
     * Создание адреса.
     *
     * @param entity адрес.
     * @return true если адрес создался успешно.
     */
    @Override
    public abstract boolean create(Address entity);

    /**
     * Получение всех адресов.
     *
     * @return список адресов.
     */
    @Override
    public abstract List<Address> getAll();

    /**
     * Получение адреса по ID.
     *
     * @param id адреса.
     * @return адрес.
     */
    @Override
    public abstract Address getByID(int id);

    /**
     * Обновить адрес.
     *
     * @param entity адрес.
     * @return true если адрес успешно обновлен.
     */
    @Override
    public abstract boolean update(Address entity);

    /**
     * Удалить адрес.
     *
     * @param entity адрес.
     * @return true если адрес успешно удален.
     */
    @Override
    public abstract boolean delete(Address entity);
}
