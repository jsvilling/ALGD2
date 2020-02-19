package ch.fhnw.algd2.collections.bag;

import java.util.Arrays;

import ch.fhnw.algd2.collections.MyAbstractCollection;

import static java.lang.Math.abs;
import static java.util.Arrays.binarySearch;

public class SortedBag<E extends Comparable<E>> extends AbstractBag<E> {

    public SortedBag() {
        super(DEFAULT_CAPACITY);
    }

    public SortedBag(int capacity) {
        super(capacity);
        data = new Object[capacity];
    }

    @Override
    public boolean addElement(E e) {
        int i = abs(binarySearch(data, 0, size, e) + 1);
        int c = size;
        while (c > i && c > 0) {
            data[c] = data[c - 1];
            c--;
        }
        data[i] = e;
        size++;
        return true;
    }



}
