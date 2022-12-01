package orderlyQueue_899;

import java.util.Arrays;

class Solution {
    public String orderlyQueue(String s, int k) {
        if (k == 1) return rotate(s);
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        for (char c: arr) sb.append(c);
        return sb.toString();
    }

    private String rotate(String s) {
        int min = 0, n = s.length();
        for (int i = 1; i < n; i++) {
            if (compareString(s, n, i, min) == -1) {
                min = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < n; k++) {
            sb.append(s.charAt((min + k) % n));
        }

        return sb.toString();
    }

    // -1 if 'i' is less than 'j'. 0 if equal. 1 if 'i' is greater
    private int compareString(String s, int n, int i, int j) {
        int count = 0;
        while (count < n && s.charAt(i) == s.charAt(j)) {
            i = (i+1) % n;
            j = (j+1) % n;
            count++;
        }

        if (count == n) return 0;
        else if (s.charAt(i) < s.charAt(j)) return -1;
        return 1;
    }
}