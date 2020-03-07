import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.ListIterator;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComparableListTest {

    private static final String[] DATA = {"asdf", "as", "asdas", "aaa"};
    private static final String[] DATA_REVERSE = {"aaa", "asdas", "as", "asdf"};

    @Test
    public void testAddHead() {
        // Given
        ComparableList<String> list = new ComparableList<>();

        // When
        Arrays.stream(DATA).forEach(list::addHead);

        // Then
        assertEquals("[aaa, asdas, as, asdf]", list.toString());
    }

    @Test
    public void testAddTail() {
        // Given
        ComparableList<String> list = new ComparableList<>();

        // When
        Arrays.stream(DATA).forEach(list::addTail);

        // Then
        assertEquals("[asdf, as, asdas, aaa]", list.toString());
    }


    @Test
    public void testRemoveHead() {
        // Given
        ComparableList<String> list = new ComparableList<>();
        Arrays.stream(DATA).forEach(list::addHead);

        // When
        String head = list.removeHead();

        // Then
        assertEquals(DATA[DATA.length - 1], head);
        assertEquals("[asdas, as, asdf]", list.toString());
    }

    @Test
    public void testRemoveTail() {
        // Given
        ComparableList<String> list = new ComparableList<>();
        Arrays.stream(DATA).forEach(list::addHead);

        // When
        String tail = list.removeTail();

        // Then
        assertEquals(DATA[0], tail);
        assertEquals("[aaa, asdas, as]", list.toString());
    }

    @Test
    public void testIterator_IteratingForwardAndBack() {
        // Given
        ComparableList<String> list = new ComparableList<>();
        Arrays.stream(DATA).forEach(list::addTail);

        // When
        ListIterator<String> iterator = list.iterator();

        String[] forward = new String[DATA.length];
        String[] backwards = new String[DATA.length];

        int f = 0;
        while (iterator.hasNext()) {
            forward[f++] = iterator.next();
        }
        int b = 0;
        while (iterator.hasPrevious()) {
            backwards[b++] = iterator.previous();
        }

        // Then
        assertEquals(DATA.length, backwards.length);
        assertEquals(DATA.length, forward.length);
        Assertions.assertArrayEquals(DATA_REVERSE, backwards);
        Assertions.assertArrayEquals(DATA, forward);
    }

    @Test
    public void testIterator_previousOnHead() {
        // Given
        ComparableList<String> list = new ComparableList<>();
        Arrays.stream(DATA).forEach(list::addHead);
        ListIterator<String> iterator = list.iterator();

        // When, Then
        Assertions.assertThrows(IllegalStateException.class, iterator::previous);
    }

    @Test
    public void testIterator_nextOnTail() {
        // Given
        ComparableList<String> list = new ComparableList<>();
        Arrays.stream(DATA).forEach(list::addHead);
        ListIterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }

        // When, Then
        Assertions.assertThrows(IllegalStateException.class, iterator::next);
    }

    @Test
    public void testIterator_add() {
        // Given
        ComparableList<String> list = new ComparableList<>();
        Arrays.stream(DATA).forEach(list::addHead);
        ListIterator<String> iterator = list.iterator();
        IntStream.range(0, 2).forEach(i -> iterator.next());
        String newElement = "IAMSUPERNEW";

        // When
        iterator.add(newElement);

        // Then
        assertEquals("[aaa, asdas, IAMSUPERNEW, as, asdf]", list.toString());
    }


    @Test
    public void testIterator_remove() {
        // Given
        ComparableList<String> list = new ComparableList<>();
        Arrays.stream(DATA).forEach(list::addHead);
        ListIterator<String> iterator = list.iterator();
        IntStream.range(0, 2).forEach(i -> iterator.next());

        // When
        iterator.remove();

        // Then
        assertEquals("[aaa, as, asdf]", list.toString());
    }

    @Test
    public void testIterator_previousIndex() {
        // Given
        ComparableList<String> list = new ComparableList<>();
        Arrays.stream(DATA).forEach(list::addHead);
        ListIterator<String> iterator = list.iterator();
        IntStream.range(0, 2).forEach(i -> iterator.next());

        // When
        int i = iterator.previousIndex();

        // Then
        assertEquals(2, i);
    }

    @Test
    public void testIterator_previousIndexAtHead() {
        // Given
        ComparableList<String> list = new ComparableList<>();
        Arrays.stream(DATA).forEach(list::addHead);
        ListIterator<String> iterator = list.iterator();

        // When, Then
        Assertions.assertThrows(IllegalStateException.class, iterator::previousIndex);
    }

    @Test
    public void testIterator_nextIndex() {
        // Given
        ComparableList<String> list = new ComparableList<>();
        Arrays.stream(DATA).forEach(list::addHead);
        ListIterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }

        // When, Then
        Assertions.assertThrows(IllegalStateException.class, iterator::nextIndex);
    }

    @Test
    public void testIterator_DefaultStartIndex() {
        // Given
        ComparableList<String> list = new ComparableList<>();
        Arrays.stream(DATA).forEach(list::addHead);

        // When
        ListIterator<String> iterator = list.iterator();

        // Then
        assertEquals(0, iterator.nextIndex());
        assertEquals(DATA_REVERSE[0], iterator.next());
    }

    @Test
    public void testIterator_CustomIndex() {
        // Given
        ComparableList<String> list = new ComparableList<>();
        Arrays.stream(DATA).forEach(list::addHead);

        // When
        ListIterator<String> iterator = list.iterator(2);

        // Then
        assertEquals(2, iterator.nextIndex());
        assertEquals(DATA_REVERSE[2], iterator.next());
    }
}