import java.util.ListIterator;
import java.util.stream.Stream;

public class ComparableList<E extends Comparable<E>> implements Iterable<E> {

    private int size = 0;
    private Element<E> head = new Element<>();
    private Element<E> tail = new Element<>();

    public ComparableList() {
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public ListIterator<E> iterator() {
        return new CListIterator();
    }

    public ListIterator<E> iterator(int index) {
        if (index >= size) {
            throw new IllegalArgumentException();
        }
        return new CListIterator(index);
    }

    public void addHead(E e) {
        Element<E> newElement = new Element<>(e, head.next, head);
        head.next.prev = newElement;
        head.next = newElement;
        size++;
    }

    public void addTail(E e) {
        if (size == 0) {
            addHead(e);
        } else {
            Element<E> newElement = new Element<>(e, tail, tail.prev);
            tail.prev.next = newElement;
            tail.prev = newElement;
            size++;
        }
    }

    public E removeHead() {
        if (size > 0) {
            return remove(head.next);
        }
        throw new IllegalStateException();
    }

    public E removeTail() {
        if (size > 0) {
            return remove(tail.prev);
        }
        throw new IllegalStateException();
    }

    private E remove(Element<E> e) {
        e.prev.next = e.next;
        e.next.prev = e.prev;
        size--;
        return e.data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Element<E> element = head.next;
        sb.append("[");
        sb.append(element.data);
        while (element.next != null && element.next != tail) {
            element = element.next;
            sb.append(", ");
            sb.append(element.data);
        }
        sb.append("]");
        return sb.toString();
    }

    private static class Element<E> {
        private E data;
        private Element<E> next;
        private Element<E> prev;

        Element() {
            this(null, null, null);
        }

        Element(E e, Element<E> next, Element<E> prev) {
            this.data = e;
            this.next = next;
            this.prev = prev;
        }
    }

    private class CListIterator implements ListIterator<E> {

        private int index;

        private Element<E> next;
        private Element<E> returned;

        CListIterator() {
            this.returned = head;
            this.next = head.next;
            this.index = -1;
        }

        CListIterator(int index) {
            this();
            for (int i = 0; i < index; i++) {
                next();
            }
        }

        @Override
        public boolean hasNext() {
            return next != tail;
        }

        @Override
        public E next() {
            index++;
            returned = next;
            next = next.next;
            return returned.data;
        }

        @Override
        public boolean hasPrevious() {
            return returned != head;
        }

        @Override
        public E previous() {
            index--;
            E data = returned.data;
            next = next.prev;
            returned = returned.prev;
            return data;
        }

        @Override
        public int nextIndex() {
            return index + 1;
        }

        @Override
        public int previousIndex() {
            return index;
        }

        @Override
        public void remove() {
            if (returned == null) {
                throw new IllegalStateException();
            } else {
                if (returned == next) {
                    next = returned.next;
                } else index--;
                ComparableList.this.remove(returned);
                returned = null;
            }
        }

        @Override
        public void set(E data) {
            if (returned == null)
                throw new IllegalStateException();
            else {
                if (data == null) {
                    throw new IllegalArgumentException();
                }
                returned.data = data;
            }
        }

        @Override
        public void add(E e) {
            Element<E> newElement = new Element<>(e, next, next.prev);
            next.prev.next = newElement;
            next.prev = newElement;
            size++;
        }
    }
}
