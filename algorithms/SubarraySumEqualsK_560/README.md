# Intuition
<!-- Describe your first thoughts on how to solve this problem. -->
![560. Subarray Sum Equals K.png](https://assets.leetcode.com/users/images/42532ac1-da02-4107-8009-1a0077d70dd3_1668621404.4430354.png)


# Approach
<!-- Describe your approach to solving the problem. -->
1. Create map `sumOccurrences` with key = sum and value = number of times the sum has occurred
2. Iterate through the array, at each iteration, keep track of the `sum` = sum of array until current index. update `ans = ans + sumOccurrences.get(sum - k)` update the map with current `sum`

# Complexity
- Time complexity: $$O(n)$$
<!-- Add your time complexity here, e.g. $$O(n)$$ -->

- Space complexity:$$O(n)$$
<!-- Add your space complexity here, e.g. $$O(n)$$ -->

# Code
```
class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> sumOccurrences = new HashMap<>();
        int ans = 0;
        int sum = 0;
        
        sumOccurrences.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sumOccurrences.containsKey(sum - k)) {
                ans += sumOccurrences.get(sum - k);
            }
            sumOccurrences.put(sum, 1 + sumOccurrences.getOrDefault(sum, 0));
        }

        return ans;
    }
}           
```