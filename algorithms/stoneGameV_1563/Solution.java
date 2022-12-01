package stoneGameV_1563;

import java.util.Arrays;

class Solution {
    int[] sum;
    int[][] dp;
    public int stoneGameV(int[] stoneValue) {
        dp = new int[stoneValue.length][stoneValue.length];
        for (int[] row: dp) Arrays.fill(row, -1);
        sum = new int[stoneValue.length];
        sum[0] = stoneValue[0];
        for (int i = 1; i < sum.length; i++) sum[i] = sum[i-1] + stoneValue[i];
        return solve(stoneValue, 0, stoneValue.length - 1);
    }

    private int solve(int[] stoneValue, int i, int j) {
        if (i == j) return 0;

        if (dp[i][j] != -1) return dp[i][j];
        int res = Integer.MIN_VALUE;
        for (int m = i; m < j; m++) {
            // i to m
            int left = (i > 0) ? sum[m] - sum[i-1] : sum[m];

            // m+1 to j
            int right = sum[j] - sum[m];
            int cur = 0;

            if (left < right) {
                cur = left + solve(stoneValue, i, m);
            } else if (right < left) {
                cur = right + solve(stoneValue, m + 1, j);
            } else {
                cur = left + Math.max(solve(stoneValue, i, m), solve(stoneValue, m + 1, j));
            }

            res = Math.max(res, cur);
        }

        return dp[i][j] = res;
    }
}