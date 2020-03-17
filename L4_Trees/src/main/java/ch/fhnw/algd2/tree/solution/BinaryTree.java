package ch.fhnw.algd2.tree.solution;

//******************************************************************************
//  FHNW.ALGD2  -  Excercise 4 : Binary Search Trees                           *
// --------------------------------------------------------------------------  *
//  version 2                                                             vtg  *
//******************************************************************************
public class BinaryTree {

    // ***** API *****************************************************************

    // ***** attributes, constants & nested classes ******************************
    private Node m_root = new Node(Integer.MIN_VALUE);

    public BinaryTree() {
        m_root.R = null;
    }

    public BinaryTree(int[] sorted) {
        m_root.R = buildTree(sorted, 0, sorted.length - 1);
    }

    public void show() {
        System.out.println();
        traverse(m_root.R, 0);
    }

    public boolean exists(int key) {
        Node r = m_root.R;
        while (r != null) {
            if (r.key == key)
                return true;
            r = key > r.key ? r.R : r.L;
        }
        return false;
    }

    public boolean insert(int key) {
        SearchResult r = find(key);
        if (r.node != null)          // key exists already
            return false;
        if (r.isLeftChild)
            r.parent.L = new Node(key);
        else
            r.parent.R = new Node(key);
        return true;
    }


    // ***** auxiliaries *********************************************************

    public boolean remove(int key) {
        SearchResult res = find(key);
        if (res.node == null)                                  // nonexistent node
            return false;
        if (res.node.L == null && res.node.R == null) {       // no sons
            if (res.isLeftChild)
                res.parent.L = null;
            else
                res.parent.R = null;
        } else if (res.node.L == null ^ res.node.R == null) {  // only one son
            if (res.isLeftChild)
                res.parent.L = (res.node.L != null ? res.node.L : res.node.R);
            else
                res.parent.R = (res.node.L != null ? res.node.L : res.node.R);
        } else {                                                 // two sons
            Node r = res.node.L;   // search substitute
            while (r.R != null)
                r = r.R;
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
            ret.L = buildTree(a, start, M - 1);
            ret.R = buildTree(a, M + 1, end);
        }
        return ret;
    }

    private void traverse(Node root, int level) {
        if (root != null) {
            traverse(root.R, level + 1);
            for (int i = 0; i < level; ++i)
                System.out.print("    ");
            System.out.println(root.key);
            traverse(root.L, level + 1);
        }
    }

    private SearchResult find(int key) {
        SearchResult res = new SearchResult(m_root, m_root.R, false);
        while (res.node != null) {
            if (res.node.key == key)
                return res;
            res.parent = res.node;
            if (key > res.node.key) {
                res.node = res.node.R;
                res.isLeftChild = false;
            } else {
                res.node = res.node.L;
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
        Node L;
        Node R;

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
