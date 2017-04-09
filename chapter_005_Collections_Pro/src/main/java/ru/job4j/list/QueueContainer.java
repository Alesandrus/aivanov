package ru.job4j.list;

import java.util.NoSuchElementException;

/**
 * QueueContainer.
 * Add() for adding to end of queue.
 *
 * @author Alexander Ivanov
 * @since 02.04.2017
 * @version 1.0
 */
public class QueueContainer<E> extends LinkedContainer<E> {
    public E poll() {
        if (getSize() == 0) {
            throw new NoSuchElementException();
        }
        E elem = remove(0);
        return elem;
    }

    public static void main(String[] args) {
        QueueContainer<String> container = new QueueContainer<>();
        container.add("1");
        container.add("2");
        container.add("3");
        container.add("4");
        container.add("5");
        System.out.println(container.poll());
        System.out.println(container.poll());
        System.out.println("Container contains");
        for (String s : container) {
            System.out.println(s);
        }
    }
}
