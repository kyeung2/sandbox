package io.nimbus.algorithms.search.bfs;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

/**
 * https://www.baeldung.com/java-breadth-first-search
 */
public class BreadthFirstSearchTrees {

    public static <T> Optional<Tree<T>> search(T value, Tree<T> root) {
        Queue<Tree<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Tree<T> currentNode = queue.remove();
            System.out.println("visited node with value: " + currentNode.value);

            if (currentNode.value.equals(value)) {
                return Optional.of(currentNode);
            } else {
                queue.addAll(currentNode.children);
            }
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        Tree<Integer> root = Tree.of(10);
        root.addChild(2).addChild(3);
        root.addChild(4);

        System.out.println("found: " + BreadthFirstSearchTrees.search(4, root));

    }
}
