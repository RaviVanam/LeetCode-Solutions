package countNumberOfPossibleRootNodes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// a re-rooting problem
class Solution {
    Set<String> guessSet;
    int rightGuesses;
    public int rootCount(int[][] edges, int[][] guesses, int k) {
        int n = edges.length + 1;
        guessSet = new HashSet<>();
        for (int[] guess: guesses) guessSet.add(guess[0] + " " + guess[1]);


        // build tree
        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }

        for (int[] edge: edges) {
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
        }

        // find number of right guesses if root was 0
        // do dfs
        rightGuesses = findGuessesDFS(tree, 0, -1);


        int[] rights = new int[n];
        dfs(tree,rights, 0, -1);

        int ans = 0;
        for (int count: rights) {
            if (count >= k) ans++;
        }

        return ans;
    }

    private void dfs(List<List<Integer>> tree, int[] rights, int cur, int prev) {
        List<Integer> children = tree.get(cur);

        if (prev == -1) rights[cur] = rightGuesses;
        else {
            int x = (guessSet.contains(prev + " " + cur)) ? -1 : 0;
            int y = (guessSet.contains(cur + " " + prev)) ? 1 : 0;
            rights[cur] = rights[prev] + x + y;
        }

        for (int child: children) {
            if (child == prev) continue;
            dfs(tree, rights, child, cur);
        }
    }

    private int findGuessesDFS(List<List<Integer>> tree, int cur, int prev) {
        List<Integer> children = tree.get(cur);
        if (children.size() == 1 && prev != -1) return 0;

        int rightGuesses = 0;
        for (int child: children) {
            if (child == prev) continue;
            if (guessSet.contains(cur + " " + child)) rightGuesses++;
            rightGuesses += findGuessesDFS(tree, child, cur);
        }

        return rightGuesses;
    }
}