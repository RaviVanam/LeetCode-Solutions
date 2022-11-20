package test;

import java.util.Stack;

public class Solution {
    int kthValue(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        int count = 0;

        while (!stack.empty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
                continue;
            }

            count++;
            if (count == k) return stack.pop().val;
            root = stack.pop().right;
        }

        return 0;
    }
}
