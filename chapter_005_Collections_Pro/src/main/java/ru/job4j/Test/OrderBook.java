package ru.job4j.Test;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author Alexander Ivanov
 * @version 1.0
 * @since 16.04.2017
 */
public class OrderBook implements Comparable<OrderBook> {
    private String name;
    private HashMap<Integer, Order> map = new HashMap<>();

    public OrderBook(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void put(Integer id, Order order) {
        map.put(id, order);
    }

    public void delete(Integer id) {
        map.remove(id);
    }

    public HashMap<Integer, Order> getMap() {
        return map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderBook book = (OrderBook) o;
        return Objects.equals(name, book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(OrderBook o) {
        return name.compareTo(o.name);
    }
}
