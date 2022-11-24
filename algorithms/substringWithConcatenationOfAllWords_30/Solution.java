// todo: write a solution for this
package substringWithConcatenationOfAllWords_30;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    public List<Integer> findSubstring(String s, String[] words) {
        final Map<String, Integer> wordFreq = new HashMap<>();
        for (String word: words) {
            wordFreq.put(word, 1 + wordFreq.getOrDefault(word, 0));
        }

        final Map<String, Integer> substringWordFreq = new HashMap<>();
        final List<Integer> ans = new ArrayList<>();

        int n = s.length();
        int m = words[0].length();
        int permutationLength = m * words.length;

        for (int start = 0; start < m; start++) {
            int i = start;
            for (int j = start + m - 1; j < n; j = j + m) {
                String curWord = s.substring(j - m + 1, j + 1);

                substringWordFreq.putIfAbsent(curWord, 0);
                substringWordFreq.put(curWord, 1 + substringWordFreq.get(curWord));

                if (!wordFreq.containsKey(curWord)) {
                    while (i < j) {
                        String removeWord = s.substring(i, i + m);
                        substringWordFreq.put(removeWord, -1 + substringWordFreq.get(removeWord));
                        i = i + m;
                    }
                    continue;
                }

                while (wordFreq.get(curWord) < substringWordFreq.get(curWord)) {
                    String removeWord = s.substring(i, i + m);
                    substringWordFreq.put(removeWord, -1 + substringWordFreq.get(removeWord));
                    i = i + m;
                }

                if (j - i + 1 == permutationLength) {
                    ans.add(i);
                    String removeWord = s.substring(i, i + m);
                    substringWordFreq.put(removeWord, -1 + substringWordFreq.get(removeWord));
                    i = i + m;
                }
            }
            substringWordFreq.clear();
        }

        return ans;
    }
}
