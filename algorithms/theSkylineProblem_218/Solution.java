package theSkylineProblem_218;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

class Solution {
    class Point {
        int x;
        int height;
        boolean start;
        Point(int x, int height, boolean start) {
            this.x = x;
            this.height = height;
            this.start = start;
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        // answer should contain the contour points where the "height changes"
        // initially height is 0

        // process the start and end points of buildings' tops because we don't really need the baseline points
        // sort the points based on x coordinates and if they are one same x coordinate then do this
        // 1. if they are both start points then the taller point comes first
        // 2. if they are both end points then the shorter point comes first
        // 3. if they are start and end points then the start point comes first

        List<List<Integer>> ans = new ArrayList<>();
        Point[] points = new Point[buildings.length * 2];
        for (int i = 0; i < buildings.length; i++) {
            points[2*i] = new Point(buildings[i][0], buildings[i][2], true);
            points[2*i+1] = new Point(buildings[i][1], buildings[i][2], false);
        }

        Arrays.sort(points, (p1, p2) -> {
            if (p1.x != p2.x) return Integer.compare(p1.x, p2.x);
            return (p1.start == p2.start)
                    ? ((p1.start) ? Integer.compare(p2.height, p1.height) : Integer.compare(p1.height, p2.height))
                    : ((p1.start) ? -1 : 1);
        });

        // key = height, value = frequency
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        tm.put(0, 1);
        int prevHeight = 0, curHeight = 0;

        for (Point point: points) {
            if (point.start) {
                tm.compute(point.height, (key, value) -> (value == null) ? 1 : value + 1);
            } else {
                tm.compute(point.height, (key, value) -> (value == 1) ? null : value - 1);
            }

            curHeight = tm.lastKey();

            if (curHeight != prevHeight) {
                ans.add(Arrays.asList(point.x, curHeight));
                prevHeight = curHeight;
            }
        }

        return ans;
    }
}
