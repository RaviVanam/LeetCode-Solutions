package superUglyNumber_313;

import java.util.PriorityQueue;

public class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        int[] ugly = new int[n+1];
        ugly[1] = 1;

        for (int prime : primes) {
            queue.add(new int[]{prime, prime, 1});
        }

        int i = 2;
        while (i <= n) {
            int[] cur = queue.poll();
            int num = cur[0];
            int prime = cur[1];
            int index = cur[2];

            // we are basically merging k sorted lists
            // so there can be duplicates in the final sorted list
            // so don't add duplicates
            if (num != ugly[i-1]) {
                ugly[i] = num;
                i++;
            }

            cur[0] = prime * ugly[index + 1];
            if (prime != cur[0] / ugly[index + 1]) cur[0] = Integer.MAX_VALUE;
            cur[2] = index + 1;
            queue.add(cur);
        }

        return ugly[n];
    }
}