package longestSubstringWithAtLeastKRepeatingCharacters_395;

import java.util.Arrays;

class Solution {
    public int longestSubstring(String s, int k) {
        int res = 0;
        int[] windowChars = new int[26];

        int totalUniqueChars = 0;
        for (int i = 0; i < s.length(); i++) {
            windowChars[s.charAt(i) - 'a']++;
        }
        for (int freq: windowChars) if (freq > 0) totalUniqueChars++;

        for (int uniqueChars = 1; uniqueChars <= totalUniqueChars; uniqueChars++) {
            Arrays.fill(windowChars, 0);
            int start = 0, end = 0;
            boolean valid = false;
            int curUniqueChars = 0;
            int uniqueCharsWithAtLeastKOccurrences = 0;

            while (end < s.length()) {
                if (curUniqueChars <= uniqueChars) {
                    windowChars[s.charAt(end) - 'a']++;
                    if (windowChars[s.charAt(end) - 'a'] == 1) curUniqueChars++;
                    if (windowChars[s.charAt(end) - 'a'] == k) uniqueCharsWithAtLeastKOccurrences++;
                    end++;
                } else {
                    windowChars[s.charAt(start) - 'a']--;
                    if (windowChars[s.charAt(start) - 'a'] == 0) curUniqueChars--;
                    if (windowChars[s.charAt(start) - 'a'] == k - 1) uniqueCharsWithAtLeastKOccurrences--;
                    start++;
                }

                if (uniqueChars == curUniqueChars && uniqueChars == uniqueCharsWithAtLeastKOccurrences) {
                    res = Math.max(res, end - start);
                }
            }
        }

        return res;
    }
}
