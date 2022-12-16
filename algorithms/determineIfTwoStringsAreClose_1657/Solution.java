package determineIfTwoStringsAreClose_1657;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Solution {
    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) return false;

        int[] f1 = new int[26];
        int[] f2 = new int[26];

        boolean[] c1 = new boolean[26];
        boolean[] c2 = new boolean[26];

        for (char c: word1.toCharArray()) {
            f1[c - 'a']++;
            c1[c - 'a'] = true;
        }

        for (char c: word2.toCharArray()) {
            f2[c - 'a']++;
            c2[c - 'a'] = true;
        }

        for (int i = 0; i < 26; i++) if (c1[i] != c2[i]) return false;


        Map<Integer, Integer> freqOfFreq1 = new HashMap<>();
        Map<Integer, Integer> freqOfFreq2 = new HashMap<>();

        for (int i = 0; i < 26; i++) {
            if (f1[i] > 0) freqOfFreq1.put(f1[i], 1 + freqOfFreq1.getOrDefault(f1[i], 0));
            if (f2[i] > 0) freqOfFreq2.put(f2[i], 1 + freqOfFreq2.getOrDefault(f2[i], 0));
        }

        for (Map.Entry<Integer, Integer> entry: freqOfFreq1.entrySet()) {
            if (!Objects.equals(freqOfFreq2.get(entry.getKey()), entry.getValue())) return false;
        }

        return true;
    }
}