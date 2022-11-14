# Intuition
<!-- Describe your first thoughts on how to solve this problem. -->

![1871. Jump Game IV.png](https://assets.leetcode.com/users/images/cda2f5c0-384c-43a5-b752-72ba954e57ec_1668451790.7851596.png)

# Approach
<!-- Describe your approach to solving the problem. -->
> *Next indices* for index `i` are the indices that can be reached from index `i` by doing just one jump

> index `i`'s next indices are:
> 1. index `i - 1`
> 2. index `i + 1`
> 3. all indices that have same value as index `i`
> ** All these next indices have same minimum jumps = `dp[i] + 1` (assuming the next indices haven't been processed already)

> How to find the next indices `i` ?
1.Create a Map `indices`: map each value to list of indices having same value
2.Now the next indices of index `i` are `i+1`, `i-1`, and `indices.get(arr[i])`


1. Create a `dp[]` that stores minimum jumps needed for each index.

3. Start by adding index `0` to the queue with current`jump = 0`

4. Now for each index `i` in the queue do
    * poll `i` from the queue
    * `dp[i] = jump` and
    *  add the `i`'s next indices to the queue
   >**Important for O(n) and not getting TLE**:
   It is possible that `i`'s next indices are already in the queue. So adding it again makes no sense. So use a `visiting` boolean array or set  that tells you if it has been added in the queue already so you can avoid adding duplicates.

5. Do `jump++`

6. Repeat steps 4, 5 until queue is empty
7. Return `dp[n-1]`

# Complexity
- Time complexity: $O(n)$

- Space complexity: $O(n)$
<!-- Add your space complexity here, e.g. $$O(n)$$ -->

# Code
```
class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        boolean[] processed = new boolean[n]; // tells if dp[i] is calculated
        Set<Integer> visiting = new HashSet<>(); // for optimization. tells if arr[i] in queue
        Map<Integer, List<Integer>> indices = new HashMap<>(); // Map value to indices having same value

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
```
