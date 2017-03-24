package ru.job4j.test;

/**
 * Class User.
 *
 * @author Alexander Ivanov
 * @since 23.03.2017
 * @version 1.0
 */
public class User {
    /**
     * User name.
     */
    private String name;

    /**
     * User passport number.
     */
    private int passport;

    /**
     * User constructor.
     * @param name of user.
     * @param passport number of user.
     */
    public User(String name, int passport) {
        this.name = name;
        this.passport = passport;
    }

    /**
     * Name getter.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Pasport number getter.
     * @return passport number.
     */
    public int getPassport() {
        return passport;
    }
}
