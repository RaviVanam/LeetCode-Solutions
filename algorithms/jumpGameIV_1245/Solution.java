package jumpGameIV_1245;

import java.util.*;

class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        boolean[] processed = new boolean[n]; // tells if dp[i] is calculated
        Set<Integer> visiting = new HashSet<>(); // for optimization. tells if arr[i] in queue
        Map<Integer, List<Integer>> indices = new HashMap<>(); // Map value to indices with that value

        // construct the map
        for (int i = 0; i < n; i++) indices.computeIfAbsent(arr[i], (key) -> new ArrayList<>()).add(i);

        Queue<Integer> positionsToProcess = new ArrayDeque<>();
        positionsToProcess.add(0);

        // do BFS and each BFS level represents no of jumps.
        int jumps = 0;
        while (!positionsToProcess.isEmpty()) {
            int queueLength = positionsToProcess.size();

            for (int i = 0; i < queueLength; i++) {

                int position = positionsToProcess.poll();
                if (processed[position]) continue;
                dp[position] = jumps;
                processed[position] = true;

                // add only those next jump indices that are not already in the queue
                if (position - 1 >= 0 && !visiting.contains(arr[position - 1]))
                    positionsToProcess.add(position - 1);
                if (position + 1 < n && !visiting.contains(arr[position + 1]))
                    positionsToProcess.add(position + 1);
                if (!visiting.contains(arr[position]))
                    positionsToProcess.addAll(indices.get(arr[position]));

                visiting.add(arr[position]);
            }

            jumps++;
        }

        return dp[n-1];
    }
}
