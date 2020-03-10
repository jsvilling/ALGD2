public class Traverse {
    private static class Node {
        int data;
        Node left;
        Node right;
    }

    public static void main(String[] args) {
        Node ll = new Node();
        ll.data = 1;

        Node lr = new Node();
        lr.data = 3;

        Node l = new Node();
        l.data = 2;
        l.right = lr;
        l.left = ll;

        Node rl = new Node();
        rl.data = 5;

        Node rr = new Node();
        rr.data = 7;

        Node r = new Node();
        r.data = 6;
        r.left = rl;
        r.right = rr;

        Node root = new Node();
        root.data = 4;
        root.left = l;
        root.right = r;

        traverseLevelOrder(root);


    }

    static void traverseLevelOrder(Node root) {
        if (root != null) {
            System.out.println(root.data);
            traverseLevelOrder(root.left, root.right);
        }
    }

    static void traverseLevelOrder(Node... nodes) {
        for (Node n : nodes) {
            traverseLevelOrder(n);
        }
    }

    static void traverse(Node root) {
        if (root.left != null) {
            traverse(root.left);
        }
        System.out.println(root.data);
        if (root.right != null) {
            traverse(root.right);
        }
    }
}
