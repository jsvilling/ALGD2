package ch.fhnw.algd2.collections.set;

import java.util.Arrays;
import java.util.Set;

import ch.fhnw.algd2.collections.MyAbstractCollection;

import static java.lang.Math.abs;
import static java.util.Arrays.binarySearch;

public class SortedSet<E extends Comparable<E>> extends MyAbstractCollection<E>
		implements Set<E> {

	public static final int DEFAULT_CAPACITY = 100;
	private int capacity;
	private int size = 0;
	private Object[] data;

	public SortedSet() {
		this(DEFAULT_CAPACITY);
	}

	public SortedSet(int capacity) {
		this.capacity = capacity;
		data = new Object[capacity];
	}

	@Override
	public boolean add(E e) {
		if (e == null) {
			throw new NullPointerException();
		}
		if (size >= capacity) {
			throw new IllegalStateException();
		}
		if (contains(e)) {
			return false;
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
