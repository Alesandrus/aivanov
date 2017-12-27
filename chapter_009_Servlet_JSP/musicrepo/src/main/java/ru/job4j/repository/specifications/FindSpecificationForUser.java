package ru.job4j.repository.specifications;

import ru.job4j.model.User;

import java.util.List;

/**
 * Интерфейс для получения списка пользователей.
 */
public interface FindSpecificationForUser {
    /**
     * Получение списка пользователей.
     * @return список пользователей.
     */
    List<User> find();
}
