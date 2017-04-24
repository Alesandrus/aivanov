package ru.job4j.testTask;

import java.util.*;

/**
 * @author Alexander Ivanov
 * @version 1.0
 * @since 16.04.2017
 */
public class OrderBook {
    String name;
    private TreeMap<Float, Integer>  setBID;
    private TreeMap<Float, Integer>  setASK;
    private HashMap<Integer, Order> allOrders;
    private LinkedList<Pair> pairsBID;
    private LinkedList<Pair> pairsASK;

    public OrderBook(String name, HashMap<Integer, Order> allOrders) {
        this.name = name;
        this.allOrders = allOrders;
        setBID = new TreeMap<>(Comparator.reverseOrder());
        setASK = new TreeMap<>();
        pairsBID = new LinkedList<>();
        pairsASK = new LinkedList<>();
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

    public void addOrdersToBooks() {
        for(Map.Entry<Integer, Order> m : allOrders.entrySet()) {
            Order order = m.getValue();
            if (order.getOperation().equals("SELL")) {
                addToSetASK(order);
            } else if (order.getOperation().equals("BUY")) {
                addToSetBID(order);
            }
        }
    }

    public void showAndFillLists() {
        System.out.println("\t\t\t\tBID");
        for (Map.Entry<Float, Integer> m : setBID.entrySet()) {
            Float price = m.getKey();
            Integer volume = m.getValue();
            System.out.println(String.format("Volume - %7d @ Price - %6.2f", volume, price));
            pairsBID.add(new Pair(price, volume));
        }
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\tASK");
        for (Map.Entry<Float, Integer> m : setASK.entrySet()) {
            Float price = m.getKey();
            Integer volume = m.getValue();
            System.out.println(String.format("\t\t\t\t\t\t\t\t\tVolume - %7d @ Price - %6.2f", volume, price));
            pairsASK.add(new Pair(price, volume));
        }
    }

    public void matchBIDtoASK() {
            while (pairsASK.size() > 0 && pairsBID.size() > 0
                    && pairsBID.peek().getPrice() >= pairsASK.peek().getPrice()) {
                int volumeBID = pairsBID.peek().getVolume();
                int volumeASK = pairsASK.peek().getVolume();
                if (volumeBID > volumeASK) {
                    pairsBID.peek().setVolume(volumeBID - volumeASK);
                    pairsASK.poll();
                } else if (volumeBID < volumeASK) {
                    pairsASK.peek().setVolume(volumeASK - volumeBID);
                    pairsBID.poll();
                } else {
                    pairsASK.poll();
                    pairsBID.poll();
                }
            }

    }

    public void showAfterDeal() {
        System.out.println(String.format("%s\t\t\t\t\t\t\t   %s", System.lineSeparator(), name.toUpperCase()));
        System.out.println("\t\t   BID after deal");
        for (Pair p : pairsBID) {
            System.out.println(String.format("Volume - %7d @ Price - %6.2f", p.getVolume(), p.getPrice()));
        }
        System.out.println("\t\t\t\t\t\t\t\t\t\t\tASK after deal");
        for(Pair p : pairsASK) {
            System.out.println(String.format("\t\t\t\t\t\t\t\t Volume - %7d @ Price - %6.2f", p.getVolume(), p.getPrice()));
        }
    }
}
