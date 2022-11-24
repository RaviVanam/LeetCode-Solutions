package lastDayWhereYouCanStillCross_1970;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        System.out.print(new Solution().latestDayToCross(3, 3, new int[][]{{1,2},{2,1},{3,3}}));
    }

    public int latestDayToCross(int row, int col, int[][] cells) {
        int[][] land = new int[row][col];
        for (int[] landRow: land) Arrays.fill(landRow, cells.length + 1);
        for (int day = 0; day < cells.length; day++) {
            int landRow = cells[day][0] - 1;
            int landCol = cells[day][1] - 1;
            land[landRow][landCol] = day;
        }

        int l = 0;
        int r = cells.length - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            boolean crossed = canCross(land, m);
            if (crossed) {
                l = m + 1;
            } else {
                r = m;
            }
        }

        if (canCross(land, r)) return r + 1;
        return r;
    }

    private boolean canCross(int[][] land, int day) {
        int rows = land.length;
        int cols = land[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < land[0].length; i++) {
            if (dfs(land, day, visited, 0, i)) return true;
        }
        return false;
    }

    private boolean dfs(int[][] land, int day, boolean[][] visited, int i, int j) {
        if (i < 0 || i >= land.length || j < 0 || j >= land[0].length) return false;
        if (visited[i][j] || land[i][j] <= day) return false;
        if (i == land.length - 1) return true;
        visited[i][j] = true;

        if (dfs(land, day, visited, i - 1, j)) return true;
        if (dfs(land, day, visited, i + 1, j)) return true;
        if (dfs(land, day, visited, i, j - 1)) return true;
        if (dfs(land, day, visited, i, j + 1)) return true;

        return false;
    }
}
