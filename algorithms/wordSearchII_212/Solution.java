package wordSearchII_212;

import java.util.ArrayList;
import java.util.List;

class TrieNode {
    TrieNode[] next;
    String val;
    TrieNode() {
        next = new TrieNode[26];
    }
}

class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = new TrieNode();
        for (String word: words) populate(root, word, 0);

        List<String> ans = new ArrayList<>();

        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                trieSearch(board, i, j, m, n, root, ans, visited);
            }
        }

        return ans;
    }

    private void trieSearch(char[][] board, int i, int j, int m, int n, TrieNode root, List<String> ans, boolean[][] visited) {
        if (root.val != null) {
            ans.add(root.val);
            root.val = null; // don't do return; because there might be another word that you can add continuing this trie branch
        }

        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j]) return;

        char cur = board[i][j];
        visited[i][j] = true;
        TrieNode nextRoot = root.next[cur - 'a'];
        if (nextRoot != null) {
            trieSearch(board, i + 1, j, m, n, nextRoot, ans, visited);
            trieSearch(board, i - 1, j, m, n, nextRoot, ans, visited);
            trieSearch(board, i, j + 1, m, n, nextRoot, ans, visited);
            trieSearch(board, i, j - 1, m, n, nextRoot, ans, visited);
        }

        visited[i][j] = false;
    }

    private void populate(TrieNode root, String word, int index) {
        if (index == word.length()) {
            root.val = word;
            return;
        }

        if (root.next[word.charAt(index) - 'a'] == null)
            root.next[word.charAt(index) - 'a'] = new TrieNode();

        populate(root.next[word.charAt(index) - 'a'], word, index + 1);
    }
}