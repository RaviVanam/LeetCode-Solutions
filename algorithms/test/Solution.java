package test;

// nums = [3,2,1,4,5], k = 4

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int longestSquareStreak(int[] nums) {
        Map<Long, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put((long)nums[i], i);
        }

        int max = 0;
        int[] dp = new int[nums.length];
        boolean[] visited = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            long cur = (long)nums[i];
            if (visited[i]) continue;
            visited[i] = true;
            while (map.containsKey(cur * cur)) {
                int index = map.get(cur * cur);
                if (dp[index] > 0) {
                    dp[i] += 1 + dp[index];
                    break;
                }
                visited[index] = true;
                dp[i]++;
                cur = cur * cur;
            }

            max = Math.max(max, dp[i]);
        }

        return (max == 0) ? -1 : max + 1;
    }
}

class Allocator {

    int[] array;

    public Allocator(int n) {
        this.array = new int[n];
    }

    public int allocate(int size, int mID) {
        int count = 0;
        for (int j = 0; j < array.length; j++) {
            if (array[j] == 0) {
                count++;
            } else {
                count = 0;
            }

            if (count == size) {
                for (int i = j - count + 1; i <= j; i++) {
                    array[i] = mID;
                }
                return j - count + 1;
            }
        }

        return -1;
    }

    public int free(int mID) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == mID) {
                array[i] = 0;
                count++;
            }
        }
        return count;
    }
}
