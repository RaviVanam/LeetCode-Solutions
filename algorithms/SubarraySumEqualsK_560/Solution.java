package SubarraySumEqualsK_560;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> sumOccurrences = new HashMap<>();
        int ans = 0;
        int sum = 0;

        sumOccurrences.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sumOccurrences.containsKey(sum - k)) {
                ans += sumOccurrences.get(sum - k);
            }
            sumOccurrences.put(sum, 1 + sumOccurrences.getOrDefault(sum, 0));
        }

        return ans;
    }
}