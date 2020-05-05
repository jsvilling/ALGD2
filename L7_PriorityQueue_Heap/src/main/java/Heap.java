import java.util.ArrayDeque;
import java.util.Queue;

public class Heap {


    public Heap(int... priorities) {
        root = buildTree(priorities, 0, priorities.length - 1);
        balanceNodesDown();
        size = priorities.length;
    }

    private int size = 0;
    private Node root = null;

    public Heap() {
    }

    public static void main(String[] args) {
        Heap heap = new Heap(1, 2, 3);
        heap.show();
    }

    private Node buildTree(int[] values, int start, int end) {
        Node node = null;
        if (start <= end) {
            int m = (start + end) / 2;
            node = new Node();
            node.priority = values[m];
            node.left = buildTree(values, start, m - 1);
            node.right = buildTree(values, m + 1, end);
        }
        return node;
    }

    private void balanceNodesDown() {
        int m = size / 2;
        while (m > 0) {
            Node node = find(m);
            sickleDown(node);
            m /= 2;
        }
    }

    private void sickleDown(Node node) {
        if (node.hasLargerChild()) {
            Node largerChild = node.getLargerChild();
            int temp = largerChild.priority;
            largerChild.priority = node.priority;
            node.priority = temp;
            sickleDown(largerChild);
        }
    }

    public int get() {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        return root.priority;
    }

    private Node find(int i) {
        Queue<Node> q = new ArrayDeque<>();
        Node current = null;
        q.add(root);
        int c = 1;
        while (!q.isEmpty() && c <= i) {
            current = q.poll();
            if (current.left != null) {
                q.add(current.left);
                c++;
            }
            if (c <= i && current.right != null) {
                q.add(current.right);
                c++;
            }
        }
        return current;
    }


    private void traverseLevelOrder(Node... nodes) {
        for (Node n : nodes) {
            traverseLevelOrder(n);
        }
    }

    public int size() {
        return size;
    }

    public int remove() {
        return 0;
    }

    public void insert(int priority) {

    }

    public void show() {
        show(root, 0);
    }

    private void show(Node root, int level) {
        if (root != null) {
            show(root.right, level + 1);
            for (int i = 0; i < level; ++i) {
                System.out.print("    ");
            }
            System.out.println(root.priority);
            show(root.left, level + 1);
        }
    }


    private class Node {
        int priority;
        Node parent, left, right;

        private Node() {
        }

        private Node(int priority, Node parent, Node left, Node right) {
            this.priority = priority;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        boolean hasLargerChild() {
            return (left != null && left.priority > priority) || (right != null && right.priority > priority);
        }

        Node getLargerChild() {
            if (right == null) {
                return left;
            }
            if (left == null) {
                return right;
            }
            return left.priority > right.priority ? left : right;
        }


    }
}
