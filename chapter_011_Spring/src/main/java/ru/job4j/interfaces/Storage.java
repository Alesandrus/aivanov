package ru.job4j.interfaces;

import ru.job4j.models.User;

import java.util.List;

/**
 * Storage for users.
 */
public interface Storage {
    /**
     * Add user to storage.
     *
     * @param user .
     */
    void add(User user);

    /**
     * Get all users from storage.
     *
     * @return all users.
     */
    List<User> getAll();

    /**
     * Removes the first occurrence of user from storage.
     *
     * @param user .
     * @return true if user is removed, false if storage not contain user.
     */
    boolean remove(User user);
}
