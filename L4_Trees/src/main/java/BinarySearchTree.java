
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import static java.util.Arrays.sort;

public class BinarySearchTree<E extends Comparable<E>> {

    private Node<E> rootNode;

    public BinarySearchTree(E[] values) {
        sort(values);
        int[] keys = IntStream.range(0, values.length - 1).toArray();
        rootNode = buildTree(values, 0, values.length);
    }

    private Node<E> buildTree(E[] values, int start, int end) {
        int m = (end - start) / 2;
        Node<E> root = new Node<>(values[m]);
        root.left = buildTree(values, start, m);
        root.right = buildTree(values, m, end);
        return root;
    }

    private static class Node<E> {
        Node(E data) {
            this.data = data;
        }
        int key;
        E data;
        Node left;
        Node right;
    }

    public E find(E key) {
        E result = findForgiving(key);
        if (result != null) {
            return result;
        }
        throw new NoSuchElementException();
    }

    public boolean contains(E key) {
        return findForgiving(key) != null;
    }

    private E findForgiving(E key) {
        Node<E> current = rootNode;
        while (current != null) {
            if (current.data.equals(key)) {
                return rootNode.data;
            }
            current = rootNode.data.compareTo(key) > 0 ? current.left : current.right;
        }
        return null;
    }

}
