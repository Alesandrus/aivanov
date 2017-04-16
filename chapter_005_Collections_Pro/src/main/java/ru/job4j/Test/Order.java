package ru.job4j.Test;

/**
 * @author Alexander Ivanov
 * @version 1.0
 * @since 16.04.2017
 */
public class Order {
    /**
     * Value of operation.
     * buy - true.
     * sell - false.
     */
    private boolean operation;

    /**
     * Order price.
     */
    private double price;

    /**
     * Order value.
     */
    private int volume;

    public Order(boolean operation, double price, int volume) {
        this.operation = operation;
        this.price = price;
        this.volume = volume;
    }

    public boolean isOperation() {
        return operation;
    }

    public double getPrice() {
        return price;
    }

    public int getVolume() {
        return volume;
    }

    public void setOperation(boolean operation) {
        this.operation = operation;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
