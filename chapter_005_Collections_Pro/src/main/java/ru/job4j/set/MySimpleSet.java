package ru.job4j.set;

import ru.job4j.list.LinkedContainer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class MySimpleSet.
 *
 * @author Alexander Ivanov
 * @since 03.04.2017
 * @version 1.0
 */
public class MySimpleSet<E> implements SimpleSet<E> {

    private LinkedContainer<E>[] tab;
    private int tabSize;
    private int factSize;

    public MySimpleSet() {
        tabSize = 16;
        this.tab = new LinkedContainer[tabSize];
    }

    @Override
    public void add(E e) {
        growTab();
        int hashElem = e.hashCode();
        int index = hashElem % tabSize;
        if (tab[index] == null) {
            tab[index] = new LinkedContainer<E>();
        }
        if (!tab[index].contains(e)) {
            tab[index].add(e);
        }
        factSize++;
    }

    @Override
    public Iterator iterator() {
        return new Itr();
    }

    private void growTab() {
        if (factSize > 0.75 * tabSize) {
            LinkedContainer<E>[] copyTab = tab;
            tabSize = tabSize * 2;
            factSize = 0;
            tab = new LinkedContainer[tabSize];
            for (int i = 0; i < copyTab.length; i++) {
                if (copyTab[i] != null) {
                    for (int j = 0; j < copyTab[i].getSize(); j++) {
                        E elem = copyTab[i].get(j);
                        this.add(elem);
                    }
                }
            }
        }
    }

    private class Itr<E> implements Iterator<E> {

        private int indexItr = 0;
        private ArrayList<Iterator<E>> listItr = new ArrayList<>();
        private Iterator<E> factIter;

        public Itr() {
            for (int i = 0; i < tabSize; i++) {
                if (tab[i] != null) {
                    Iterator iterator = tab[i].iterator();
                    listItr.add(iterator);
                }
            }
            if (listItr.size() > 0) {
                factIter = listItr.get(0);
            }
        }


        @Override
        public boolean hasNext() {
            boolean isNext = true;
            if (indexItr == listItr.size()) {
                isNext = factIter.hasNext();
            }
            return isNext;
        }

        @Override
        public E next() {
            E element = null;
            boolean isNext = false;
            while (indexItr <= listItr.size()) {
                if (factIter != null && factIter.hasNext()) {
                    element = factIter.next();
                    isNext = true;
                    break;
                } else {
                    factIter = listItr.get(indexItr++);
                }
            }
            if (!isNext) {
                throw new NoSuchElementException();
            }
            return element;
        }
    }

    public static void main(String[] args) {
        MySimpleSet<Integer> set = new MySimpleSet<>();
        set.add(1);
        set.add(2);
        set.add(777);
        set.add(4);
        set.add(5);
        set.add(77);
        set.add(7);
        set.add(8);
        set.add(100);
        set.add(100);
        set.add(100);
        set.add(100);
        set.add(10);
        set.add(11);
        set.add(12);
        set.add(13);

        for (Integer s : set) {
            System.out.println(s);
        }
        System.out.println("size " + set.factSize);
        System.out.println("len " + set.tab.length);
    }
}
