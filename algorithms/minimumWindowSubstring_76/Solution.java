package minimumWindowSubstring_76;

public class Solution {
    public String minWindow(String s, String t) {
        int n = s.length();
        int[] setT = new int[128];
        int[] curSet = new int[128];
        int min = Integer.MAX_VALUE;
        String ans = "";

        for (char c: t.toCharArray()) setT[c]++;

        int l = 0;
        for (int r = 0; r < n; r++) {
            curSet[s.charAt(r)]++;
            if (validate(curSet, setT)) {
                while (validate(curSet, setT)) {
                    curSet[s.charAt(l)]--;
                    l++;
                }
                if (r - l + 2 < min) {
                    min = r - l + 2;
                    ans = s.substring(l-1, r + 1);
                }
            }
        }

        return ans;
    }

    private boolean validate(int[] curSet, int[] setT) {
        for (int i = 0; i < 128; i++) {
            if (curSet[i] < setT[i]) return false;
        }
        return true;
    }
}
