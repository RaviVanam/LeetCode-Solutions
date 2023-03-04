package subarraySumsDivisibleByK_974;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public static void main(String[] args) {
        System.out.println(-6 % 5);
    }

    public int subarraysDivByK(int[] nums, int k) {
        // store the number of subarrays on the left with same remainder when divided by k
        Map<Integer, Integer> remainders = new HashMap<>();
        remainders.put(0, 1);
        int sum = 0;
        int res = 0;
        for (int num: nums)  {
            sum += num;
            int rem = (sum >= 0) ? (sum % k) : ((k + sum % k) % k);
            res += remainders.getOrDefault(rem, 0);
            remainders.put(rem, 1 + remainders.getOrDefault(rem, 0));
        }
        return res;
    }
}
