package minimumTimeToCollectAllApplesInATree_1443;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        // process graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        buildGraph(graph, edges);

        // dfs on graph from 0 to find ans
        int res = solve(graph, 0, hasApple, -1);
        return res > 0 ? res - 2 : 0;
    }

    private int solve(List<List<Integer>> graph, int cur, List<Boolean> hasApple, int prev) {
        int res = 0;
        for (int child: graph.get(cur)) {
            if (child == prev) continue;
            res += solve(graph, child, hasApple, cur);
        }

        res += (res > 0 || hasApple.get(cur)) ? 2 : 0;
        return res;
    }

    private void buildGraph(List<List<Integer>> graph, int[][] edges) {
        for (int[] edge: edges) {
            int a = edge[0];
            int b = edge[1];

            graph.get(a).add(b);
            graph.get(b).add(a);
        }
    }
}