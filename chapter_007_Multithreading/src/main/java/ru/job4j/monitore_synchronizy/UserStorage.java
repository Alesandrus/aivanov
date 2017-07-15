package ru.job4j.monitore_synchronizy;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * UserStorage.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 15.07.2017
 */
@ThreadSafe
public class UserStorage {
    /**
     * Storage for users.
     */
    private final Map<Integer, User> storage = new HashMap<>();

    /**
     * Add user to storage.
     *
     * @param user for adding.
     */
    public synchronized void add(User user) {
        storage.put(user.getId(), user);
    }

    /**
     * Setting new amount of money to user.
     *
     * @param userID    of user.
     * @param newAmount to update.
     * @return old amount of user's money.
     */
    public int update(int userID, int newAmount) {
        if (!storage.containsKey(userID)) {
            throw new NoSuchElementException("No such user");
        }
        User user = storage.get(userID);
        int oldValue = user.getAmount();
        synchronized (user) {
            user.setAmount(newAmount);
        }
        return oldValue;
    }

    /**
     * Delete user.
     *
     * @param userID of user.
     * @return deleting user.
     */
    public synchronized User delete(int userID) {
        return storage.remove(userID);
    }

    /**
     * Synchronized transfer money from one user to another.
     *
     * @param fromId user, which give money.
     * @param toId   user, which take money.
     * @param amount for transfer.
     * @return true if transfer is done.
     */
    public boolean transfer(int fromId, int toId, int amount) {
        if (!storage.containsKey(fromId) && !storage.containsKey(toId)) {
            throw new NoSuchElementException("No user");
        }
        User userFrom = storage.get(fromId);
        User userTo = storage.get(toId);
        synchronized (userFrom) {
            int amountUserFrom = userFrom.getAmount();
            if (amountUserFrom < amount) {
                return false;
            }
            synchronized (userTo) {
                update(fromId, amountUserFrom - amount);
                update(toId, userTo.getAmount() + amount);
            }
        }
        return true;
    }
}
