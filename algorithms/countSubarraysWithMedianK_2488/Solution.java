// todo: write solution for this
package countSubarraysWithMedianK_2488;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int countSubarrays(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;

        int indexK = 0;
        while (nums[indexK] != k) indexK++;

        Map<Integer, Integer> freqBeforeK = new HashMap<>();
        int diff = 0;

        freqBeforeK.put(0, 1); // for element k
        for (int i = indexK - 1; i >= 0; i--) {
            diff += (nums[i] > k) ? 1 : -1;
            freqBeforeK.put(diff, 1 + freqBeforeK.getOrDefault(diff, 0));
        }

        // adiff + bdiff == 0 || 1   => bdiff == -adiff || 1 - adiff
        diff = 0;
        ans += freqBeforeK.get(0) + freqBeforeK.getOrDefault(1, 0);
        for (int i = indexK + 1; i < n; i++) {
            diff += (nums[i] > k) ? 1 : -1;
            ans += freqBeforeK.getOrDefault(-diff, 0);
            ans += freqBeforeK.getOrDefault(1 - diff, 0);
        }

        return ans;
    }
}
