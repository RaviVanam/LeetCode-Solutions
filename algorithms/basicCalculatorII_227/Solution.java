package basicCalculatorII_227;

import java.util.Stack;

class Solution {
    public int calculate(String s) {
        int n = s.length();
        Stack<Integer> stk = new Stack<>();
        char sign = '+';
        int cur = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                cur = 10 * cur + c - '0';
            }
            if ((!Character.isDigit(c) && c != ' ') || i == n - 1) {
                if (sign == '+') stk.push(cur);
                else if (sign == '-') stk.push(-cur);
                else if (sign == '*') stk.push(stk.pop() * cur);
                else stk.push(stk.pop() / cur);
                sign = c;
                cur = 0;
            }
        }

        int result = 0;
        for (int r: stk) {
            result += r;
        }
        return result;
    }
}
