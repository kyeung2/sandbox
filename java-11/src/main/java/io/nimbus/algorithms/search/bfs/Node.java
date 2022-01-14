package io.nimbus.algorithms.search.bfs;

import java.util.HashSet;
import java.util.Set;

public class Node<T> {
    T value;
    Set<Node<T>> neighbors;

    public Node(T value) {
        this.value = value;
        this.neighbors = new HashSet<>();
    }

    public void connect(Node<T> other) {
        if (this == other) throw new IllegalArgumentException("Can't connect node to itself");
        neighbors.add(other);
        other.neighbors.add(this);
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}