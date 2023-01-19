package differenceBetweenMaximumAndMinimumPriceSum_3538;

import java.util.ArrayList;
import java.util.List;

class Solution {
    int ans = 0;

    public long maxOutput(int n, int[][] edges, int[] price) {
        ans = 0;
        int[] parent_contribution = new int[n];
        parent_contribution[0] = 0;

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] edge: edges) {
            int a = edge[0];
            int b = edge[1];

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        int[] depths = new int[n];
        dfs0(graph, 0, -1, depths, price);
        dfs1(graph, parent_contribution, 0, -1, price, depths);

        return ans;
    }

    private void dfs1(List<List<Integer>> graph, int[] parent_contribution, int cur, int parent, int[] price, int[] depths) {
        List<Integer> children = graph.get(cur);

        int max1 = 0;
        int max2 = 0;
        int maxChild = -1;

        for (int child: children) {
            if (child == parent) continue;
            int depth = depths[child];
            if (depth >= max1) {
                max2 = max1;
                max1 = depth;
                maxChild = child;
            } else if (depth >= max2) {
                max2 = depth;
            }
        }

        for (int child: children) {
            if (child == parent) continue;
            if (child == maxChild) parent_contribution[child] = Math.max(max2, parent_contribution[cur]) + price[cur];
            else parent_contribution[child] = Math.max(max1, parent_contribution[cur]) + price[cur];

            dfs1(graph, parent_contribution, child, cur, price, depths);
        }

        ans = Math.max(ans, Math.max(parent_contribution[cur], max1));
    }

    private int dfs0(List<List<Integer>> graph, int cur, int parent, int[] depths, int[] price) {
        List<Integer> children = graph.get(cur);

        int max = 0;
        for (int child: children) {
            if (child == parent) continue;
            int depth = dfs0(graph, child, cur, depths, price);
            if (depth >= max) max = depth;
        }

        depths[cur] = price[cur] + max;
        return depths[cur];
    }
}
