package test;

public class Solution {
    public static int countMaximumOperations(String s, String t) {
        int ans = Integer.MAX_VALUE;
        int[] freqS = new int[26];
        int[] freqT = new int[26];

        for (char c: t.toCharArray()) {
            freqT[c - 'a']++;
        }

        for (char c: s.toCharArray()) {
            freqS[c - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (freqT[i] > 0)
                ans = Math.min(ans, freqS[i] / freqT[i]);
        }

        return ans;
    }
}
