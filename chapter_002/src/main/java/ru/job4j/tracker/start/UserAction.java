package ru.job4j.tracker.start;

/**
 * Interface UserAction.
 * @author Alexander Ivanov
 * @since 06.02.2016
 * @version 1.0
 */
public interface UserAction {
    int key();
    void execute(Input input, Tracker tracker);
    String info();
}
