import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.Consumer;

import static java.util.Arrays.sort;

public class BinarySearchTree<E extends Comparable<E>> {

    private Node<E> rootNode;

    public BinarySearchTree(E[] values) {
        sort(values);
        rootNode = buildTree(values, 0, values.length - 1);
    }

    public static void main(String[] args) {
        Integer[] is = {1, 2, 3, 4, 5, 6, 7};
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(is);

        System.out.println("Tree: ");
        tree.show();

        System.out.println();
        System.out.println("Removed 2: ");
        tree.remove(2);
        tree.show();

        System.out.println();
        System.out.println("Added 0: ");
        tree.insert(0);
        tree.show();
    }

    private Node<E> buildTree(E[] values, int start, int end) {
        if (start <= end) {
            int m = (start + end) / 2;
            Node<E> node = new Node(values[m]);
            node.left = buildTree(values, start, m - 1);
            node.right = buildTree(values, m + 1, end);
        }
        return null;
    }

    public void show() {
        show(rootNode, 0);
    }

    private void show(Node root, int level) {
        if (root != null) {
            show(root.right, level + 1);
            for (int i = 0; i < level; ++i) {
                System.out.print("    ");
            }
            System.out.println(root.key);
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
        SearchResult<E> result = new SearchResult<>(rootNode, rootNode.right, false);
        while (result.node != null) {
            if (result.node.key == key)
                return result;
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
        E key;
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
