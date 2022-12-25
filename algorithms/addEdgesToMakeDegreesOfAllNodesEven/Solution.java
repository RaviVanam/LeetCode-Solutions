package addEdgesToMakeDegreesOfAllNodesEven;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public boolean isPossible(int n, List<List<Integer>> edges) {
        //number of odd degree nodes should be equal to 2 or 4. if 0 then return true;
        int[] degrees = new int[1+n];
        List<Integer> oddNodes = new ArrayList<>();
        List<Set<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new HashSet<>());
        for (List<Integer> edge: edges) {
            degrees[edge.get(0)]++;
            degrees[edge.get(1)]++;
            adj.get(edge.get(0)).add(edge.get(1));
            adj.get(edge.get(1)).add(edge.get(0));
        }

        for (int i = 1; i <= n; i++) {
            if (degrees[i] % 2 == 1) oddNodes.add(i);
        }

        if (oddNodes.size() > 4 || oddNodes.size() % 2 == 1) return false;
        if (oddNodes.size() == 0) return true;

        // if two odd nodes are not connected to one another see if there is a common node that they are both not connected to

        if (oddNodes.size() == 2) {
            int node1 = oddNodes.get(0);
            int node2 = oddNodes.get(1);
            if (!adj.get(node1).contains(node2)) return true;
            for (int i = 1; i <= n; i++) {
                if (!adj.get(node1).contains(i) && !adj.get(node2).contains(i)) return true;
            }
        } else if (oddNodes.size() == 4) {
            int node1 = oddNodes.get(0);
            int node2 = oddNodes.get(1);
            int node3 = oddNodes.get(2);
            int node4 = oddNodes.get(3);

            if (!adj.get(node1).contains(node2) && !adj.get(node3).contains(node4)) return true;
            if (!adj.get(node2).contains(node3) && !adj.get(node1).contains(node4)) return true;
            if (!adj.get(node1).contains(node3) && !adj.get(node2).contains(node4)) return true;
        }

        return false;
    }
}