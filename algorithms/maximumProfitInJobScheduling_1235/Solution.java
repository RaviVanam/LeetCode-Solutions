package maximumProfitInJobScheduling_1235;

import java.util.Arrays;
import java.util.TreeMap;

class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int[][] jobs = new int[profit.length][3];
        for (int i = 0; i < jobs.length; i++) jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        Arrays.sort(jobs, (a, b) -> Integer.compare(a[1], b[1])); // sort by end times
        TreeMap<Integer, Integer> dp = new TreeMap<>(); // map endtime to dp value

        dp.put(-1, 0);
        for (int i = 0; i < jobs.length; i++) {
            int pick = dp.floorEntry(jobs[i][0]).getValue() + jobs[i][2];
            int noPick = dp.lastEntry().getValue();
            dp.put(jobs[i][1], Math.max(pick, noPick));
        }

        return dp.lastEntry().getValue();
    }
}
