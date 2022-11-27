// todo: write solution
package arithmeticSlicesII_Subsequence_446;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        int ans = 0;
        Map<Long, Integer>[] dp = new Map[n];
        // mapping diff to count of subsequences (having at least 2 numbers) for each index
        for (int i = 0; i < n; i++) dp[i] = new HashMap<>();

        for (int j = 1; j < n; j++) {
            for (int i = 0; i < j; i++) {
                long diff = (long)nums[j] - nums[i];
                // no of subsequences ending at i (having at least 2 numbers)
                int cnt = dp[i].getOrDefault(diff, 0);
                // we add +1 here because, subsequence [nums[i], nums[j]] is new valid subsequence (having at least 2 numbers)
                dp[j].put(diff, 1 + cnt + dp[j].getOrDefault(diff, 0));
                // just cnt because, we add number of subsequences ending at index i and having at least 3 numbers
                ans += cnt;
            }
        }

        return ans;
    }
}