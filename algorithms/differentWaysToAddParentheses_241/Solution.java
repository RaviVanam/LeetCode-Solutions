package differentWaysToAddParentheses_241;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    Map<String, List<Integer>> memo = new HashMap<>();

    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> result = new ArrayList<>();
        int n = expression.length();
        for (int i = 0; i < n; i++) {
            if (expression.charAt(i) <= '9' && expression.charAt(i) >= '0') continue;
            String leftPart = expression.substring(0, i);
            String rightPart = expression.substring(i + 1, n);
            List<Integer> leftResult = memo.getOrDefault(leftPart, diffWaysToCompute(leftPart));
            List<Integer> rightResult = memo.getOrDefault(rightPart, diffWaysToCompute(rightPart));

            for (int l: leftResult) {
                for (int r: rightResult) {
                    int ans = 0;
                    if (expression.charAt(i) == '+') ans = l + r;
                    else if (expression.charAt(i) == '-') ans = l - r;
                    else ans = l * r;

                    result.add(ans);
                }
            }
        }

        if (result.size() == 0) {
            result.add(Integer.valueOf(expression));
        }

        memo.put(expression, result);
        return result;
    }
}
