public class Heap {

    public Heap() {
    }

    private int size = 0;
    private Node root = null;

    public Heap(int... priorities) {
        root = buildTree(priorities, 0, priorities.length - 1);
        size = priorities.length;
        balanceNodesDown();
    }

    public static void main(String[] args) {
        Heap heap = new Heap(3, 2, 1, 32, 5, 12, 322, 123, 55);
        heap.show();
//        System.out.println("find 1 " + heap.find(1).priority);
//        System.out.println("find 2 " + heap.find(2).priority);
//        System.out.println("find 3 " + heap.find(3).priority);
//        System.out.println("find 4 " + heap.find(4).priority);

        int pos = 8;
        int mask = 0b10000000;
        while (mask > 0) {
            if ((pos & mask) != 0) {
                break;
            }
            mask >>>= 1;
        }

        Node result = heap.root;
        while (mask > 1) {
            mask >>>= 1;


            if ((pos & mask) == 0) {
                result = result.left;
            } else {
                result = result.right;
            }
        }

        System.out.println(result.priority);


    }

    private Node buildTree(int[] values, int start, int end) {
        Node node = null;
        if (start <= end) {
            int m = (start + end) / 2;
            node = new Node();
            node.priority = values[m];
            node.left = buildTree(values, m + 1, end);
            node.right = buildTree(values, start, m - 1);
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

    public int get() {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        return root.priority;
    }

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
