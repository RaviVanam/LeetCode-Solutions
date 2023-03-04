package nonDecreasingSubsequences_491;

import java.util.*;

class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        solve(res, nums, 0, Integer.MIN_VALUE, new ArrayList<>());
        return res;
    }

    private void solve(List<List<Integer>> res, int[] nums, int index, int prev, List<Integer> cur) {
        // don't return here
        if (cur.size() > 1) res.add(new ArrayList<>(cur));

        Set<Integer> used = new HashSet<>();
        for (int i = index; i < nums.length; i++) {
            if (nums[i] >= prev && !used.contains(nums[i])) {
                used.add(nums[i]);
                cur.add(nums[i]);
                solve(res, nums, i + 1, nums[i], cur);
                cur.remove(cur.size() - 1);
            }
        }
    }
}
