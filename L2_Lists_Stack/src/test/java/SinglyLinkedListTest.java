import ch.fhnw.algd2.l2.list.SinglyLinkedList;
import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

public class SinglyLinkedListTest {

    @Test
    public void insertFirst_EmptyList() {
        // Given
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        // When
        list.insertFirst("2");

        // Then
        Assert.assertEquals("2", list.get(0));
    }

    @Test
    public void insertFirst_MultipleCalls() {
        // Given
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        // When
        list.insertFirst("2");
        list.insertFirst("q");
        list.insertFirst("4");
        list.insertFirst("5");

        // Then
        Assert.assertEquals("5", list.get(0));
        Assert.assertEquals("4", list.get(1));
        Assert.assertEquals("q", list.get(2));
        Assert.assertEquals("2", list.get(3));
        Assert.assertEquals(4, list.size());
    }

    @Test
    public void insertAfter() {
        // Given
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.insertFirst("2");
        list.insertFirst("q");

        // When
        list.insertAfter("4", 0);
        list.insertAfter("5", 1);

        // Then
        Assert.assertEquals("q", list.get(0));
        Assert.assertEquals("4", list.get(1));
        Assert.assertEquals("5", list.get(2));
        Assert.assertEquals("2", list.get(3));
        Assert.assertEquals(4, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void insertAfter_NegativeIndex() {
        // Given
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.insertFirst("2");

        // When
        list.insertAfter("a", -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void insertAfter_IndexLargerThanSize() {
        // Given
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.insertFirst("2");

        // When
        list.insertAfter("a", 500);
    }

    @Test
    public void getFirst() {
        // Given
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.insertFirst("2");
        list.insertFirst("q");

        // When
        String first = list.getFirst();

        // Then
        Assert.assertEquals("q", first);
    }

    @Test(expected = NoSuchElementException.class)
    public void getFirst_EmptyList() {
        // Given
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        // When
        list.getFirst();
    }

    @Test
    public void removeFirst() {
        // Given
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.insertFirst("2");
        list.insertFirst("q");
        list.insertAfter("4", 0);
        list.insertAfter("5", 1);

        // When
        list.removeFirst();

        // Then
        Assert.assertEquals("4", list.get(0));
        Assert.assertEquals("5", list.get(1));
        Assert.assertEquals("2", list.get(2));
        Assert.assertEquals(3, list.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void removeFirst_EmptyList() {
        // Given
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        // When
        list.removeFirst();
    }

    @Test
    public void remove() {
        // Given
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.insertFirst("2");
        list.insertFirst("q");
        list.insertAfter("4", 0);
        list.insertAfter("5", 1);

        // When
        list.remove(2);

        // Then
        Assert.assertEquals("q", list.get(0));
        Assert.assertEquals("4", list.get(1));
        Assert.assertEquals("2", list.get(2));
        Assert.assertEquals(3, list.size());
    }

    @Test
    public void removeAll() {
        // Given
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.insertFirst("2");
        list.insertFirst("q");
        list.insertAfter("4", 0);
        list.insertAfter("5", 1);

        // When
        list.removeAll();

        // Then
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void testToString() {
        // Given
        // Given
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.insertFirst("2");
        list.insertFirst("q");
        list.insertAfter("4", 0);
        list.insertAfter("5", 1);

        // When
        String stringList = list.toString();

        // Then
        Assert.assertEquals("[q, 4, 5, 2]", stringList);
    }
}