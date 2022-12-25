package maximumNumberOfPointsFromGridQueries;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int similarPairs(String[] words) {
        int count = 0;
        Map<String, Integer> map = new HashMap<>();

        boolean[] letters = new boolean[26];
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            Arrays.fill(letters, false);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < word.length(); j++) {
                letters[word.charAt(j) - 'a'] = true;
            }
            for (int l = 0; l < 26; l++) {
                if (letters[l]) sb.append((char)('a' + l));
            }

            String temp = sb.toString();
            count += map.getOrDefault(temp, 0);
            map.put(temp, 1 + map.getOrDefault(temp, 0));
        }

        return count;
    }
}