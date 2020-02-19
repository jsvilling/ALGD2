package ch.fhnw.algd2.collections.set;

import java.util.Arrays;
import java.util.Set;

import ch.fhnw.algd2.collections.MyAbstractCollection;

public class SortedSet<E extends Comparable<E>> extends MyAbstractCollection<E>
		implements Set<E> {

	public static final int DEFAULT_CAPACITY = 100;
	private int capacity;
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
		return false;
	}

	@Override
	public boolean remove(Object o) {
		return false;
	}


	@Override
	public boolean contains(Object o) {
		return false;
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(data, size());
	}

	@Override
	public int size() {
		return 0;
	}

	public static void main(String[] args) {
		SortedSet<Integer> bag = new SortedSet<Integer>();
		bag.add(2);
		bag.add(2);
		bag.add(1);
		System.out.println(Arrays.toString(bag.toArray()));
	}

}
