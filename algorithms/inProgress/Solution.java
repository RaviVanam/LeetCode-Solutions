package inProgress;
// todo: learn rank in union find
class Solution {
    public int minScore(int n, int[][] roads) {
        int[] parent = new int[n+1];
        for (int i = 1; i <= n; i++) parent[i] = i;

        for (int[] road: roads) {
            union(parent, road[0], road[1]);
        }

        int min = Integer.MAX_VALUE;
        int root = find(parent, 1);
        for (int[] road: roads) {
            if (root == find(parent, road[0])) min = Math.min(min, road[2]);
        }

        return min;
    }

    private void union(int[] parent, int u, int v) {
        parent[find(parent, v)] = find(parent, u);
    }

    private int find(int[] parent, int u) {
        int parent_u = parent[u];
        while (parent[parent_u] != parent_u) parent_u = parent[parent_u];
        parent[u] = parent_u;
        return parent[u];
    }
}