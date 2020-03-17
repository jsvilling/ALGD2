package ch.fhnw.algd2.tree.solution;

//******************************************************************************
//  FHNW.ALGD2  -  Excercise 4 : Binary Search Trees                           *
// --------------------------------------------------------------------------  *
//  version 2                                                             vtg  *
//******************************************************************************
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
        Node r = rootNode.right;
        while (r != null) {
            if (r.key == key)
                return true;
            r = key > r.key ? r.right : r.left;
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


    // ***** auxiliaries *********************************************************

    public boolean remove(int key) {
        SearchResult res = find(key);
        if (res.node == null)                                  // nonexistent node
            return false;
        if (res.node.left == null && res.node.right == null) {       // no sons
            if (res.isLeftChild)
                res.parent.left = null;
            else
                res.parent.right = null;
        } else if (res.node.left == null ^ res.node.right == null) {  // only one son
            if (res.isLeftChild)
                res.parent.left = (res.node.left != null ? res.node.left : res.node.right);
            else
                res.parent.right = (res.node.left != null ? res.node.left : res.node.right);
        } else {                                                 // two sons
            Node r = res.node.left;   // search substitute
            while (r.right != null)
                r = r.right;
            remove(r.key);         // process removal
            res.node.key = r.key;
        }
        return true;
    }

    private Node buildTree(int[] a, int start, int end) {
        Node ret = null;
        if (start <= end) {
            int M = (start + end) / 2;
            ret = new Node(a[M]);
            ret.left = buildTree(a, start, M - 1);
            ret.right = buildTree(a, M + 1, end);
        }
        return ret;
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
        SearchResult res = new SearchResult(rootNode, rootNode.right, false);
        while (res.node != null) {
            if (res.node.key == key)
                return res;
            res.parent = res.node;
            if (key > res.node.key) {
                res.node = res.node.right;
                res.isLeftChild = false;
            } else {
                res.node = res.node.left;
                res.isLeftChild = true;
            }
        }
        return res;
    }

    //just for testing issues
    public void testInternalSearch(int key) {
        SearchResult r = find(key);
        System.out.println("node=" + (r.node != null ? r.node.key : "nonexistent"));
        System.out.println("parent=" + (r.parent != null ? r.parent.key : "nonexistent"));
        System.out.println(r.isLeftChild ? "left child" : "right child");
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
