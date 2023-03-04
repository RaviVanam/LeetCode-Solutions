package basicCalculator_224;

import java.util.Stack;

// Todo: Create Xournal Solution for this
public class Solution {
    public int calculate(String s) {
        int n = s.length();
        Stack<Integer> curScopeSign = new Stack<>();
        curScopeSign.push(1);
        int sign = 1;
        int result = 0;
        int cur = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                cur = cur * 10 + c - '0';
            }
            if ((!Character.isDigit(c) && s.charAt(i) != ' ') || i == n - 1) {
                result += sign * cur;
                if (c == '(') curScopeSign.push(sign);
                else if (c == ')') curScopeSign.pop();
                else if (c == '+' || c == '-') sign = (c == '+' ? 1 : -1) * curScopeSign.peek();
                cur = 0;
            }
        }

        return result;
    }
}

//public class Solution {
//    public static void main(String[] args) {
//        int ans = new Solution().calculate("2147483647");
//        int b = 2;
//    }
//
//    public int calculate(String s) {
//        Stack<String> stack = new Stack<>();
//        Stack<String> execStack = new Stack<>();
//        int len = s.length();
//        for (int i = 0; i < len; i++) {
//            if (s.charAt(i) == ' ') continue;
//            if (s.charAt(i) == ')') {
//                String c = stack.pop();
//                while (!c.equals("(")) {
//                    execStack.push(c);
//                    c = stack.pop();
//                }
//                stack.push(evaluate(execStack));
//            } else if (s.charAt(i) == '(' || s.charAt(i) == '+' || s.charAt(i) == '-') {
//                stack.push(s.substring(i, i + 1));
//            } else {
//                int j = i + 1;
//                while (j < len && s.charAt(j) <= '9' && s.charAt(j) >= '0') j++;
//                stack.push(s.substring(i, j));
//                i = j - 1;
//            }
//        }
//
//        while (!stack.isEmpty()) {
//            execStack.push(stack.pop());
//        }
//
//        return Integer.parseInt(evaluate(execStack));
//    }
//
//    private String evaluate(Stack<String> execStack) {
//        int ans = 0;
//        if (!execStack.peek().equals("-"))
//            ans = Integer.parseInt(execStack.pop());
//        while (!execStack.isEmpty()) {
//            String op = execStack.pop();
//            int a = Integer.parseInt(execStack.pop());
//            if (op.equals("+")) ans += a;
//            else if (op.equals("-")) ans -= a;
//        }
//
//        return String.valueOf(ans);
//    }
//}
