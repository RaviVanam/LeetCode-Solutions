package longestPalindromeByConcatenatingTwoLetterWords_2131;

class Solution {
    public int longestPalindrome(String[] words) {
        int[][] freq = new int[26][26];
        boolean unpaired = false;
        int ans = 0;

        for (String word: words) {
            freq[word.charAt(0) - 'a'][word.charAt(1) - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                int min = Math.min(freq[i][j], freq[j][i]);
                if (i == j) {
                    ans += 2 * min ;
                    if (min % 2 != 0) {
                        ans -= 2;
                        unpaired = true;
                    }
                } else {
                    ans += 4 * min;
                }
                freq[i][j] = 0;
                freq[j][i] = 0;
            }
        }

        return (unpaired) ? ans + 2 : ans;
    }
}