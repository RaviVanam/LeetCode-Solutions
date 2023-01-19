package countTheNumberOfGoodSubarrays_2537;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public long countGood(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int pairs = 0;
        long res = 0;
        for (int i = 0, j = 0; j < nums.length; j++) {
            pairs += countMap.getOrDefault(nums[j], 0);
            countMap.put(nums[j], 1 + countMap.getOrDefault(nums[j], 0));

            while (pairs >= k) {
                countMap.put(nums[i], countMap.get(nums[i]) - 1);
                pairs -= countMap.get(nums[i]);
                i++;
            }

            res += i;
        }

        return res;
    }
}
