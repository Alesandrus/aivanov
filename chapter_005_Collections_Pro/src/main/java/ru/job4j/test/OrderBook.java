package ru.job4j.test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Alexander Ivanov
 * @version 1.0
 * @since 16.04.2017
 */
public class OrderBook implements Runnable {
    String name;
    private TreeMap<Float, Integer>  setBID;
    private TreeMap<Float, Integer>  setASK;
    private HashMap<Integer, Order> allOrders;

    public OrderBook(String name, HashMap<Integer, Order> allOrders) {
        this.name = name;
        this.allOrders = allOrders;
        setBID = new TreeMap<>(Comparator.reverseOrder());
        setASK = new TreeMap<>();
    }

    private void addOrder(Order order, TreeMap<Float, Integer> treeSet) {
        Float price = order.getPrice();
        Integer volume = order.getVolume();
        Integer oldVolume = treeSet.get(price);
        if (oldVolume != null) {
            treeSet.put(price, oldVolume + volume);
        } else {
            treeSet.put(price, volume);
        }
    }

    public void addToSetBID(Order order) {
        addOrder(order, setBID);
    }

    public void addToSetASK(Order order) {
        addOrder(order, setASK);
    }

    public TreeMap<Float, Integer> getSetBID() {
        return setBID;
    }

    public TreeMap<Float, Integer> getSetASK() {
        return setASK;
    }

    @Override
    public void run() {
        for(Map.Entry<Integer, Order> m : allOrders.entrySet()) {
            Order order = m.getValue();
            if (order.getOperation().equals("SELL")) {
                addToSetASK(order);
            } else if (order.getOperation().equals("BUY")) {
                addToSetBID(order);
            }
        }
    }

    public void show() {
        System.out.println("BID");
        for (Map.Entry<Float, Integer> m : setBID.entrySet()) {
            System.out.println("price - " + m.getKey() + "  volume - " + m.getValue());
        }
        System.out.println("ASK");
        for (Map.Entry<Float, Integer> m : setBID.entrySet()) {
            System.out.println("            price - " + m.getKey() + "  volume - " + m.getValue());
        }
    }
}