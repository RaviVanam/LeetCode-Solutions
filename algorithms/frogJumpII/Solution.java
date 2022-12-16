package frogJumpII;

class Solution {
    public int maxJump(int[] stones) {
        int max = 0;
        for (int i = 2; i < stones.length; i = i + 2) {
            int diff = stones[i] - stones[i-2];
            max = Math.max(max, diff);
        }

        for (int i = 3; i < stones.length; i = i + 2) {
            int diff = stones[i] - stones[i - 2];
            max = Math.max(max, diff);
        }

        max = Math.max(max, stones[1] - stones[0]);
        max = Math.max(max, stones[stones.length-1] - stones[stones.length - 2]);

        return max;
    }
}
