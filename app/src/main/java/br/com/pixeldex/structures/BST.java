package br.com.pixeldex.structures;

public class BST<T extends Comparable<T>> {

    private class Node {
        T data;
        Node left;
        Node right;

        Node(T data) {
            this.data = data;
        }
    }

    private Node root;

    public void insert(T data) {
        root = insertRec(root, data);
    }

    private Node insertRec(Node root, T data) {
        if (root == null) return new Node(data);
        if (data.compareTo(root.data) < 0) {
            root.left = insertRec(root.left, data);
        } else if (data.compareTo(root.data) > 0) {
            root.right = insertRec(root.right, data);
        }
        return root;
    }

    public T search(T key) {
        return searchRec(root, key);
    }

    private T searchRec(Node root, T key) {
        if (root == null) return null;
        if (key.compareTo(root.data) == 0) return root.data;
        
        if (key.compareTo(root.data) < 0) return searchRec(root.left, key);
        else return searchRec(root.right, key);
    }

    public void remove(T key) {
        root = removeRec(root, key);
    }

    private Node removeRec(Node root, T key) {
        if (root == null) return null;

        if (key.compareTo(root.data) < 0) {
            root.left = removeRec(root.left, key);
        } else if (key.compareTo(root.data) > 0) {
            root.right = removeRec(root.right, key);
        } else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            root.data = minValue(root.right);

            root.right = removeRec(root.right, root.data);
        }
        return root;
    }

    private T minValue(Node root) {
        T minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

    public void inOrder() { inOrderRec(root); System.out.println(); }
    private void inOrderRec(Node root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.println(root.data);
            inOrderRec(root.right);
        }
    }

    public void preOrder() { preOrderRec(root); System.out.println(); }
    private void preOrderRec(Node root) {
        if (root != null) {
            System.out.println(root.data);
            preOrderRec(root.left);
            preOrderRec(root.right);
        }
    }

    public void postOrder() { postOrderRec(root); System.out.println(); }
    private void postOrderRec(Node root) {
        if (root != null) {
            postOrderRec(root.left);
            postOrderRec(root.right);
            System.out.println(root.data);
        }
    }

    public int height() { return heightRec(root); }
    private int heightRec(Node root) {
        if (root == null) return -1;
        return 1 + Math.max(heightRec(root.left), heightRec(root.right));
    }

    public int countNodes() { return countNodesRec(root); }
    private int countNodesRec(Node root) {
        if (root == null) return 0;
        return 1 + countNodesRec(root.left) + countNodesRec(root.right);
    }

    public int countLeaves() { return countLeavesRec(root); }
    private int countLeavesRec(Node root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return countLeavesRec(root.left) + countLeavesRec(root.right);
    }

    public boolean isBST() { return isBSTRec(root, null, null); }
    private boolean isBSTRec(Node root, T min, T max) {
        if (root == null) return true;
        if (min != null && root.data.compareTo(min) <= 0) return false;
        if (max != null && root.data.compareTo(max) >= 0) return false;
        return isBSTRec(root.left, min, root.data) && isBSTRec(root.right, root.data, max);
    }

    public void range(T min, T max) {
        rangeRec(root, min, max);
        System.out.println();
    }
    
    private void rangeRec(Node root, T min, T max) {
        if (root == null) return;

        if (min.compareTo(root.data) < 0) {
            rangeRec(root.left, min, max);
        }

        if (min.compareTo(root.data) <= 0 && max.compareTo(root.data) >= 0) {
            System.out.println(root.data);
        }

        if (max.compareTo(root.data) > 0) {
            rangeRec(root.right, min, max);
        }
    }
}