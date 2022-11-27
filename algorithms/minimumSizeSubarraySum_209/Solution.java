package minimumSizeSubarraySum_209;

class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int sum = 0;
        for (int num: nums) sum += num;
        if (sum < target) return 0;

        int l = 0;
        int r = nums.length - 1;
        while (sum > target && l <= r) {
            if (nums[l] < nums[r]) {
                l++;
                sum -= nums[l];
            } else {
                r--;
                sum -= nums[r];
            }
        }

        return r - l + 2;
    }
}