package numberOfBeautfulPartitions_2478;

import java.util.Arrays;

class Solution {
    private final boolean[] primes = new boolean[] {
            false, false, true, true, false,
            true, false, true, false, false
    };

    private boolean isPrime(char c) {
        return primes[c - '0'];
    }

    public int beautifulPartitions(String s, int k, int minLength) {
        int len = s.length();
        if (!isPrime(s.charAt(0)) || isPrime(s.charAt(len - 1))) return 0;

        int noOfPrimeStarts = 1;
        for (int i = 1; i < len; i++) {
            if (isPrime(s.charAt(i)) && !isPrime(s.charAt(i-1))) noOfPrimeStarts++;
        }

        int[] primesIndices = new int[noOfPrimeStarts];
        int cur = 1;
        for (int i = 1; i < len; i++) {
            if (isPrime(s.charAt(i)) && !isPrime(s.charAt(i-1))) {
                primesIndices[cur] = i;
                cur++;
            }
        }

        int[][] memo = new int[noOfPrimeStarts][k];
        for (int[] row: memo) Arrays.fill(row, -1);
        return findPartitions(primesIndices, len, minLength, 0, k - 1, memo);
    }

    private int findPartitions(int[] primesIndices, int len, int minLength, int prev, int k, int[][] memo) {
        if (memo[prev][k] != -1) return memo[prev][k];
        if (k == 0) return (len - primesIndices[prev] >= minLength) ? 1 : 0;

        int curPartitions = 0;
        for (int i = prev + 1; i < primesIndices.length; i++) {
            if ( primesIndices[i] - primesIndices[prev] >= minLength)
                curPartitions += findPartitions(primesIndices, len, minLength, i, k - 1, memo);
            curPartitions = curPartitions % 1000000007;
        }

        memo[prev][k] = curPartitions % 1000000007;
        return memo[prev][k];
    }
}