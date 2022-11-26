package sumOfSubarrayRanges_2104;

import java.util.Stack;

class Solution {
    public long subArrayRanges(int[] nums) {
        // basic idea: sum(max(a) - min(a)) = sum(max(a)) - sum(min(a))
        // for simplicity using Stack<>, use ArrayDeque<> you'll get a lot more performance
        Stack<Integer> stk = new Stack<>();
        long ans = 0;

        stk.push(-1);
        for (int r = 0; r <= nums.length; r++) {
            int cur = (r == nums.length) ? Integer.MIN_VALUE : nums[r];

            while (stk.peek() != -1 && nums[stk.peek()] > cur) {
                int index = stk.pop();
                int val = nums[index];
                int l = stk.peek();
                ans -= (long)(r - index) * (index - l) * val;
            }

            stk.push(r);
        }

        stk.clear();

        stk.push(-1);
        for (int r = 0; r <= nums.length; r++) {
            int cur = (r == nums.length) ? Integer.MAX_VALUE : nums[r];

            while (stk.peek() != -1 && nums[stk.peek()] < cur) {
                int index = stk.pop();
                int val = nums[index];
                int l = stk.peek();
                ans += (long)(r - index) * (index - l) * val;
            }

            stk.push(r);
        }

        return ans;
    }
}