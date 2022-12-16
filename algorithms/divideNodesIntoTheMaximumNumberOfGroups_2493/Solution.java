package divideNodesIntoTheMaximumNumberOfGroups_2493;

import java.util.*;

class Solution {
    public int magnificentSets(int n, int[][] edges) {
        // process graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // find components[][]
        List<List<Integer>> components = findComponents(n, graph);

        // for each component, for each node in component do bsf and find maximum depth while making sure there is no odd loop
        // add maximum depths of all components
        int sumOfMaximumDepths = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n+1];

        for (List<Integer> component : components) {
            int maxDepth = 0;
            for (int node : component) {
                int curDepth = bfs(graph, node, queue, n);
                if (curDepth == -1) return -1;
                maxDepth = Math.max(maxDepth, curDepth);
            }

            sumOfMaximumDepths += maxDepth;
        }

        // return maximum depth sum
        return sumOfMaximumDepths;
    }

    private List<List<Integer>> findComponents(int n, List<List<Integer>> graph) {
        List<List<Integer>> components = new ArrayList<>();
        boolean[] visited = new boolean[n+1];

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) components.add(dfs(graph, visited, new ArrayList<>(), i));
        }

        return components;
    }

    private List<Integer> dfs(List<List<Integer>> graph, boolean[] visited, List<Integer> component, int i) {
        component.add(i);
        visited[i] = true;
        for (int node : graph.get(i)) {
            if (!visited[node]) dfs(graph, visited, component, node);
        }
        return component;
    }

    private int bfs(List<List<Integer>> graph, int i, Deque<Integer> queue, int n) {
        queue.clear();

        int[] group = new int[n+1];
        boolean[] visited = new boolean[n+1];
        queue.offer(i);
        visited[i] = true;
        group[i] = 1;

        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;

            for (int k = 0; k < size; k++) {
                int node = queue.poll();
                for (int child: graph.get(node)) {
                    if (visited[child] && group[child] == depth) return -1;
                    if (!visited[child]) {
                        queue.offer(child);
                        visited[child] = true;
                        group[child] = depth + 1;
                    }
                }
            }

        }

        return depth;
    }
}