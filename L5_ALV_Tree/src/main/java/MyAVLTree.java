import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.Consumer;

import static java.util.Arrays.sort;

public class MyAVLTree<E extends Comparable<E>> {
    private Node<E> rootNode;

    public MyAVLTree(E[] values) {
        sort(values);
        rootNode = new Node<>(null);
        rootNode = buildTree(values, 0, values.length - 1);
    }

    public static void main(String[] args) {
        Integer[] is = {17};
        MyAVLTree<Integer> tree = new MyAVLTree<>(is);
        tree.insert(20);
        tree.insert(14);
        tree.insert(25);
        tree.insert(30);
        tree.insert(21);
        tree.show();
    }

    private Node<E> buildTree(E[] values, int start, int end) {
        Node<E> node = null;
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
            for (int i = 0; i < level; ++i) {
                System.out.print("        ");
            }
            System.out.println(root.key + " (" + root.balance + ")");
            show(root.left, level + 1);
        }
    }

    public boolean remove(E key) {
        SearchResult<E> result = find(key);
        if (result.node == null) {
            return false;
        }
        if (result.node.hasNoChild()) {
            if (result.isLeftNode) {
                result.parent.left = null;
            } else {
                result.parent.right = null;
            }
        } else if (result.node.hasOneChild()) {
            if (result.isLeftNode) {
                result.parent.left = result.node.hasLeftChild() ? result.node.left : result.node.right;
            } else {
                result.parent.right = result.node.hasLeftChild() ? result.node.left : result.node.right;
            }
        } else {
            Node<E> node = result.node.left;
            while (node.hasRightChild()) {
                node = node.right;
            }
            remove(node.key);
            result.node.key = node.key;
        }
        return false;
    }

    public void insert(E key) {
        SearchResult<E> searchResult = find(key);
        if (searchResult.node == null) {
            Node<E> node = new Node<>(key);
            node.parent = searchResult.parent;

            if (searchResult.isLeftNode) {
                searchResult.parent.left = node;
            } else {
                searchResult.parent.right = node;
            }
            if (searchResult.parent.hasOneChild()) {
                updateIn(node);
            } else {
                searchResult.parent.balance = 0;
            }
        }

    }

    private void updateIn(Node<E> node) {
        Node<E> c = node;
        while (c.parent != null) {
            if (c.parent.right == c) {
                c.parent.balance++;
            } else {
                c.parent.balance--;
            }

            if (c.parent.balance > 1) {
                rotateLeft(c.parent);
            }
            c = c.parent;
        }
    }

    private void rotateLeft(Node<E> node) {
        System.out.println("One triggery boy: " + node.key);
        if (node.right.balance < 0) {
            rotateRight(node.right);
            return;
        }
        node.right.parent = node.parent;
        node.parent.right = node.right;
        node.parent = node.right;
        node.right = node.right.left;
        node.parent.left = node;
        node.balance = 0;
        node.parent.balance--;
        updateIn(node);
    }

    private void rotateRight(Node<E> node) {
        node.left.left = node.left.right;
        node.left.parent = node.parent;
        node.parent.right = node.left;
        node.parent = node.left;
        node.left.right = node;
    }

    public boolean contains(E key) {
        return find(key).node != null;
    }

    private SearchResult<E> find(E key) {
        SearchResult<E> result = new SearchResult<>(rootNode, null, false);
        while (result.node != null) {
            if (result.node.key == key) {
                return result;
            }
            result.parent = result.node;
            if (key.compareTo(result.node.key) > 0) {
                result.node = result.node.right;
                result.isLeftNode = false;
            } else {
                result.node = result.node.left;
                result.isLeftNode = true;
            }
        }
        return result;
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
        int balance;
        E key;
        Node<E> parent;
        Node<E> left;
        Node<E> right;

        Node(E key) {
            this.key = key;
        }

        private boolean hasNoChild() {
            return !hasLeftChild() && !hasRightChild();
        }

        private boolean hasOneChild() {
            return hasLeftChild() ^ hasRightChild();
        }

        private boolean hasRightChild() {
            return right != null;
        }

        private boolean hasLeftChild() {
            return left != null;
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
