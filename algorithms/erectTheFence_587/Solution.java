package erectTheFence_587;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

// using monotone chain algorithm for convex hull to solve this question
class Solution {

    public int[][] outerTrees(int[][] points) {
        // sort based on x co-ordinate and where x co-ordinate is equal sort based on y co-ordinate
        Arrays.sort(points, (a, b) -> (a[0] == b[0]) ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));

        Stack<int[]> hull = new Stack<>();

        for (int i = 0; i < points.length; i++) {
            int size = hull.size();
            while (size >= 2 && orientation(hull.get(size - 2), hull.get(size - 1), points[i]) < 0) {
                hull.pop();
                size--;
            }
            hull.push(points[i]);
        }

        hull.pop();
        for (int i = points.length - 1; i >= 0; i--) {
            int size = hull.size();
            while (size >= 2 && orientation(hull.get(size - 2), hull.get(size - 1), points[i]) < 0) {
                hull.pop();
                size--;
            }
            hull.push(points[i]);
        }

        Set<int[]> unique = new HashSet<>(hull);
        return unique.toArray(new int[unique.size()][]);
    }

    private int orientation(int[] p, int[] q, int[] r) {
        return (q[1] - p[1]) * (r[0] - q[0]) - (q[0] - p[0]) * (r[1] - q[1]);
    }
}
