package maximumNumberOfPointsFromGridQueries;

import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int[] maxPoints(int[][] grid, int[] queries) {
        // create array queries with indices [query, index]
        // sort queries_with_indices
        // create a priority queue that stores [i, j, val] and priority is based on val
        // for every query in queries:
        //      poll if smaller than query and ++count and do bfs and adjacent [i, j, val]s to queue
        //      do the above until there is no other cell smaller than query or until queue is empty
        //      finally answer[query_index] = count
        // return count

        int[][] directions = new int[][] {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        int count = 0;
        int[] answer = new int[queries.length];

        int[][] queriesWithIndices = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) {
            queriesWithIndices[i][0] = queries[i];
            queriesWithIndices[i][1] = i;
        }

        Arrays.sort(queriesWithIndices, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        queue.add(new int[] {0, 0, grid[0][0]});
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        visited[0][0] = true;

        for (int[] queryWithIndex: queriesWithIndices) {
            int queryVal = queryWithIndex[0];
            int queryIndex = queryWithIndex[1];

            while (!queue.isEmpty() && queue.peek()[2] < queryVal) {
                count++;
                int[] cell = queue.poll();

                int i, j;
                for (int[] direction: directions) {
                    i = cell[0] + direction[0];
                    j = cell[1] + direction[1];
                    if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && !visited[i][j]) {
                        visited[i][j] = true;
                        queue.add(new int[]{i, j, grid[i][j]});
                    }
                }
            }

            answer[queryIndex] = count;
        }

        return answer;
    }
}