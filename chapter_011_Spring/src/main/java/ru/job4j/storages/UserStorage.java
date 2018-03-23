package ru.job4j.storages;

import ru.job4j.interfaces.Storage;
import ru.job4j.models.User;

import java.util.List;

/**
 * Adapter for storage.
 */
public class UserStorage {
    /**
     * Srorage.
     */
    private final Storage storage;

    /**
     * Constructor.
     *
     * @param storage .
     */
    public UserStorage(final Storage storage) {
        this.storage = storage;
    }

    /**
     * Add user to storage.
     *
     * @param user .
     */
    public void add(User user) {
        this.storage.add(user);
    }

    /**
     * Get all users from storage.
     *
     * @return list of users.
     */
    public List<User> getAll() {
        return storage.getAll();
    }

    /**
     * Remove user from storage.
     *
     * @param user .
     * @return true if user is removed.
     */
    public boolean remove(User user) {
        return storage.remove(user);
    }
}
