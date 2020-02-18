package ch.fhnw.algd2.collections.bag;

import static org.junit.Assert.assertTrue;
import ch.fhnw.algd2.collections.AbstractCollectionTest;

public abstract class AbstractBagTest extends AbstractCollectionTest {

	@Override
	protected void addNumbersToBag(Integer[] numbers) {
		for (Integer number : numbers) {
			assertTrue(bag.add(number));
		}
	}

}
