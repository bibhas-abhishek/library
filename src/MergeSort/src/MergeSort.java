import java.util.Arrays;


public class MergeSort {

    // Recursively sorts the portion of 'array' between indices start and end (inclusive)
    // using a temporary array 'temp' to merge subarrays.
    public void mergeSort(int[] array, int[] temp, int start, int end) {
        if (start >= end) {
            return; // base case: a single element (or invalid range) is already sorted
        }

        int mid = (start + end) / 2;
        // sort left half
        mergeSort(array, temp, start, mid);
        // sort right half
        mergeSort(array, temp, mid + 1, end);
        // merge the two sorted halves into one sorted segment
        merge(array, temp, start, end);
    }

    // Merges two consecutive sorted subarrays within 'array' into 'temp',
    // then copies the merged result back into 'array'.
    // The left subarray is [leftStart .. leftEnd] and right subarray is [rightStart .. rightEnd].
    private void merge(int[] array, int[] temp, int leftStart, int rightEnd) {
        int leftEnd = (leftStart + rightEnd) / 2; // midpoint dividing left and right halves
        int rightStart = leftEnd + 1;
        int l = leftStart;   // pointer for left subarray
        int r = rightStart;  // pointer for right subarray
        int index = leftStart; // write position in temp

        // Merge elements from both halves in sorted order
        while (l <= leftEnd && r <= rightEnd) {
            if (array[l] <= array[r]) {
                temp[index++] = array[l++]; // take from left when smaller or equal
            } else {
                temp[index++] = array[r++]; // take from right otherwise
            }
        }

        // If left side still has elements, copy remaining left elements into temp
        System.arraycopy(array, l, temp, index, leftEnd - l + 1);
        // If right side still has elements, copy remaining right elements into temp
        System.arraycopy(array, r, temp, index, rightEnd - r + 1);
        // Copy the merged range from temp back into the original array
        System.arraycopy(temp, leftStart, array, leftStart, rightEnd - leftStart + 1);
    }

    public static void main(String[] args) {
        MergeSort obj = new MergeSort();
        int[] array = {10, 5, 7, 8, 1, 2, 6, 3, 4, 9};
        int[] temp = new int[array.length];
        obj.mergeSort(array, temp, 0, array.length - 1);
        Arrays.stream(array).mapToObj(e -> e + " ").forEach(System.out::print);
    }

    /*
     * Overall algorithm complexity (entire implementation):
     *
     * Time complexity: O(n log n)
     * Space complexity: O(n)
     *
     * Explanation:
     * The algorithm divides the array recursively into halves (log n levels) and
     * performs linear-time merging at each level, resulting in O(n log n) time overall.
     * A temporary array of size proportional to the input (used during merging)
     * is required, so the auxiliary space is O(n).
     */
}
