package permutationSequence_60;

class Solution {
    public String getPermutation(int n, int k) {
        int[] fact = new int[] {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
        StringBuilder s = new StringBuilder();
        for (int i = 1; i <= n; i++) s.append(i);

        StringBuilder ans = new StringBuilder();

        k--; // since k is 1 indexed, making it 0 indexed

        while (k > 0) {
            int index = k / fact[n-1];
            ans.append(s.charAt(index));
            s.deleteCharAt(index);
            k -= fact[n-1] * index;
            n--;
        }

        ans.append(s);
        return ans.toString();
    }
}
