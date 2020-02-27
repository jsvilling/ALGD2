/**
 * SinglyLinkedList implementation for ALGD2
 *
 * @author J. VIlling
 */
public class SinglyLinkedList<E> {

    private Element<E> head;
    private int size;

    public SinglyLinkedList() {
        head = new Element<>();
        size = 0;
    }

    public SinglyLinkedList(SinglyLinkedList<E> original) {
        this();
        for (int i = 0; i < original.size(); i++) {
            this.insertAfter(original.get(i), i - 1);
        }
    }

    public void insertFirst(E e) {
        insertAfter(e, -1);
    }

    public void insertAfter(E e, int i) {
        if (i > size) {
            throw new IllegalStateException();
        }
        Element<E> newElement = new Element<>(e);
        Element<E> prev = head;
        int c = 0;
        while (c <= i) {
            c++;
            prev = prev.next;
        }
        newElement.next = prev.next;
        prev.next = newElement;
        size++;
    }

    public void removeFirst() {
        remove(0);
    }

    public void remove(int i) {
        if (size > 0) {
            Element<E> prev = head;
            int c = 0;
            while (c < i) {
                c++;
                prev = prev.next;
            }
            prev.next = prev.next.next;
            size--;
        }
    }

    public void removeAll() {
        head.next = null;
        size = 0;
    }

    public E getFirst() {
        return size == 0 ? null : head.next.data;
    }

    public E get(int i) {
        if (i >= size) {
            throw new IllegalArgumentException();
        }
        Element<E> element = head.next;
        int c = 0;
        while (c < i) {
            element = element.next;
            c++;
        }
        return element.data;
    }

    public int size() {
        return size;
    }

    public String toString() {
        Element<E> element = head.next;
        StringBuilder sb = new StringBuilder("[");
        if (size > 0) {
            sb.append(element.data);
            for (int i = 1; i < size; i++) {
                element = element.next;
                sb.append(", ");
                sb.append(element.data);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private static class Element<E> {
        private E data;
        private Element<E> next;

        private Element() {
            this(null, null);
        }

        private Element(E data) {
            this(data, null);
        }

        private Element(E data, Element<E> next) {
            this.data = data;
            this.next = next;
        }
    }
}
