package ch.fhnw.algd2.l2.stack;

import ch.fhnw.algd2.l2.list.SinglyLinkedList;

/**
 * Stack Implementation using {@link SinglyLinkedList <T>} for ALGD2
 *
 * @author J. Villing
 */
public class Stack<T> {
    private final SinglyLinkedList<T> list;

    public Stack() {
        list = new SinglyLinkedList<>();
    }

    public T top() {
        return list.getFirst();
    }

    public void push(T data) {
        list.insertFirst(data);
    }

    public T pop() {
        T first = list.getFirst();
        list.removeFirst();
        return first;
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }

    public String toString() {
        return list.toString();
    }
}
