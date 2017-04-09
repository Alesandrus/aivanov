package ru.job4j.map;

import ru.job4j.list.ArrayContainer;

import java.util.Iterator;
import java.util.Objects;

/**
 * Class MyMap.
 *
 * @author Alexander Ivanov
 * @since 07.04.2017
 * @version 1.0
 */
public class MyMap<K, V> implements Iterable<K>{
    private Node[] table;

    private int tabSize;
    private int factSize;

    public MyMap() {
        this.tabSize = 16;
        this.table = new Node[tabSize];
    }

    public V insert(K key, V value) {
        grow();
        int index = hash(key);
        if (table[index] != null) {
            Node<K, V> node = table[index];
            while (node != null) {
                if (node.key.equals(key)) {
                    V prevValue = node.value;
                    node.value = value;
                    factSize++;
                    return prevValue;
                }
                if (node.next == null) {
                    Node<K, V> newNode = new Node<>(key, value, null);
                    node.next = newNode;
                    break;
                }
                node = node.next;
            }
        } else {
            table[index] = new Node<>(key, value, null);
        }
        factSize++;
        return null;
    }

    public V get(K key) {
        int index = hash(key);
        if (table[index] != null) {
            Node<K, V> node = table[index];
            while (node != null) {
                if (node.key.equals(key)) {
                    return node.value;
                }
                node = node.next;
            }
        }
        return null;
    }

    public boolean delete(K key) {
        int index = hash(key);
        if (table[index] != null) {
            Node<K, V> node = table[index];
            if (node.key.equals(key)) {
                if (node.next != null) {
                    table[index] = node.next;
                } else {
                    table[index] = null;
                }
                factSize--;
                return true;
            }
            Node<K, V> prevNode = node;
            while (node != null) {
                if (node.key.equals(key)) {
                    if (node.next != null) {
                        prevNode.next = node.next;
                        node.next = null;
                    } else {
                        prevNode.next = null;
                    }
                    factSize--;
                    return true;
                }
                prevNode = node;
                node = node.next;
            }
        }
        return false;
    }

    public boolean containsKey(K key) {
        int index = hash(key);
        if (table[index] != null) {
            Node<K, V> node = table[index];
            while (node != null) {
                if (node.key.equals(key)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    private int hash(K key) {
        return key.hashCode() % tabSize;
    }

    private void grow() {
        if (factSize > 0.75 * tabSize) {
            Node<K, V>[] copyTable = table;
            tabSize *= 2;
            factSize = 0;
            table = new Node[tabSize];
            for (int i = 0; i < copyTable.length; i++) {
                if (copyTable[i] != null) {
                    Node<K, V> node = copyTable[i];
                    while (node != null) {
                        insert(node.key, node.value);
                        node = node.next;
                    }
                }
            }
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new Itr();
    }

    private class Node<K, V> {
        final K key;
        V value;
        Node next;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private class Itr<K> implements Iterator<K> {
        private final ArrayContainer<K> keyList = new ArrayContainer<K>();
        private Iterator<K> iterator;

        Itr() {
            for (int i = 0; i < table.length; i++) {
                if (table[i] != null) {
                    Node<K, V> node = table[i];
                    while (node != null) {
                        keyList.add(node.key);
                        node = node.next;
                    }
                }
            }
            iterator = keyList.iterator();
        }


        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public K next() {
            return iterator.next();
        }
    }

    public static void main(String[] args) {
        MyMap<Integer, String> myMap = new MyMap<>();
        myMap.insert(3, "3");
        myMap.insert(1, "1");
        myMap.insert(17, "17");
        myMap.insert(4, "4");
        myMap.insert(34, "34");
        myMap.insert(19, "19");
        for (Integer i : myMap) {
            System.out.println(myMap.get(i));
        }
        myMap.insert(2, "2");
        myMap.insert(5, "5");
        myMap.insert(6, "6");
        myMap.insert(7, "7");
        myMap.insert(8, "8");


        myMap.insert(9, "9");

        myMap.insert(10, "10");
        System.out.println("fact " + myMap.factSize);

        myMap.insert(11, "11");
        System.out.println("size " + myMap.table.length);
        System.out.println("fact " + myMap.factSize);
        myMap.insert(12, "12");
        myMap.insert(13, "13");
        myMap.insert(14, "14");
        myMap.insert(15, "15");
        myMap.insert(16, "16");
        System.out.println("Add some");
        for (Integer i : myMap) {
            System.out.println(myMap.get(i));
        }
        System.out.println("size" + myMap.table.length);
    }
}
