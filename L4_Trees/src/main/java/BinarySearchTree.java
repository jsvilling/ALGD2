import java.util.NoSuchElementException;

import static java.util.Arrays.sort;

public class BinarySearchTree<E extends Comparable<E>> {

    private Node<E> rootNode;

    public BinarySearchTree(E[] values) {
        sort(values);
        rootNode = buildTree(values, 0, values.length);
    }

    private Node<E> buildTree(E[] values, int start, int end) {
        int m = (end - start) / 2;
        Node<E> root = new Node<>(values[m]);
        root.left = buildTree(values, start, m);
        root.right = buildTree(values, m, end);
        return root;
    }

    public void insert(E key) {
        Result<E> searchResult = findForgiving(key);
        if (searchResult.result == null) {
            Node<E> node = new Node<>(key);
            if (searchResult.parent.left == null) {
                searchResult.parent.left = node;
            } else {
                searchResult.parent.right = node;
            }
        }
    }

    public E find(E key) {
        Result<E> result = findForgiving(key);
        if (result.result != null) {
            return result.result.key;
        }
        throw new NoSuchElementException();
    }

    public boolean contains(E key) {
        return findForgiving(key).result != null;
    }

    private Result<E> findForgiving(E key) {
        Node<E> previous = null;
        Node<E> current = rootNode;
        while (current != null) {
            if (current.key.equals(key)) {
                return new Result(current, previous);
            }
            previous = current;
            current = current.key.compareTo(key) > 0 ? current.left : current.right;
        }
        return null;
    }

    private static class Node<E> {
        E key;
        Node left;
        Node right;

        Node(E key) {
            this.key = key;
        }
    }

    private static class Result<E> {
        private Node<E> result;
        private Node<E> parent;

        private Result(Node<E> result, Node<E> parent) {
            this.result = result;
            this.parent = parent;
        }
    }

}
