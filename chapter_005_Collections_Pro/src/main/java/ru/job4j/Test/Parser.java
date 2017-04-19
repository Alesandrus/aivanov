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
    public static Map<String, HashMap<Integer, Order>> scan(String path) {
        Map<String, HashMap<Integer, Order>> bookList = new TreeMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String s;
            while ((s = reader.readLine()) != null) {
                if (s.charAt(1) == 'A') {
                    String[] array = parseAdd(s);
                    int id = Integer.valueOf(array[4]);
                    HashMap<Integer, Order> map = bookList.get(array[0]);
                    if (map == null) {
                        map = new HashMap<>();
                        bookList.put(array[0], map);
                    }
                    map.put(id, new Order(array[0], array[1], Float.valueOf(array[2]), Integer.valueOf(array[3]), Integer.valueOf(array[4])));
                } else if (s.charAt(1) == 'D') {
                    String[] array = parseDelete(s);
                    int id = Integer.valueOf(array[1]);
                    bookList.get(array[0]).remove(id);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public static String[] parseAdd(String s) {
        char[] arr = s.toCharArray();
        String[] array = new String[5];
        boolean flag = false;
        int start = 15;
        int index = 0;
        for (int end = start; end < arr.length - 2; end++) {
            if (arr[end] == '\"') {
                if (flag) {
                    array[index++] = s.substring(start + 1, end);
                    flag = false;
                } else {
                    flag = true;
                }
                start = end;
            }
        }
        return array;
    }

    public static String[] parseDelete(String s) {
        char[] arr = s.toCharArray();
        String[] array = new String[2];
        boolean flag = false;
        int start = 17;
        int index = 0;
        for (int end = start; end < arr.length - 2; end++) {
            if (arr[end] == '\"') {
                if (flag) {
                    array[index++] = s.substring(start + 1, end);
                    flag = false;
                } else {
                    flag = true;
                }
                start = end;
            }
        }
        return array;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Map<String, HashMap<Integer, Order>> books = Parser.scan("D:\\orders.xml");
        Map<String, OrderBook> map = new HashMap<>();
        for (Map.Entry<String, HashMap<Integer, Order>> m : books.entrySet()) {
            String name = m.getKey();
            OrderBook book = new OrderBook(name, m.getValue());
            map.put(name, book);
            Thread thread = new Thread(book, name);
            thread.start();
        }


        System.out.println(System.currentTimeMillis() - start);
        /*String[] arr = Parser.parseAdd("<AddOrder book=\"book-3\" operation=\"SELL\" price=\"100.30\" volume=\"96\" orderId=\"134\" />");
        for (String s : arr) {
            System.out.println(s);
        }
        String[] arr1 = Parser.parseDelete("<DeleteOrder book=\"book-2\" orderId=\"30\" />");
        for (String s : arr1) {
            System.out.println(s);
        }*/
    }
}
