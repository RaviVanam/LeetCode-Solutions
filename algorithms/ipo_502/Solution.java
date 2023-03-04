package ipo_502;

import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int[][] projects = new int[profits.length][2];

        Queue<int[]> profitq = new PriorityQueue<>((a, b) -> -Integer.compare(a[0], b[0]));
        Queue<int[]> capitalq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        for (int i = 0; i < profits.length; i++) {
            projects[i][0] = profits[i];
            projects[i][1] = capital[i];
            capitalq.add(projects[i]);
        }

        while ( k > 0 ) {
            while (!capitalq.isEmpty() && capitalq.peek()[1] <= w) {
                profitq.add(capitalq.poll());
            }

            if (profitq.isEmpty()) return w;
            int[] curProject = profitq.poll();
            w += curProject[0];
            k--;
        }

        return w;
    }
}
