package oneThirtyTwoPattern_456;

import java.util.Stack;

class Solution {
    public boolean find132pattern(int[] nums) {
        // pre compute min array
        int[] min = new int[nums.length];
        min[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min[i] = Math.min(nums[i], min[i-1]);
        }

        // it can be proved induction that the values in stack are in ascending order, smallest at the top.
        // if they were in descending than it means we already should have returned true
        // so they are still ins ascending order;
        Stack<Integer> stk = new Stack<>();
        for (int j = nums.length - 1; j > 0; j--) {
            while (!stk.empty() && stk.peek() <= min[j]) stk.pop();
            if (!stk.empty() && stk.peek() < nums[j]) return true;
            stk.push(nums[j]);
        }

        return false;
    }
}
