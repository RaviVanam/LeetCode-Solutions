package escapeTheSpreadingFire_2258;

import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    int[][] dirs = new int[][] {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public int maximumMinutes(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] fireArrival = new int[m][n];
        int[][] playerArrival = new int[m][n];

        Queue<int[]> fireQueue = new ArrayDeque<>();
        Queue<int[]> playerQueue = new ArrayDeque<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                playerArrival[i][j] = -1;
                if (grid[i][j] == 1) {
                    fireArrival[i][j] = 0;
                    fireQueue.add(new int[]{i, j});
                } else {
                    fireArrival[i][j] = -1;
                }
            }
        }

        playerArrival[0][0] = 0;
        playerQueue.add(new int[]{0, 0});

        // bfs for fire
        int bfsLevel = 0;
        while (!fireQueue.isEmpty()) {
            int size = fireQueue.size();
            bfsLevel++;
            for (int count = 0; count < size; count++) {
                int[] pos = fireQueue.poll();
                int i = pos[0], j = pos[1];
                for (int[] dir: dirs) {
                    int x = i + dir[0], y = j + dir[1];
                    if (x >= m || x < 0 || y >= n || y < 0 || fireArrival[x][y] != -1 || grid[x][y] == 2) continue;
                    fireArrival[x][y] = bfsLevel;
                    fireQueue.offer(new int[]{x, y});
                }
            }
        }

        // bfs for player
        bfsLevel = 0;
        while (!playerQueue.isEmpty()) {
            int size = playerQueue.size();
            bfsLevel++;
            for (int count = 0; count < size; count++) {
                int[] pos = playerQueue.poll();
                int i = pos[0], j = pos[1];
                for (int[] dir: dirs) {
                    int x = i + dir[0], y = j + dir[1];
                    if (x >= m || x < 0 || y >= n || y < 0 || playerArrival[x][y] != -1 || grid[x][y] == 2) continue;
                    playerArrival[x][y] = bfsLevel;
                    playerQueue.offer(new int[]{x, y});
                }
            }
        }

        // if the fire never arrives but player arrives
        if (fireArrival[m-1][n-1] == -1 && playerArrival[m-1][n-1] != -1) return 1000_000_000;

        // if fire arrives before the player or the player never arrives
        if (playerArrival[m-1][n-1] == -1 || playerArrival[m-1][n-1] > fireArrival[m-1][n-1]) return -1;

        boolean fireArrivalFromTop = fireArrival[m-1][n-1] - 1 == fireArrival[m-2][n-1];
        boolean fireArrivalFromLeft = fireArrival[m-1][n-1] - 1 == fireArrival[m-1][n-2];
        boolean playerArrivalFromLeft = playerArrival[m-1][n-1] - 1 == playerArrival[m-1][n-2];
        boolean playerArrivalFromTop = playerArrival[m-1][n-1] - 1 == playerArrival[m-2][n-1];

        if ((!fireArrivalFromLeft && playerArrivalFromLeft) || (!fireArrivalFromTop && playerArrivalFromTop))
            return fireArrival[m-1][n-1] - playerArrival[m-1][n-1];

        return fireArrival[m-1][n-1] - playerArrival[m-1][n-1] - 1;
    }
}
