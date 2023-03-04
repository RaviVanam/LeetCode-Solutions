package pow_Of;

class Solution {
    public static void main(String[] args) {
        new Solution().myPow(2, -2147483648);
    }

    public double myPow(double x, int n2) {
        long n = n2;
        double ans = 1;
        if (x == 1d) return ans;

        if (n < 0) {
            x = 1/x;
            n = -n;
        }

        while (n != 0) {
            System.out.println(n);
            if (n % 2 != 0) {
                ans *= x;
                n = (n - 1);
            }

            x = x * x;
            n = n / 2;
        }

        return ans;
    }
}