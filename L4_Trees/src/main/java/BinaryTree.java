import java.util.Arrays;

/**
 * Binary Tree implementation for ALGD2
 *
 * @author J. Villing
 */
public class BinaryTree<E> {

    private Node<E> rootNode;

    public BinaryTree(E[] values) {
        rootNode = buildTree(values);
    }

    private Node<E> buildTree(E[] values) {
        Arrays.sort(values);
        return buildTree(values, 0, values.length);
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
        E data;
        Node<E> left;
        Node<E> right;
    }
}
