package allAncestorsOfANodeInADirectedAcyclicGraph_2192;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        new Solution().getAncestors(8, new int[][]{{0,3},{0,4},{1,3},{2,4},{2,7},{3,5},{3,6},{3,7},{4,6}});
    }

    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        var parents = new boolean[n][n];
        for (int[] edge: edges) {
            parents[edge[1]][edge[0]] = true;
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) ans.add(new ArrayList<>());
        var ancestors = new boolean[n][n];
        var visited = new boolean[n];

        for (int cur = 0; cur < n; cur++) dfs(parents, ancestors, visited, ans, cur);
        return ans;
    }

    private void dfs(boolean[][] parents, boolean[][] ancestors, boolean[] visited, List<List<Integer>> ans, int cur) {
        if (visited[cur]) return;
        for (int p = 0; p < parents.length; p++) {
            if (!parents[cur][p]) continue;
            if (!visited[p]) dfs(parents, ancestors, visited, ans, p);

            for (int j = 0; j < parents.length; j++) {
                if (ancestors[p][j]) ancestors[cur][j] = true;
            }

            ancestors[cur][p] = true;
        }
        System.out.print("");
        for (int i = 0; i < parents.length; i++) {
            if (ancestors[cur][i]) ans.get(cur).add(i);
        }

        visited[cur] = true;
    }
}
