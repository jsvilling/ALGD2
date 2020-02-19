package ch.fhnw.algd2.collections.bag;

public class UnsortedBag<E extends Comparable<E>> extends AbstractBag<E> {

	public UnsortedBag() {
		super(DEFAULT_CAPACITY);
	}

	public UnsortedBag(int capacity) {
		super(capacity);
		data = new Object[capacity];
	}

	@Override
	public boolean addElement(E e) {
		data[size] = e;
		size++;
		return true;
	}
}
