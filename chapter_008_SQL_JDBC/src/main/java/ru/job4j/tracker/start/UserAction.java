package ru.job4j.tracker.start;

import java.sql.Connection;

/**
 * Interface UserAction.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 06.02.2016
 */
public interface UserAction {
    /**
     * key for actions.
     *
     * @return int key.
     */
    int key();

    /**
     * method for execute action.
     *
     * @param input      for enter information.
     * @param connection to database.
     */
    void execute(Input input, Connection connection);

    /**
     * method for print information.
     *
     * @return String information.
     */
    String info();
}
