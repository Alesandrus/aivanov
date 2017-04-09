package ru.job4j.iterator;

import java.util.*;

/**
 * Iterator of iterators.
 *
 * @author Alexander Ivanov
 * @since 31.03.2017
 * @version 1.0
 */
public class IteratorIterators implements ConvertIterator {

    private Iterator<Iterator<Integer>> allIterators;

    private  Iterator<Integer> factIterator;

    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        this.allIterators = it;
        if (it.hasNext()) {
            this.factIterator = it.next();
        }
        return this;
    }

    @Override
    public boolean hasNext() {
        return factIterator.hasNext() || allIterators.hasNext();
    }

    @Override
    public Integer next() {
        Integer element = null;
        boolean hasNextElement = false;
        if(factIterator.hasNext()) {
            element = factIterator.next();
            hasNextElement = true;
        } else if (allIterators.hasNext()) {
            while (allIterators.hasNext()) {
                factIterator = allIterators.next();
                if (factIterator.hasNext()) {
                    element = factIterator.next();
                    hasNextElement = true;
                    break;
                } else {
                    hasNextElement = false;
                }
            }
        }
        if (!hasNextElement) {
            throw new NoSuchElementException();
        }
        return element;
    }

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3));
        Iterator<Integer> it1 = list1.iterator();
        List<Integer> list3 = new ArrayList<>(Arrays.asList(6, 8, 9));
        Iterator<Integer> it3 = list3.iterator();
        int[] arr = {3, 5, 7};
        EvenIterator it2 = new EvenIterator(arr);
        List<Iterator<Integer>> iteratorList = new ArrayList<>(Arrays.asList(it1, it2, it3));
        Iterator<Iterator<Integer>> iterInt = iteratorList.iterator();
        IteratorIterators iteratorIterators = new IteratorIterators();
        Iterator<Integer> i = iteratorIterators.convert(iterInt);
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }
}
