package io.nimbus.algorithms.search.bfs;

import java.util.ArrayList;
import java.util.List;

public class Tree<T> {
     T value;
     List<Tree<T>> children;

    private Tree(T value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public static <T> Tree<T> of(T value) {
        return new Tree<>(value);
    }

    public Tree<T> addChild(T value) {
        Tree<T> newChild = new Tree<>(value);
        children.add(newChild);
        return newChild;
    }


    @Override
    public String toString() {
        return "Tree{" +
                "value=" + value +
                '}';
    }
}