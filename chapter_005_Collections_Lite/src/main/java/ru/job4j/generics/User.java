package ru.job4j.generics;

/**
 * Class User.
 *
 * @author Alexander Ivanov
 * @since 23.03.2017
 * @version 1.0
 */
public class User {
    /**
     * Static ID.
     */
    private static int idNumber = 0;

    /**
     * User ID.
     */
    private int id = ++idNumber;

    /**
     * User name.
     */
    private String name;

    /**
     * User city.
     */
    private String city;

    /**
     * User constructor.
     * @param name of user.
     * @param city of user.
     */
    public User(String name, String city) {
        this.name = name;
        this.city = city;
    }

    /**
     * Getter for user ID.
     * @return ID.
     */
    public int getId() {
        return this.id;
    }
}
