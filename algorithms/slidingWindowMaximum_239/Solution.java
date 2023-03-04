package slidingWindowMaximum_239;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // use a monotonic queue
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
                q.pollLast();
            }
            q.addLast(i);

            if (q.peekFirst() == i - k) {
                q.pollFirst();
            }

            if (i >= k - 1) {
                res[i - (k - 1)] = nums[q.peekFirst()];
            }
        }
        return res;
    }
}
