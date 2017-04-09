package ru.job4j.list;

import java.util.EmptyStackException;

/**
 * StackContainer.
 *
 * @author Alexander Ivanov
 * @since 02.04.2017
 * @version 1.0
 */
public class StackContainer<E> extends LinkedContainer<E> {
    public void push(E elem) {
        add(0, elem);
    }

    public E pop() {
        if (getSize() == 0) {
            throw new EmptyStackException();
        }
        E elem = remove(0);
        return elem;
    }



    public static void main(String[] args) {
        StackContainer<String> container = new StackContainer<>();
        container.push("1");
        container.push("2");
        container.push("3");
        container.push("4");
        container.push("5");
        System.out.println(container.pop());
        System.out.println(container.pop());
        for (String s : container) {
            System.out.println(s);
        }
    }
}
