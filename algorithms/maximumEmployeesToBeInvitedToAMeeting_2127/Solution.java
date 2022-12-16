// todo: do this using topological sort
package maximumEmployeesToBeInvitedToAMeeting_2127;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int maximumInvitations(int[] favorite) {
        int n = favorite.length;

        // process favorites into a directed graph, where favorite[i] -> i instead of i -> favorite[i]
        List<List<Integer>> graph = buildGraph(favorite);

        // find the maximum loop length
        int maxLoop = 0;
        boolean[] visited = new boolean[n];
        boolean[] inStack = new boolean[n];
        int[] depths = new int[n];

        for (int i = 0; i < n; i++) {
            maxLoop = Math.max(maxLoop, findLargestLoop(graph, visited, inStack, i, depths, 0));
        }

        // find sum of all free components lengths
            // find all pairs that point to each other and for each of those pairs find depth through dfs on both sides

        int sumFreeComponents = 0;
        for (int i = 0; i < n; i++) {
            if (favorite[favorite[i]] == i && i < favorite[i])
                sumFreeComponents += depthOf(graph, favorite, i) + depthOf(graph, favorite, favorite[i]);
        }

        return Math.max(sumFreeComponents, maxLoop);
    }

    private List<List<Integer>> buildGraph(int[] favorite) {
        int n = favorite.length;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            graph.get(favorite[i]).add(i);
        }

        return graph;
    }

    private int findLargestLoop(List<List<Integer>> graph, boolean[] visited, boolean[] inStack, int cur, int[] depths, int curDepth) {
        if (inStack[cur]) return curDepth - depths[cur];
        if (visited[cur]) return Integer.MIN_VALUE;

        int maxLoop = Integer.MIN_VALUE;
        visited[cur] = true;
        inStack[cur] = true;
        depths[cur] = curDepth;
        for (int node: graph.get(cur)) {
            maxLoop = Math.max(maxLoop, findLargestLoop(graph, visited, inStack, node, depths, curDepth + 1));
        }
        inStack[cur] = false;

        return maxLoop;
    }

    private int depthOf(List<List<Integer>> graph, int[] favorite, int cur) {
        if (graph.get(cur).size() == 1) return 1;

        int maxDepth = 0;
        for (int node: graph.get(cur)) {
            if (node != favorite[cur])
                maxDepth = Math.max(maxDepth, dfsDepth(graph, node, 2));
        }

        return maxDepth;
    }

    // optimized for tree graph. that's why doesn't use visited array
    private int dfsDepth(List<List<Integer>> graph, int cur, int depth) {
        if (graph.get(cur).size() == 0) return depth;

        int maxDepth = 0;
        for (int node: graph.get(cur)) {
            maxDepth = Math.max(maxDepth, dfsDepth(graph, node, depth + 1));
        }

        return maxDepth;
    }
}
