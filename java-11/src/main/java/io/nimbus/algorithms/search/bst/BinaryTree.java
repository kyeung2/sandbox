package io.nimbus.algorithms.search.bst;

/**
 * https://www.baeldung.com/java-binary-tree
 */
public class BinaryTree {

    Node root = null;



    public void add(int value) {
        root = addRecursive(root, value);
    }

    public boolean containsNode(int value) {
        return containsNodeRecursive(root, value);
    }


    public void delete(int value) {
        root = deleteRecursive(root, value);
    }




    // todo this will actually result in the values in numerical order.
    public void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(" " + node.value);
            traverseInOrder(node.right);
        }
    }

    private BinaryTree createBinaryTree() {
        BinaryTree bt = new BinaryTree();

        bt.add(6);
        bt.add(4);
        bt.add(8);
        bt.add(3);
        bt.add(5);
        bt.add(7);
        bt.add(9);

        return bt;
    }


    /**
     * Insert
     * 1. if the new node's value is lower than the current node's, we go to the left child
     * 2. if the new node's value is greater than the current node's, we go to the right child
     * 3. when the current node is null, we've reached a leaf node and we can insert the new node in that position
     */
    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        } else {
            // value already exists
            return current;
        }

        return current;

    }



    // pretty obvious logic, O(log N) time complexity, cutting problem space in half each iteration
    private boolean containsNodeRecursive(Node current, int value) {
        if (current == null) {
            return false;
        }
        if (value == current.value) {
            return true;
        }
        return value < current.value
                ? containsNodeRecursive(current.left, value)
                : containsNodeRecursive(current.right, value);
    }


    private Node deleteRecursive(Node current, int value) {
        if (current == null) {
            return null;
        }

        if (value == current.value) {

            // Case 1: no children
            if (current.left == null && current.right == null) {
                // this will be the returned recursive value to add to the previous iteration's parent.
                return null;
            }

            // Case 2: only 1 child, i.e. by definition as the Case 1 already tested for 2 null children.
            if (current.right == null) {
                return current.left;
            }

            if (current.left == null) {
                return current.right;
            }


            // Case 3: 2 children
            int smallestValue = findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;

        }
        if (value < current.value) {

            current.left = deleteRecursive(current.left, value);
            return current;
        }
        current.right = deleteRecursive(current.right, value);
        return current;
    }

    private int findSmallestValue(Node root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }
}
