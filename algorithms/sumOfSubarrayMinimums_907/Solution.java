// Todo: Write Solution for this
package sumOfSubarrayMinimums_907;

import java.util.Stack;

class Solution {
    public int sumSubarrayMins(int[] arr) {
        Stack<Integer> stk = new Stack<>();
        stk.push(-1);
        long ans = 0;

        // r = right less index, l = left less index
        for (int r = 0; r <= arr.length; r++) {
            int cur = (r < arr.length) ? arr[r] : Integer.MIN_VALUE;

            while (stk.peek() != -1 && arr[stk.peek()] > cur) {
                int index = stk.pop();
                int val = arr[index];
                int l = stk.peek();
                ans += (((long) (r - index) * (index - l)) % (1e9 + 7)) * val;
                ans %= 1e9+7;
            }

            stk.push(r);
        }

        return (int)ans;
    }
}
