package io.nimbus.algorithms.search.bfs;

import java.util.*;

/**
 * https://www.baeldung.com/java-breadth-first-search
 */
public class BreadthFirstSearchGraphs {

    public static <T> Optional<Node<T>> search(T value, Node<T> start) {
        Set<Node<T>> alreadyVisited = new HashSet<>();

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(start);

        Node<T> currentNode;

        while (!queue.isEmpty()) {
            currentNode = queue.remove();
            if (alreadyVisited.add(currentNode)) {
                System.out.println("visited node with value: " + currentNode.value);

                if (currentNode.value.equals(value)) {
                    return Optional.of(currentNode);
                } else {
                    queue.addAll(currentNode.neighbors);
                    queue.removeAll(alreadyVisited);
                }
            }
        }

        return Optional.empty();
    }

    public static void main(String[] args) {
        Node<Integer> start = new Node<>(10);
        Node<Integer> firstNeighbor = new Node<>(2);
        start.connect(firstNeighbor);

        Node<Integer> firstNeighborNeighbor = new Node<>(3);
        firstNeighbor.connect(firstNeighborNeighbor);
        firstNeighborNeighbor.connect(start);

        Node<Integer> secondNeighbor = new Node<>(4);
        start.connect(secondNeighbor);

        System.out.println("found: " + BreadthFirstSearchGraphs.search(4, firstNeighbor));
    }
}
