package findKPairsWithSmallestSums_373;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        int r = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        while (r < n2 && r < k) {
            q.add(new int[]{0, r, nums1[0] + nums2[r]});
            r++;
        }

        List<List<Integer>> ans = new ArrayList<>();
        int count = 0;
        while (!q.isEmpty() && count < k) {
            int[] cur = q.poll();
            ans.add(Arrays.asList(nums1[cur[0]], nums2[cur[1]]));

            if (cur[0] + 1 < n1) q.offer(new int[]{cur[0] + 1, cur[1], nums1[cur[0] + 1] + nums2[cur[1]]});
            count++;
        }

        return ans;
    }
}
