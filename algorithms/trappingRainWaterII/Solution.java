package trappingRainWaterII;

import java.util.PriorityQueue;

class Solution {
    int[][] dirs = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    class Cell {
        int i, j, height;
        Cell(int i, int j, int height) {
            this.i = i;
            this.j = j;
            this.height = height;
        }
    }

    public int trapRainWater(int[][] heightMap) {
        /*
        * get the cells of four borders and add them to priority queue. (low height = high priority)
        * this queue contains the cells of the current water trapping border.
        * get the least height cell out of queue.
        * now this is the height that defines the maximum water trapped by this border. let's call this, defining height.
        * get the neighbors of this cell and see if they are unvisited. if so, then update their heights as below,
        * 1. if height of the neighbor is smaller, then it's going to contain water until it reaches defining height.
        * so add water and update its height = defining height.
        * 2. if height of the neighbor is larger, then it's not going to contain any water. keep its height the same.
        * in both the cases add the new neighbor with its updated height into the border queue and mark it visited.
        * this new cell and the rest of the cells in the queue define the new border.
        * keep repeating this process until queue is empty (until the border shrinks and disappears).
        * */

        int m = heightMap.length, n = heightMap[0].length;
        PriorityQueue<Cell> border = new PriorityQueue<>((c1, c2) -> Integer.compare(c1.height, c2.height));
        boolean[][] visited = new boolean[m][n];

        // adding left and right borders
        for (int i = 0; i < m; i++) {
            border.add(new Cell(i, 0, heightMap[i][0]));
            border.add(new Cell(i, n - 1, heightMap[i][n - 1]));
            visited[i][0] = true;
            visited[i][n-1] = true;
        }

        // adding top and bottom borders
        for (int j = 1; j < n - 1; j++) {
            border.add(new Cell(0, j, heightMap[0][j]));
            border.add(new Cell(m - 1, j, heightMap[m-1][j]));
            visited[0][j] = true;
            visited[m-1][j] = true;
        }

        int trappedWater = 0;

        while (!border.isEmpty()) {
            Cell cur = border.poll();
            int definingHeight = cur.height;

            for (int[] dir: dirs) {
                int i = cur.i + dir[0];
                int j = cur.j + dir[1];

                if (i >= 0 && i < m && j >= 0 && j < n && !visited[i][j]) {
                    trappedWater += Math.max(0, definingHeight - heightMap[i][j]);
                    border.offer(new Cell(i, j, Math.max(definingHeight, heightMap[i][j])));
                    visited[i][j] = true;
                }
            }
        }

        return trappedWater;
    }
}