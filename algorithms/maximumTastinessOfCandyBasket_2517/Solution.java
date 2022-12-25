package maximumTastinessOfCandyBasket_2517;

import java.util.Arrays;

class Solution {
    public int maximumTastiness(int[] price, int k) {
        int r = 1000_000_000;
        int l = 0;

        Arrays.sort(price);

        // binary search 'difference' over 0 to 1e9
        // find the least 'difference' that can be a valid difference for some sequence of k numbers in price[] array
        while (l <= r) {
            int difference = l + (r - l) / 2;
            if (isPossible(difference, price, k)) l = difference + 1;
            else r = difference - 1;
            System.out.println(difference);
        }

        return l - 1;
    }

    // is the 'difference' possible in the array?
    private boolean isPossible(int difference, int[] price, int k) {
        int prev = price[0];
        int count = 1;
        for (int i = 1; i < price.length; i++) {
            if (price[i] - prev >= difference) {
                prev = price[i];
                count++;

                if (count == k) return true;
            }
        }
        return false;
    }
}