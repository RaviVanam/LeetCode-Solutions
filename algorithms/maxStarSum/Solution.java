package maxStarSum;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    public int maxStarSum(int[] vals, int[][] edges, int k) {
        List<Queue<Integer>> graph = new ArrayList<>();
        int n = vals.length;
        for (int i = 0; i < n; i++) {
            graph.add(new PriorityQueue<>((a,b) -> -Integer.compare(a,b)));
        }

        for (int[] edge: edges) {
            graph.get(edge[0]).offer(vals[edge[1]]);
            graph.get(edge[1]).offer(vals[edge[0]]);
        }

        int max = vals[0];
        for (int i = 0; i < n; i++) {
            Queue<Integer> cur = graph.get(i);
            int sum = vals[i];
            int count = 0;
            while (count < k && !cur.isEmpty() && cur.peek() > 0) {
                sum += cur.poll();
                count++;
            }

            max = Math.max(max, sum);
        }

        return max;
    }
}