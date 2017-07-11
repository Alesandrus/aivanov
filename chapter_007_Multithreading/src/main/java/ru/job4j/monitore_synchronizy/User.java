package ru.job4j.monitore_synchronizy;

/**
 * Created by Ivanov_ab on 11.07.2017.
 */
public class User {
    private static int cuurentId;
    private int id;
    private int amount;

    public User() {
        this.id = ++cuurentId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }
}
