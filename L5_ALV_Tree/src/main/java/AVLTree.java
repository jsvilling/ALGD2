////******************************************************************************
////  FHNW.ALGD2  -  Excercise 5 : AVL Trees                                     *
//// --------------------------------------------------------------------------- *
////  vorgegebene Elemente                                                       *
////******************************************************************************
//public class AVLTree {
//
//    private Node rootNode = new Node(Integer.MIN_VALUE);
//
//    public AVLTree() {
//        rootNode.right = null;
//        rootNode.left = null;
//        rootNode.parent = rootNode;
//    }
//
//    public AVLTree(int[] sorted) {
//        this();
//        rootNode.right = buildTree(sorted, 0, sorted.length - 1, rootNode);
//        updateBalances(rootNode.right);
//    }
//
//    public void show() {
//        System.out.println();
//        traverse(rootNode.right, 0);
//    }
//
//    public boolean exists(int key) {
//        Node r = rootNode.right;
//        while (r != null) {
//            if (r.key == key)
//                return true;
//            r = key > r.key ? r.right : r.left;
//        }
//        return false;
//    }
//
//    public boolean insert(int key) {
//        Node node = new Node(key);
//
//
//        if (rootNode.right == null) {
//            rootNode.right = node;
//            node.parent = rootNode;
//        } else {
//            Node currentNode = rootNode.right;
//            if (currentNode.key > key) {
//                if (currentNode.left == null) {
//
//                }
//            }
//
//
//
//        }
//
//
//
//        // TODO : assignment 5.3
//    }
//
//    public boolean remove(int key) {
//        return remove(key, null);
//    }
//
//    public boolean checkBalanceFactors() {
//        return balanceInfoIsCorrect(rootNode.right);
//    }
//
//    private Node buildTree(int[] a, int start, int end, Node parent) {
//        Node ret = null;
//        if (start <= end) {
//            int M = (start + end) / 2;
//            ret = new Node(a[M]);
//            ret.parent = parent;
//            ret.balance = 0;
//            ret.left = buildTree(a, start, M - 1, ret);
//            ret.right = buildTree(a, M + 1, end, ret);
//        }
//        return ret;
//    }
//
//    // used to show a tree semigraphically
//    private void traverse(Node root, int level) {
//        if (root != null) {
//            traverse(root.right, level + 1);
//            for (int i = 0; i < level; ++i)
//                System.out.print("        ");
//            System.out.print("[");
//            System.out.format("%1$03d ", root.key);
//            if (root.balance != 0)
//                System.out.format("%1$+2d", root.balance);
//            else
//                System.out.print("\u00B70");
//            System.out.print("]");
//            System.out.println();
//            traverse(root.left, level + 1);
//        }
//    }
//
//    private SearchResult find(int key) {
//        SearchResult res = new SearchResult(rootNode, rootNode.right, false);
//        while (res.node != null) {
//            if (res.node.key == key)
//                return res;
//            res.parent = res.node;
//            if (key > res.node.key) {
//                res.node = res.node.right;
//                res.isLeftChild = false;
//            } else {
//                res.node = res.node.left;
//                res.isLeftChild = true;
//            }
//        }
//        return res;
//    }
//
//    // this method is used when a tree is generated from a sorted Array
//    private int updateBalances(Node n) {
//        if (n != null) {
//            int left = updateBalances(n.left);
//            int rght = updateBalances(n.right);
//            n.balance = rght - left;
//            return 1 + (left > rght ? left : rght);
//        } else {
//            return 0;
//        }
//    }
//
//    // removes node using optimized search, if starting node is known (pointed by r)
//    private boolean remove(int key, SearchResult r) {
//        // TODO : assignment 5.4
//    }
//
//    // search removal-substitute for node p
//    private SearchResult searchNearestSmaller(Node p) {
//        int searchKey = p.key;
//        Node currentNode = p;
//        boolean isLeft = false;
//
//        while (currentNode != null && currentNode.key >= searchKey) {
//            if (currentNode.right == null) {
//                currentNode = currentNode.left;
//                isLeft = true;
//            } else {
//                currentNode = currentNode.right;
//                isLeft = false;
//            }
//        }
//        return new SearchResult(p.parent, p, isLeft);
//    }
//
//    // trying to raise an empty sub-tree will cause a nullpointer exception
//    private void rotateRight(Node p) {
//        p.parent.right = p.left;
//        p.parent = p.left;
//        p.left = null;
//        // TODO : assignment 5.2
//    }
//
//    // trying to raise an empty sub-tree will cause a nullpointer exception
//    private void rotateLeft(Node p) {
//        Node p0_U = p.parent;
//        Node p1_L = p.right.left;
//        Node p1_R = p.right.right;
//        Node p0 = p;
//        Node p1 = p.right;
//
//        p0.parent = p1;
//        p0.right = null;
//
//        p1.parent = p0_U == p0 ? p1 : p0_U;
//        p1.left = p0;
//        p1_L.parent = p1_R;
//        // TODO : assignment 5.2
//    }
//
//    private void updateIn(Node p) {
//        // TODO : assignment 5.3
//    }
//
//    private void updateOut(Node p) {
//        // TODO : assignment 5.4
//    }
//
//    // compute depth of a tree : for testing issues only
//    private int getDepth(Node root) {
//        if (root == null) {
//            return 0;
//        } else {
//            return 1 + Math.max(getDepth(root.left), getDepth(root.right));
//        }
//    }
//
//
//    //check balance of a certain node : for testing issues only
//    private boolean balanceInfoIsCorrect(Node node) {
//        if (node == null) {
//            return true;
//        } else {
//            return (getDepth(node.right) - getDepth(node.left) == node.balance)
//                    && balanceInfoIsCorrect(node.right) && balanceInfoIsCorrect(node.left);
//        }
//    }
//
//    private static class Node {
//        int key;
//        int balance;
//        Node left;
//        Node right;
//        Node parent;
//
//        Node(int key) {
//            this.key = key;
//        }
//    }
//
//
//    private static class SearchResult {
//        Node node;
//        Node parent;
//        boolean isLeftChild;
//
//        SearchResult(Node parent, Node result, boolean isLeftChild) {
//            this.node = result;
//            this.parent = parent;
//            this.isLeftChild = isLeftChild;
//        }
//    }
//
//}
//
