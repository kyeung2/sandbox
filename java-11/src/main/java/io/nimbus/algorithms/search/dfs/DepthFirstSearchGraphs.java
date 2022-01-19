package io.nimbus.algorithms.search.dfs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * https://github.com/eugenp/tutorials/tree/master/algorithms-searching/src/main/java/com/baeldung/algorithms/dfs
 */
public class DepthFirstSearchGraphs {

    private final Map<Integer, List<Integer>> adjVertices;

    public DepthFirstSearchGraphs() {
        this.adjVertices = new HashMap<Integer, List<Integer>>();
    }

    public void dfs(int start) {
        boolean[] isVisited = new boolean[adjVertices.size()];
        dfsRecursive(start, isVisited);
    }

    // this is essentially a pre-order DFS, it cannot be anything else
    private void dfsRecursive(int current, boolean[] isVisited) {
        isVisited[current] = true;
        visit(current);
        for (int dest : adjVertices.get(current)) {
            if (!isVisited[dest])
                dfsRecursive(dest, isVisited);
        }
    }

    public void dfsWithoutRecursion(int start) {
        Stack<Integer> stack = new Stack<Integer>();
        boolean[] isVisited = new boolean[adjVertices.size()];
        stack.push(start);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (!isVisited[current]) {
                isVisited[current] = true;
                visit(current);
                for (int dest : adjVertices.get(current)) {
                    if (!isVisited[dest])
                        stack.push(dest);
                }
            }
        }

    }

    private void visit(int value) {
        System.out.print(" " + value);
    }
}
