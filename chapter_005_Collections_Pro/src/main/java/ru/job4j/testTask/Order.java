package ru.job4j.testTask;

/**
 * @author Alexander Ivanov
 * @version 1.0
 * @since 16.04.2017
 */
public class Order {
    private String name;

    private String operation;

    /**
     * Order price.
     */
    private float price;

    /**
     * Order value.
     */
    private int volume;

    private int id;

    public Order(String name, String operation, float price, int volume, int id) {
        this.name = name;
        this.operation = operation;
        this.price = price;
        this.volume = volume;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getOperation() {
        return operation;
    }

    public float getPrice() {
        return price;
    }

    public int getVolume() {
        return volume;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}