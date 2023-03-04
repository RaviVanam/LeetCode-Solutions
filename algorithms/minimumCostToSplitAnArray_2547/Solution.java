package minimumCostToSplitAnArray_2547;

import java.util.Arrays;

class Solution {
    public int minCost(int[] nums, int k) {
        // calculate trimmed lengths in O(N^2)
        // and use dp + recursion to solve, the recursive function is ans[index],
        // it represents ans for subarray [index...n-1]
        // ans[start] = min(k + trimmed[start][end] + ans[end + 1]) ---- for all end in range [start, n-1]
        // return ans[0]

        int[][] trimmed = preCalculateTrimmedLengths(nums);
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return solve(nums, k, dp, trimmed, 0);
    }

    private int[][] preCalculateTrimmedLengths(int[] nums) {
        int[][] trimmed = new int[nums.length][nums.length];
        int[] count = new int[1001];

        for (int i = 0; i < nums.length; i++) {
            Arrays.fill(count, 0);
            int len = 0;
            for (int j = i; j < nums.length; j++) {
                count[nums[j]]++;
                if (count[nums[j]] == 2) len += 2;
                else if (count[nums[j]] > 2) len++;

                trimmed[i][j] = len;
            }
        }

        return trimmed;
    }

    private int solve(int[] nums, int k, int[] dp, int[][] trimmed, int start) {
        if (start == nums.length) return 0;
        if (dp[start] != -1) return dp[start];

        int min = Integer.MAX_VALUE;
        for (int end = start; end < nums.length; end++) {
            int cur = k + trimmed[start][end];
            int nextMin = solve(nums, k, dp, trimmed, end + 1);
            min = Math.min(min, cur + nextMin);
        }

        return dp[start] = min;
    }
}
