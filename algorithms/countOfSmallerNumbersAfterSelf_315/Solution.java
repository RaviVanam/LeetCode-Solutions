package countOfSmallerNumbersAfterSelf_315;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
    class NumsWithIndices {
        int val;
        int pos;
        NumsWithIndices(int val, int pos) {
            this.val = val;
            this.pos = pos;
        }
    }


    public List<Integer> countSmaller(int[] nums) {
        int[] result = new int[nums.length];

        // construct arr with indices
        NumsWithIndices[] arr = new NumsWithIndices[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = new NumsWithIndices(nums[i], i);
        }

        // do merge sort and count smaller elements to the right for each element
        mergeSort(arr, result, 0, nums.length - 1);
        return IntStream.of(result).boxed().toList();
    }

    private void mergeSort(NumsWithIndices[] arr, int[] result, int start, int end) {
        if (start >= end) return;
        int mid = start + (end - start) / 2;

        mergeSort(arr, result, start, mid);
        mergeSort(arr, result, mid + 1, end);

        // merge [start, mid] && [mid + 1, end]
        List<NumsWithIndices> merged = new ArrayList<>();

        int elemsRightArrLessThanLeftArr = 0;

        int left = start;
        int right = mid + 1;

        while (left <= mid && right <= end) {
            if (arr[left].val > arr[right].val) {
                elemsRightArrLessThanLeftArr++;

                merged.add(arr[right]);
                right++;
            } else {
                result[arr[left].pos] += elemsRightArrLessThanLeftArr;

                merged.add(arr[left]);
                left++;
            }
        }

        while (left <= mid) {
            result[arr[left].pos] += elemsRightArrLessThanLeftArr;
            merged.add(arr[left]);
            left++;
        }

        while (right <= end) {
            merged.add(arr[right]);
            right++;
        }

        int i = start;
        for (NumsWithIndices ele: merged) {
            arr[i] = ele;
            i++;
        }
    }
}
