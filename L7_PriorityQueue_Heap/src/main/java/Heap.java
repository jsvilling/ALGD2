public class Heap {

    private int size = 0;
    private Node root = null;

    public Heap() {
    }

    public Heap(int[] priorities) {
        setupBinaryTree(priorities);
        size = priorities.length;
    }

    private void setupBinaryTree(int[] priorities) {
        this.root = buildNode(new Node(), 0, priorities)
    }

    private Node buildNode(Node root, int i, int[] priorities) {
        if (i >= priorities.length || root == null) {
            return null;
        }
        Node node = new Node();
        node.parent = root;
        node.left = buildNode(node, ++i, priorities);
        node.right = buildNode(node, ++i, priorities);
        return node;
    }

    private void balanceNodes() {
        int m = size / 2;

        while (m > 0) {
            Node node = find(m);
            sickleDown(node);
            m /= 2;
        }
    }

    private Node find(int i) {
        return root;
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
