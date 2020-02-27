import org.junit.Assert;
import org.junit.Test;

public class SinglyLinkedListTest {

    private SinglyLinkedList<String> list;

    @Test
    public void insertFirst_EmptyList() {
        // Given
        list = new SinglyLinkedList<>();

        // When
        list.insertFirst("2");

        // Then
        Assert.assertEquals("2", list.get(0));
    }

    @Test
    public void insertFirst_MultipleCalls() {
        // Given
        list = new SinglyLinkedList<>();

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
        list = new SinglyLinkedList<>();
        list.insertFirst("2");
        list.insertFirst("q");

        // When
        list.insertAfter("4", 0);
        list.insertAfter("5", 1);

        // Then
        Assert.assertEquals("4", list.get(0));
        Assert.assertEquals("2", list.get(1));
        Assert.assertEquals("5", list.get(2));
        Assert.assertEquals("q", list.get(3));
        Assert.assertEquals(4, list.size());
    }

    @Test
    public void removeFirst() {
        // Given
        list = new SinglyLinkedList<>();
        list.insertFirst("2");
        list.insertFirst("q");
        list.insertAfter("4", 0);
        list.insertAfter("5", 1);

        // When
        list.removeFirst();

        // Then
        Assert.assertEquals("5", list.get(0));
        Assert.assertEquals("4", list.get(1));
        Assert.assertEquals("q", list.get(2));
        Assert.assertEquals(3, list.size());
    }

    @Test
    public void remove() {
        // Given
        list = new SinglyLinkedList<>();
        list.insertFirst("2");
        list.insertFirst("q");
        list.insertAfter("4", 0);
        list.insertAfter("5", 1);

        // When
        list.remove(2);

        // Then
        Assert.assertEquals("5", list.get(0));
        Assert.assertEquals("4", list.get(1));
        Assert.assertEquals("2", list.get(2));
        Assert.assertEquals(3, list.size());
    }

    @Test
    public void removeAll() {
        // Given
        list = new SinglyLinkedList<>();
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

        // When

        // Then

    }
}