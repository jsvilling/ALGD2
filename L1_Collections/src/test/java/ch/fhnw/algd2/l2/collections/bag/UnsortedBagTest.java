package ch.fhnw.algd2.l2.collections.bag;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import ch.fhnw.algd2.l2.collections.MyAbstractCollection;

public class UnsortedBagTest<E extends Comparable<E>> extends
		AbstractBagTest {

	@Override
	protected MyAbstractCollection<Integer> createIntegerCollection(int size) {
		return new UnsortedBag<Integer>(size);
	}

	@Override
	protected Integer[] getExpectedOrderFor(Integer[] values) {
		return values;
	}

	
	@Test(expected = ClassCastException.class)
	public void containsOtherObject() {
		Integer[] numbers = new Integer[] { 1, 2, 3 };
		addNumbersToBag(numbers);
		assertFalse(bag.contains("Asdf"));
	}
	
}
