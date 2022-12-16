package sortCharactersByFrequency_451;

import java.util.Arrays;

class Solution {
    public String frequencySort(String s) {
        int[][] charFreq = new int[128][2];

        for (int i = 0; i < 128; i++) charFreq[i][0] = i;

        for (char c: s.toCharArray()) {
            charFreq[c][1]++;
        }

        // descending
        Arrays.sort(charFreq, (a, b) -> -Integer.compare(a[1], b[1]));

        char[] result = new char[s.length()];
        int k = 0;
        for (int i = 0; i < 128; i++) {
            char character = (char)charFreq[i][0];
            int freq = charFreq[i][1];
                System.out.println(freq);

            while (freq != 0) {
                result[k] = character;
                k++;
                freq--;
            }
        }

        return new String(result);
    }
}