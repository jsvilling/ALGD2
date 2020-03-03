import java.util.ListIterator;

public class ComparableList<E extends Comparable<E>> implements Iterable<E> {

    private int size = 0;
    private Element<E> head = new Element<>();
    private Element<E> tail = new Element<>();

    public ComparableList() {
        head.next = tail;
        tail.prev = head;
    }

    public static void main(String[] args) {
        ComparableList<String> list = new ComparableList<>();
        list.addHead("1");
        list.addHead("2");
        list.addHead("3");
        list.addHead("4");
        list.addHead("5");
        list.addTail("tail1");
        list.addTail("tail2");
        list.addTail("tail3");
        System.out.println(list);
        ListIterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
            System.out.print(" ");
        }
        System.out.println();
        while (iterator.hasPrevious()) {
            System.out.print(iterator.previous());
            System.out.print(" ");
        }
    }

    @Override
    public ListIterator<E> iterator() {
        return new CListIterator(head);
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

        private Element<E> element;
        private int index;

        private Element<E> next;

        CListIterator(Element<E> head) {
            this.element = head;
            this.next = head.next;
            this.index = -1;
        }

        @Override
        public boolean hasNext() {
            return element.next != tail;
        }

        @Override
        public E next() {
            index++;
            element = element.next;
            return element.data;
        }

        @Override
        public boolean hasPrevious() {
            return element != ComparableList.this.head;
        }

        @Override
        public E previous() {
            index--;
            E data = element.data;
            element = element.prev;
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
            element.prev.next = element.next;
            element.next.prev = element.prev;
            size--;
        }

        @Override
        public void set(E e) {
            element.data = e;
        }

        @Override
        public void add(E e) {
            Element<E> newElement = new Element<>(e, element, element.prev);
            element.prev.next = newElement;
            element.prev = newElement;
            size++;
        }
    }
}
