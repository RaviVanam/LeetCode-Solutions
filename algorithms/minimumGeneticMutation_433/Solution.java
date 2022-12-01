package minimumGeneticMutation_433;

import java.util.*;

class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {
        Set<String> bankSet = new HashSet<>();
        Collections.addAll(bankSet, bank);

        if (!bankSet.contains(endGene)) return -1;

        char[] geneCharSet = new char[] {'A', 'C', 'G', 'T'};
        Deque<String> queue = new ArrayDeque<>();
        queue.add(startGene);
        bankSet.remove(startGene);

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (cur.equals(endGene)) return level;

                char[] curArr = cur.toCharArray();
                for (int loc = 0; loc < curArr.length; loc++) {
                    char old = curArr[loc];
                    for (char c: geneCharSet) {
                        curArr[loc] = c;
                        String next = new String(curArr);
                        if (bankSet.contains(next)) {
                            queue.offer(next);
                            bankSet.remove(next);
                        }
                    }
                    curArr[loc] = old;
                }
            }

            level++;
        }

        return -1;
    }
}
