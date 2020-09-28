package io.nimbus.algorithms.greedy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.programiz.com/dsa/ford-fulkerson-algorithm
 */
public class FordFulkerson {

    static final int V = 6;

    // Using BFS as a searching algorithm
    static boolean bfs(int[][] Graph, int s, int t, int[] p) {
        boolean[] visited = new boolean[V];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        p[s] = -1;

        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < V; v++) {
                if (!visited[v] && Graph[u][v] > 0) {
                    queue.add(v);
                    p[v] = u;
                    visited[v] = true;
                }
            }
        }

        return visited[t];
    }

    // Applying fordfulkerson algorithm
    public static int fordFulkerson(int[][] graph, int s, int t) {
        int u, v;
        int[][] Graph = new int[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                Graph[u][v] = graph[u][v];

        int[] p = new int[V];

        int max_flow = 0;

        //  # Updating the residual values of edges
        while (bfs(Graph, s, t, p)) {
            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = p[v]) {
                u = p[v];
                path_flow = Math.min(path_flow, Graph[u][v]);
            }

            for (v = t; v != s; v = p[v]) {
                u = p[v];
                Graph[u][v] -= path_flow;
                Graph[v][u] += path_flow;
            }

            // Adding the path flows
            max_flow += path_flow;
        }

        return max_flow;
    }

    public static void main(String[] args) {
        int[][] graph = new int[][]{{0, 8, 0, 0, 3, 0}, {0, 0, 9, 0, 0, 0}, {0, 0, 0, 0, 7, 2},
                {0, 0, 0, 0, 0, 5}, {0, 0, 7, 4, 0, 0}, {0, 0, 0, 0, 0, 0}};

        System.out.println("Max Flow: " + FordFulkerson.fordFulkerson(graph, 0, 5));

    }
}