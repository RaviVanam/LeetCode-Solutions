package closestNodesQueriesInABinarySearchTree_2476;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
    }
}

class Solution {
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<Integer> sorted = new ArrayList<>();
        fillList(sorted, root);

        List<List<Integer>> ans = new ArrayList<>();

        int ns = sorted.size();
        int nq = queries.size();
        for (int i = 0; i < nq; i++) {
            int query = queries.get(i);

            int l = 0;
            int r = ns - 1;

            while (l < r) {
                int m = l + (r-l)/2;
                if (sorted.get(m) < query) l = m + 1;
                else r = m;
            }

            if (query == sorted.get(r)) ans.add(Arrays.asList(query, query));
            else if (sorted.get(r) < query) ans.add(Arrays.asList(sorted.get(r), -1));
            else if (r == 0) ans.add(Arrays.asList(-1, sorted.get(r)));
            else ans.add(Arrays.asList(sorted.get(r-1), sorted.get(r)));

        }

        return ans;
    }

    private void fillList(List<Integer> sorted, TreeNode root) {
        if (root != null) {
            fillList(sorted, root.left);
            sorted.add(root.val);
            fillList(sorted, root.right);
        }
    }
}