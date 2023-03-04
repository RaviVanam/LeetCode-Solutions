package kThSmallestElementInASortedMatrix_378;

import java.util.PriorityQueue;

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));

        for (int i = 0; i < m; i++) q.add(new int[]{i, 0, matrix[i][0]});

        int ans = matrix[0][0];
        for (int count = 0; count < k; count++) {
            int[] cell = q.poll();
            int i = cell[0], j = cell[1];
            ans = cell[2];
            if (j < n - 1) q.offer(new int[]{i, j + 1, matrix[i][j+1]});
        }
        return ans;
    }
}
