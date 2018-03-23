package ru.job4j.storages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.job4j.interfaces.Storage;
import ru.job4j.models.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of storage. Store user into file system.
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class MemoryStorage implements Storage {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * List of users in random access memory.
     */
    private List<User> users = new ArrayList<>();

    /**
     * Constructor.
     */
    @Autowired
    public MemoryStorage() {
    }

    /**
     * Add user to file.
     *
     * @param user .
     */
    @Override
    public synchronized void add(User user) {
        loadUsers();
        users.add(user);
        URL url = getClass().getClassLoader().getResource("users.str");
        if (url != null) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(url.getPath()))) {
                oos.writeObject(users);
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Get all users from file.
     *
     * @return all users.
     */
    @Override
    public synchronized List<User> getAll() {
        loadUsers();
        return users;
    }

    /**
     * Removes the first occurrence of user from file.
     *
     * @param user .
     * @return true if user is removed, false if storage not contain user.
     */
    @Override
    public synchronized boolean remove(User user) {
        loadUsers();
        boolean isRemoved = users.remove(user);
        URL url = getClass().getClassLoader().getResource("users.str");
        if (url != null) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(url.getPath()))) {
                oos.writeObject(users);
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return isRemoved;
    }

    /**
     * Load all users to random access memory.
     */
    @SuppressWarnings("unchecked")
    private synchronized void loadUsers() {
        URL url = getClass().getClassLoader().getResource("users.str");
        if (url != null) {
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            try {
                fis = new FileInputStream(url.getPath());
                if (fis.available() > 0) {
                    ois = new ObjectInputStream(fis);
                    List<User> loadedUsers = (ArrayList<User>) ois.readObject();
                    if (loadedUsers != null && !loadedUsers.isEmpty()) {
                        users = loadedUsers;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                LOGGER.error(e.getMessage(), e);
            } finally {
                if (ois != null) {
                    try {
                        ois.close();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage(), e);
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage(), e);
                    }
                }
            }
        }
    }
}
