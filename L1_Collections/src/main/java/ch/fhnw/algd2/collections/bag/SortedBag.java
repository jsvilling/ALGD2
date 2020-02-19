package ch.fhnw.algd2.collections.bag;

import java.util.Arrays;

import ch.fhnw.algd2.collections.MyAbstractCollection;

import static java.lang.Math.abs;
import static java.util.Arrays.binarySearch;

public class SortedBag<E extends Comparable<E>> extends MyAbstractCollection<E> {

    public static final int DEFAULT_CAPACITY = 100;
    private int size = 0;
    private int capacity;
    private Object[] data;

    public SortedBag() {
        this(DEFAULT_CAPACITY);
    }

    public SortedBag(int capacity) {
        this.capacity = capacity;
        data = new Object[capacity];
    }

    public static void main(String[] args) {
        SortedBag<Integer> bag = new SortedBag<Integer>();
        bag.add(2);
        bag.add(1);
        System.out.println(Arrays.toString(bag.toArray()));
    }

    @Override
    public boolean add(E e) {
        if (size >= capacity) {
            throw new IllegalStateException();
        }
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

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        E e = (E) o;
        int i = binarySearch(data, 0, size, e);
        if (i >= 0) {
            int c = i;
            while (c < size - 1) {
                data[c] = data[c+1];
                c++;
            }
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        E e = (E) o;
        return binarySearch(data, 0, size, e) >= 0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size());
    }

    @Override
    public int size() {
        return size;
    }

}
