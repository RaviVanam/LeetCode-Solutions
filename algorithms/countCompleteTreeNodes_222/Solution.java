package countCompleteTreeNodes_222;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int left = findDepth(root, true);
        int right = findDepth(root, false);

        if (left == right) return (int)Math.pow(2, 1 + left) - 1;

        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private int findDepth(TreeNode node, boolean left) {
        int count = 0;
        if (left) {
            while (node.left != null) {
                count++;
                node = node.left;
            }
            return count;
        } else {
            while (node.right != null) {
                count++;
                node = node.right;
            }
            return count;
        }
    }
}