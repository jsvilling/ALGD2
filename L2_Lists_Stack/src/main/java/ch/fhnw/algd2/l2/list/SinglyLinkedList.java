package ch.fhnw.algd2.l2.list;

import java.util.NoSuchElementException;

/**
 * ch.fhnw.algd2.liststack.SinglyLinkedList implementation for ALGD2
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
        if (original.size > 0) {
            this.insertFirst(original.getFirst());
            for (int i = 1; i < original.size(); i++) {
                this.insertAfter(original.get(i), i - 1);
            }
        }
    }

    public void insertFirst(E e) {
        Element<E> newElement = new Element<>(e);
        if (head.next != null) {
            newElement.next = head.next;
        }
        head.next = newElement;
        size++;
    }

    public void insertAfter(E e, int i) {
        if (i > size || i < 0) {
            throw new IndexOutOfBoundsException();
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
        if (size == 0) {
            throw new NoSuchElementException();
        }
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
        if (size == 0) {
            throw new NoSuchElementException();
        }
        head.next = null;
        size = 0;
    }

    public E getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return head.next.data;
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
