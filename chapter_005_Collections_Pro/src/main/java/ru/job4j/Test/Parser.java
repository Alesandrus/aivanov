package ru.job4j.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Alexander Ivanov
 * @version 1.0
 * @since 15.04.2017
 */
public class Parser {
    public static List<OrderBook> scan(String path) {
        List<OrderBook> bookList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String s;
            while ((s = reader.readLine()) != null) {
                if (s.startsWith("<A")) {
                    String[] arrayOfAttributes = Parser.parseAdd(s);
                    boolean operation = arrayOfAttributes[1].equals("BUY");
                    Order order =
                            new Order(operation, Double.parseDouble(arrayOfAttributes[2]),
                                    Integer.parseInt(arrayOfAttributes[3]));
                    OrderBook book = new OrderBook(arrayOfAttributes[0]);
                    int indexOfBook = bookList.indexOf(book);
                    try {
                        bookList.get(indexOfBook).put(Integer.parseInt(arrayOfAttributes[4]), order);
                    } catch (IndexOutOfBoundsException e) {
                        book.put(Integer.parseInt(arrayOfAttributes[4]), order);
                        bookList.add(book);
                    }
                } else if (s.startsWith("<D")) {
                    String[] arrayOfAttributes = Parser.parseDelete(s);
                    OrderBook book = new OrderBook(arrayOfAttributes[0]);
                    int indexOfBook = bookList.indexOf(book);
                    bookList.get(indexOfBook).delete(Integer.parseInt(arrayOfAttributes[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public static String[] parseAdd(String s) {
        String[] array = new String[5];
        array[0] = matchAndReturn(s, "book=\"", "book=\".*?\"");
        array[1] = matchAndReturn(s, "operation=\"", "operation=\".*?\"");
        array[2] = matchAndReturn(s, "price=\"", "price=\".*?\"");
        array[3] = matchAndReturn(s, "volume=\"", "volume=\".*?\"");
        array[4] = matchAndReturn(s, "orderId=\"", "orderId=\".*?\"");
        return array;
    }

    public static String[] parseDelete(String s) {
        String[] array = new String[2];
        array[0] = matchAndReturn(s, "book=\"", "book=\".*?\"");
        array[1] = matchAndReturn(s, "orderId=\"", "orderId=\".*?\"");
        return array;
    }

    private static String matchAndReturn(String s, String regBegin, String endBegin) {
        Pattern pattern = Pattern.compile(regBegin);
        Matcher m = pattern.matcher(s);
        int start = 0;
        while (m.find()) {
            start = m.end();
        }
        pattern = Pattern.compile(endBegin);
        m = pattern.matcher(s);
        int end = 0;
        while (m.find()) {
            end = m.end();
        }
        return s.substring(start, end - 1);
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<OrderBook> books = Parser.scan("D:\\orders.xml");
        System.out.println(System.currentTimeMillis() - start);
        /*Collections.sort(books);
        Map<Integer, Order> map = books.get(1).getMap();
        map = new TreeMap<>(map);
        for (Map.Entry<Integer, Order> m : map.entrySet()) {
            System.out.println("id - " + m.getKey() + " operation - " + m.getValue().isOperation() + " price - "
                    + m.getValue().getPrice() + " volume - " + m.getValue().getVolume());
        }*/
    }
}
