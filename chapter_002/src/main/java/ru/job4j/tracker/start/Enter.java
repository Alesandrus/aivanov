package ru.job4j.tracker.start;

/**
 * Class Enter.
 * @author Alexander Ivanov
 * @since 04.02.2017
 * @version 1.0
 */
public class Enter {
    private Input input;

    public Enter (Input input) {
        this.input = input;
    }

    public String execute(String question) {
        return input.ask(question);
    }
}
