package ch.fhnw.algd2.tree.solution;

/**
 * Solution for the BinaryTree exercise in ALGD2
 */
public class BinaryTree {

    // ***** API *****************************************************************

    // ***** attributes, constants & nested classes ******************************
    private Node rootNode = new Node(Integer.MIN_VALUE);

    public BinaryTree() {
        rootNode.right = null;
    }

    public BinaryTree(int[] sorted) {
        rootNode.right = buildTree(sorted, 0, sorted.length - 1);
    }

    public void show() {
        System.out.println();
        traverse(rootNode.right, 0);
    }

    public boolean exists(int key) {
        Node currentNode = rootNode.right;
        while (currentNode != null) {
            if (currentNode.key == key)
                return true;
            currentNode = key > currentNode.key ? currentNode.right : currentNode.left;
        }
        return false;
    }

    public boolean insert(int key) {
        SearchResult r = find(key);
        if (r.node != null)          // key exists already
            return false;
        if (r.isLeftChild)
            r.parent.left = new Node(key);
        else
            r.parent.right = new Node(key);
        return true;
    }


    public boolean remove(int key) {
        SearchResult result = find(key);
        if (result.node == null)                                  // nonexistent node
            return false;
        if (result.node.left == null && result.node.right == null) {       // no sons
            if (result.isLeftChild)
                result.parent.left = null;
            else
                result.parent.right = null;
        } else if (result.node.left == null ^ result.node.right == null) {  // only one son
            if (result.isLeftChild)
                result.parent.left = (result.node.left != null ? result.node.left : result.node.right);
            else
                result.parent.right = (result.node.left != null ? result.node.left : result.node.right);
        } else {                                                 // two sons
            Node r = result.node.left;   // search substitute
            while (r.right != null)
                r = r.right;
            remove(r.key);         // process removal
            result.node.key = r.key;
        }
        return true;
    }


    private Node buildTree(int[] a, int start, int end) {
        Node node = null;
        if (start <= end) {
            int m = (start + end) / 2;
            node = new Node(a[m]);
            node.left = buildTree(a, start, m - 1);
            node.right = buildTree(a, m + 1, end);
        }
        return node;
    }

    private void traverse(Node root, int level) {
        if (root != null) {
            traverse(root.right, level + 1);
            for (int i = 0; i < level; ++i)
                System.out.print("    ");
            System.out.println(root.key);
            traverse(root.left, level + 1);
        }
    }

    private SearchResult find(int key) {
        SearchResult result = new SearchResult(rootNode, rootNode.right, false);
        while (result.node != null) {
            if (result.node.key == key)
                return result;
            result.parent = result.node;
            if (key > result.node.key) {
                result.node = result.node.right;
                result.isLeftChild = false;
            } else {
                result.node = result.node.left;
                result.isLeftChild = true;
            }
        }
        return result;
    }

    private static class Node {
        int key;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
        }
    }

    private static class SearchResult {
        Node node;
        Node parent;
        boolean isLeftChild;

        SearchResult(Node parent, Node result, boolean left) {
            this.node = result;
            this.parent = parent;
            this.isLeftChild = left;
        }
    }

}
