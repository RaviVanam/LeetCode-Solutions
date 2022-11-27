package maximumEarningsFromTaxi_2008;

import java.util.Arrays;
import java.util.TreeMap;

class Solution {
    public long maxTaxiEarnings(int n, int[][] rides) {
        Arrays.sort(rides, (a, b) -> Integer.compare(a[1], b[1]));
        TreeMap<Integer, Long> dp = new TreeMap<>();

        dp.put(-1, 0L);
        for (int[] ride : rides) {
            long pick = dp.floorEntry(ride[0]).getValue() + ride[1] - ride[0] + ride[2];
            long noPick = dp.lastEntry().getValue();
            dp.put(ride[1], Math.max(pick, noPick));
        }

        return dp.lastEntry().getValue();
    }
}