package makeTheStringGreat_1544;

import java.util.ArrayDeque;
import java.util.Deque;


// A = 65, a = 97. abs diff = 97 - 65 = 32
class Solution {
    public String makeGood(String s) {
        Deque<Character> stk = new ArrayDeque<>();
        for (char c: s.toCharArray()) {
            if (!stk.isEmpty() && Math.abs(c - stk.peek()) == 32) {
                stk.pop();
            } else {
                stk.push(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character c: stk) {
            sb.append(c);
        }

        return sb.reverse().toString();
    }
}