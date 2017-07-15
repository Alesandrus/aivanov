package ru.job4j.monitore_synchronizy;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Count.
 *
 * @author Alexander Ivanov
 * @since 15.07.2017
 * @version 1.0
 */
@ThreadSafe
public class Count {

    /**
     * Counter for increment.
     */
    @GuardedBy("this")
    private int count;

    /**
     * Add count 1.
     * @return count.
     */
    public synchronized int increment() {
        return ++count;
    }
}