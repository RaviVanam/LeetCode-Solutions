package longestPathWithDifferentAdjacentCharacters_2246;

import java.util.ArrayList;
import java.util.List;

class Solution {
    int res = 1;

    public int longestPath(int[] parent, String s) {
        int n = parent.length;
        if (n == 0) return 0;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < n; i++) {
            if (parent[i] != -1) graph.get(parent[i]).add(i);
        }

        res = 1;
        solve(graph, 0, s);
        return res;
    }

    private int solve(List<List<Integer>> graph, int cur, String s) {
        if (graph.get(cur).size() == 0) return 1;

        int max = 0;
        int max2 = 0;

        char curChar = s.charAt(cur);
        List<Integer> children = graph.get(cur);
        for (int child: children) {
            char childChar = s.charAt(child);
            if (childChar == curChar) solve(graph, child, s);
            else {
                int childDepth = solve(graph, child, s);
                if (childDepth >= max) {
                    max2 = max;
                    max = childDepth;
                } else if (childDepth > max2) {
                    max2 = childDepth;
                }
            }
        }

        res = Math.max(res, 1 + max + max2);
        return max + 1;
    }
}
