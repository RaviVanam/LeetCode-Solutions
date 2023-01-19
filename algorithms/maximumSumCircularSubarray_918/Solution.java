package maximumSumCircularSubarray_918;

class Solution {
    public static void main(String[] args) {
        new Solution().maxSubarraySumCircular(new int[]{1, -2, 3, -2});
    }

    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int sum = 0;
        int max = nums[0];
        int total = 0;

        for (int j = 0; j < n; j++) {
            total += nums[j];
            if (sum >= 0) {
                sum = sum + nums[j];
            } else {
                sum = nums[j];
            }

            max = Math.max(max, sum);
        }

        int min = nums[0];
        sum = 0;
        for (int j = 0; j < n; j++) {
            if (sum <= 0) {
                sum = sum + nums[j];
            } else {
                sum = nums[j];
            }

            min = Math.min(min, sum);
        }

        if (min == total) return max;
        return Math.max(max, total - min);
    }
}
