package io.nimbus.algorithms.search.dfs;

import java.util.Stack;

/**
 * https://github.com/eugenp/tutorials/tree/master/algorithms-searching/src/main/java/com/baeldung/algorithms/dfs
 */
public class DepthFirstSearchTrees {

    private void visit(int value) {
        System.out.print(" " + value);
    }

    public void traversePreOrder(Node node) {
        if (node != null) {
            visit(node.value);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }
    public void traverseInOrder(Node node) {
        if (node != null) {
            // LEFT deep in the call stack before the first visit
            traverseInOrder(node.left);
            visit(node.value);
            // RIGHT will push one onto the call stack, then again go deep on its left branch.
            traverseInOrder(node.right);
        }
    }

    public void traversePostOrder(Node node) {
        if (node != null) {
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            visit(node.value);
        }
    }

    public void traversePreOrderWithoutRecursion(Node root) {
        Stack<Node> stack = new Stack<>();
        Node current = root;
        stack.push(root);
        while (!stack.isEmpty()) {
            current = stack.pop();

            // VISIT
            visit(current.value);

            // RIGHT in stack first so will be visited second.
            if (current.right != null) {
                stack.push(current.right);
            }
            // LEFT in stack second so top of stack so visited first.
            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }


    public void traverseInOrderWithoutRecursion(Node root) {
        Stack<Node> stack = new Stack<>();
        Node current = root;
        stack.push(root);

        while (!stack.isEmpty()) {

            // LEFT go deep on the left side before visit.
            while (current.left != null) {
                current = current.left;
                stack.push(current);
            }
            current = stack.pop();

            visit(current.value);

            // then do a right and deep left again when the while look is inspected again.
            if (current.right != null) {
                current = current.right;
                stack.push(current);
            }
        }
    }


    public void traversePostOrderWithoutRecursion(Node root) {
        Stack<Node> stack = new Stack<>();
        Node prev = root;
        Node current = root;
        stack.push(root);

        while (!stack.isEmpty()) {
            current = stack.peek();
            boolean hasChild = (current.left != null || current.right != null);
            boolean isPrevLastChild = (prev == current.right ||
                    (prev == current.left && current.right == null));

            if (!hasChild || isPrevLastChild) {
                current = stack.pop();
                visit(current.value);
                prev = current;
            } else {
                if (current.right != null) {
                    stack.push(current.right);
                }
                if (current.left != null) {
                    stack.push(current.left);
                }
            }
        }
    }
}
