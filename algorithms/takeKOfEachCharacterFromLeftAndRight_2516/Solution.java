package takeKOfEachCharacterFromLeftAndRight_2516;

class Solution {
    public int takeCharacters(String s, int k) {
        // count the number of 'a's 'b's and 'c's
        int num_of_a = 0;
        int num_of_b = 0;
        int num_of_c = 0;

        char[] arr = s.toCharArray();
        for (char c: arr) {
            if (c == 'a') num_of_a++;
            else if (c == 'b') num_of_b++;
            else num_of_c++;
        }

        // return -1 if any of them are less than k
        if (num_of_a < k || num_of_b < k || num_of_c < k) return -1;


        // find the maximum window such that the count 'a's 'b's and 'c's in left of the array is still >= k
        int i = 0;
        int maxWindow = 0;

        for (int j = 0; j < arr.length; j++) {
            if (arr[j] == 'a') num_of_a--;
            else if (arr[j] == 'b') num_of_b--;
            else num_of_c--;

            while (num_of_a < k || num_of_b < k || num_of_c < k) {
                if (arr[i] == 'a') num_of_a++;
                else if (arr[i] == 'b') num_of_b++;
                else num_of_c++;
                i++;
            }

            maxWindow = Math.max(maxWindow, j - i + 1);
        }

        return arr.length - maxWindow;
    }
}