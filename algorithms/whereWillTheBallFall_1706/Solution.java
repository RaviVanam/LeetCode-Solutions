package whereWillTheBallFall_1706;

import java.util.Arrays;

class Solution {
    public int[] findBall(int[][] grid) {
        //  1 = \
        // -1 = /
        // dfs might be the optimal solution but
        // since m and n < 100 dfs won't give a better performance than just doing n*m simulation

        int[] ans = new int[grid[0].length];
        int[] nextAns = new int[grid[0].length];
        for (int i = 0; i < grid[0].length; i++) ans[i] = i;
        Arrays.fill(nextAns, -1);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (ans[j] != -1) {
                    if ( j > 0 && grid[i][j] == -1 && grid[i][j-1] == grid[i][j]) {
                        nextAns[j-1] = ans[j];
                    } else if (j < grid[0].length-1 && grid[i][j] == 1 && grid[i][j+1] == grid[i][j]) {
                        nextAns[j+1] = ans[j];
                    }
                }
            }
            int[] temp = ans;
            ans = nextAns;
            nextAns = temp;
            Arrays.fill(nextAns, -1);
        }

        int[] result = new int[grid[0].length];
        Arrays.fill(result, -1);
        for (int i = 0; i < grid[0].length; i++) if (ans[i] != -1) result[ans[i]] = i;

        return result;
    }
}
