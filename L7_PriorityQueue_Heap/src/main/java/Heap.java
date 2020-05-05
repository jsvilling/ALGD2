public class Heap {

    private int size = 0;
    private Node root = null;

    public Heap() {
    }

    public Heap(int... priorities) {
        root = buildTree(priorities, 0, priorities.length - 1, null);
        size = priorities.length;
        balanceNodesDown();
    }

    public static void main(String[] args) {
        Heap heap = new Heap(3, 2, 1);
        heap.insert(32);
        heap.insert(33);
        heap.insert(34);
        heap.remove();
        heap.show();
    }

    private Node buildTree(int[] values, int start, int end, Node parent) {
        Node node = null;
        if (start <= end) {
            int m = (start + end) / 2;
            node = new Node();
            node.priority = values[m];
            node.parent = parent;
            node.left = buildTree(values, m + 1, end, node);
            node.right = buildTree(values, start, m - 1, node);
        }
        return node;
    }

    private void balanceNodesDown() {
        int m = size / 2;
        while (m > 0) {
            Node node = find(m);
            sickleDown(node);
            m--;
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

    private void balanceNodesUp() {
        Node node = find(size);
        sickleUp(node);
    }

    private void sickleUp(Node node) {
        if (node != null && node.parent != null && node.priority > node.parent.priority) {
            int temp = node.priority;
            node.priority = node.parent.priority;
            node.parent.priority = temp;
            sickleUp(node.parent);
        }
    }

    /**
     * @return Top element without deleting it
     */
    public int get() {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        return root.priority;
    }

    /**
     * @return Node at position pos
     */
    private Node find(int pos) {
        Node result = root;
        int mask = getMask(pos);
        while (mask > 1) {
            mask >>>= 1;
            if ((pos & mask) == 0) {
                result = result.left;
            } else {
                result = result.right;
            }
        }
        return result;
    }

    private int getMask(int pos) {
        int mask = 0b10000000;
        while (mask > 0) {
            if ((pos & mask) != 0) {
                return mask;
            }
            mask >>>= 1;
        }
        return 0;
    }

    public int size() {
        return size;
    }

    public int remove() {
        int result = root.priority;
        if (size == 1) {
            root = null;
        } else {
            Node latest = find(size);
            root.priority = latest.priority;
            if (latest.parent.left == latest) {
                latest.parent.left = null;
            } else {
                latest.parent.right = null;
            }
        }
        size--;
        balanceNodesDown();
        return result;
    }

    public void insert(int priority) {
        Node newNode = new Node();
        newNode.priority = priority;
        Node parent = find((size + 1) / 2);
        newNode.parent = parent;
        if (parent.left == null) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        size++;
        balanceNodesUp();
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
