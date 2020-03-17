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

    public void remove(E key) {
        Result<E> searchResult = findForgiving(key);
        if (searchResult.result != null) {
            Node<E> p = searchResult.parent;
            Node<E> c = searchResult.result;
            if (c.left == null && c.right == null) {
                p.right = null;
                p.left = null;
            } else if (c.left != null && c.right == null) {
                if (p.left.equals(c)) {
                    p.left = c.left;
                } else {
                    p.right = c.left;
                }
            } else if (c.left == null && c.right != null) {
                if (p.left.equals(c)) {
                    p.left = c.right;
                } else {
                    p.right = c.right;
                }
            } else {
                Node<E> n = c.right.key.compareTo(c.left.key) > 0 ? c.right : c.left;
                if (p.left.equals(c)) {
                    p.left = n;
                } else {
                    p.right = n;
                }
            }
        }
        throw new NoSuchElementException();
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
        throw new NoSuchElementException();
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
        Node<E> left;
        Node<E> right;

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
