public class Heap {

    private int size = 0;
    private Node root = null;

    public int get() {
        return 0;
    }

    public int size() {
        return size;
    }

    public int remove() {
        return 0;
    }

    public void insert(int priority) {

    }

    void show() {

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
    }
}
