import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.Consumer;

import static java.util.Arrays.sort;

public class BinarySearchTree<E extends Comparable<E>> {

    public BinarySearchTree(E[] values) {
        sort(values);
        rootNode = buildTree(values, 0, values.length - 1);
    }

    private Node<E> rootNode;

    public static void main(String[] args) {
        Integer[] is = {1, 2, 3, 4, 5, 6, 7};
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(is);
        System.out.println("In Order: ");
        tree.forEach(System.out::println);

        System.out.println("In Level Order: ");
        tree.forEachLevelOrdered(System.out::println);
    }

    private Node<E> buildTree(E[] values, int start, int end) {
        Node node = null;
        if (start <= end) {
            int m = (start + end) / 2;
            node = new Node(values[m]);
            node.left = buildTree(values, start, m - 1);
            node.right = buildTree(values, m + 1, end);
        }
        return node;
    }

    public void show() {
        show(rootNode, 0);
    }

    private void show(Node root, int level) {
        if (root != null) {
            show(root.right, level + 1);
            for (int i = 0; i < level; ++i)
                System.out.print("    ");
            System.out.println(root.key);
            show(root.left, level + 1);
        }
    }

    public void remove(E key) {
        SearchResult<E> searchResult = find(key);
        if (searchResult != null && searchResult.node != null) {
            Node<E> p = searchResult.parent;
            Node<E> c = searchResult.node;
            if (c.left == null && c.right == null) {
                p.right = null;
                p.left = null;
            } else if (c.left != null && c.right == null) {
                if (searchResult.isLeftNode) {
                    p.left = c.left;
                } else {
                    p.right = c.left;
                }
            } else if (c.left == null) {
                if (searchResult.isLeftNode) {
                    p.left = c.right;
                } else {
                    p.right = c.right;
                }
            } else {
                Node<E> r = searchResult.node.left;
                while (r.right != null)
                    r = r.right;
                remove(r.key);
                searchResult.node.key = r.key;
            }
        }
    }

    public void insert(E key) {
        SearchResult<E> searchResult = find(key);
        if (searchResult.node == null) {
            if (searchResult.isLeftNode) {
                searchResult.parent.left = new Node(key);
            } else {
                searchResult.parent.right = new Node(key);
            }
        }
    }

    public boolean contains(E key) {
        return find(key).node != null;
    }

    private SearchResult<E> find(E key) {
        boolean isLeftNode = false;
        Node<E> previous = null;
        Node<E> current = rootNode;
        while (current != null) {
            if (current.key.equals(key)) {
                return new SearchResult(current, previous, isLeftNode);
            }
            previous = current;
            if (current.key.compareTo(key) < 0) {
                current = current.left;
                isLeftNode = true;
            } else {
                current = current.right;
                isLeftNode = true;
            }
        }
        return null;
    }

    public void forEach(Consumer<E> c) {
        traverse(rootNode, c);
    }

    private void traverse(Node<E> root, Consumer<E> c) {
        if (root != null) {
            traverse(root.left, c);
            c.accept(root.key);
            traverse(root.right, c);
        }
    }

    private void forEachLevelOrdered(Consumer<E> c) {
        traverseLevelOrder(c);
    }

    private void traverseLevelOrder(Consumer<E> c) {
        Queue<Node<E>> q = new ArrayDeque<>();
        Node<E> current;
        q.add(rootNode);
        while (!q.isEmpty()) {
            current = q.poll();
            c.accept(current.key);
            if (current.left != null) {
                q.add(current.left);
            }
            if (current.right != null) {
                q.add(current.right);
            }
        }
    }

    private static class Node<E> {
        E key;
        Node<E> left;
        Node<E> right;

        Node(E key) {
            this.key = key;
        }
    }

    private static class SearchResult<E> {
        private Node<E> node;
        private Node<E> parent;
        private boolean isLeftNode;

        private SearchResult(Node<E> node, Node<E> parent, boolean isLeftNode) {
            this.node = node;
            this.parent = parent;
            this.isLeftNode = isLeftNode;
        }
    }


}
