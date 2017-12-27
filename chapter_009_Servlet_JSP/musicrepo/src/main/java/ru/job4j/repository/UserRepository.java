package ru.job4j.repository;

import ru.job4j.model.User;
import ru.job4j.repository.specifications.FindSpecificationForUser;

import java.util.List;

/**
 * Абстрактный класс репозитория пользователя.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 27.12.2017
 */
public abstract class UserRepository {

    /**
     * Добавление пользователя в хранилище.
     * @param user пользователь.
     */
    public abstract void add(User user);

    /**
     * Метод запрашивающий объект реализующий интерфейс спецификации и возвращающий список пользователей.
     *
     * @param findSpecification объект реализующий интерфейс FindSpecificationForUser.
     * @return список музыкальных типов.
     */
    public abstract List<User> querry(FindSpecificationForUser findSpecification);
}
