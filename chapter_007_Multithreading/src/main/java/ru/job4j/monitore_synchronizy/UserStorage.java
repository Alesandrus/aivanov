package ru.job4j.monitore_synchronizy;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Created by Ivanov_ab on 11.07.2017.
 */
@ThreadSafe
public class UserStorage {
    private final Map<Integer, User> storage = new HashMap<>();

    public synchronized void add(User user) {
        storage.put(user.getId(), user);
    }

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

    public synchronized User delete(int userID) {
        return storage.remove(userID);
    }

    public boolean transfer(int fromId, int toId, int amount) {
        if(!storage.containsKey(fromId) && !storage.containsKey(toId)) {
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
