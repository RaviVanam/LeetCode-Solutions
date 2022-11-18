package uglyNumberIII_1201;

public class Solution {
    public int nthUglyNumber(int n, int a, int b, int c) {
        int r = Integer.MAX_VALUE - 100;
        int l = 0;

        long ab_lcm = lcm(a, b);
        long ac_lcm = lcm(a, c);
        long bc_lcm = lcm(b, c);
        long abc_lcm = lcm(ab_lcm, c);
        System.out.println(abc_lcm + " " + ac_lcm + " " + bc_lcm + " " + abc_lcm);

        while (l < r) {
            int m = l + (r - l) / 2;
            int count =  (int) (m/a + m/b + m/c - m/ab_lcm - m/ac_lcm - m/bc_lcm + m/abc_lcm);
            if (count >= n) {
                r = m;
            } else {
                l = m + 1;
            }
        }

        return r;
    }

    private long gcd(long a, long b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }
    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

}
