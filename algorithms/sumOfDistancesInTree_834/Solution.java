package sumOfDistancesInTree_834;

import java.util.ArrayList;
import java.util.List;

class Solution {
    int totalCount = 0;

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int[] distances = new int[n];
        int[] count = new int[n];

        postDFS(graph, distances, count, 0, -1);
        totalCount = count[0];
        preDFS(graph, distances, count, 0, -1);

        return distances;
    }

    private void postDFS(List<List<Integer>> graph, int[] distances, int[] count, int cur, int parent) {
        count[cur] = 1;
        List<Integer> children = graph.get(cur);
        for (int child: children) {
            if (child == parent) continue;
            postDFS(graph, distances, count, child, cur);
            count[cur] += count[child];
            distances[cur] += count[child] + distances[child];
        }
    }

    private void preDFS(List<List<Integer>> graph, int[] distances, int[] count, int cur, int parent) {
        distances[cur] = (parent == -1) ? distances[cur] : (distances[parent] - count[cur]) + (totalCount - count[cur]);
        List<Integer> children = graph.get(cur);
        for (int child: children) {
            if (child == parent) continue;
            preDFS(graph, distances, count, child, cur);
        }
    }
}
