package countPalindromicSubsequence_2484;

import java.util.Arrays;

class Solution {
    public int countPalindromes(String s) {
        int mod = 1000_000_007;
        int[][][] pre = new int[10][10][s.length()];
        int[][][] post = new int[10][10][s.length()];
        char[] chars = s.toCharArray();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int count = 0;

                for (int k = 0; k < chars.length; k++) {
                    if (k > 0) pre[i][j][k] = pre[i][j][k-1];
                    if (chars[k] - '0' == j) pre[i][j][k] += count;
                    if (chars[k] - '0' == i) count++;
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int count = 0;

                for (int k = chars.length - 1; k >= 0; k--) {
                    if (k < chars.length - 1) post[i][j][k] = post[i][j][k+1];
                    if (chars[k] - '0' == i) post[i][j][k] += count;
                    if (chars[k] - '0' == j) count++;
                }
            }
        }

        System.out.println(Arrays.toString(pre[1][0]));
        System.out.println(Arrays.toString(post[0][1]));

        int ans = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 2; k < chars.length - 2; k++) {
                    ans = (int)((ans + 1L * pre[i][j][k - 1] * post[j][i][k + 1]) % mod);
                }
            }
        }

        return ans;
    }
}