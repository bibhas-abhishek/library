import java.util.Arrays;


public class MergeSort {

    // Recursively splits the array and merges sorted halves
    public void mergeSort(int[] array, int[] temp, int low, int high) {
        if (low >= high)    // base case: single element
            return;

        int midpoint = (low + high) / 2;

        // Sort left half
        mergeSort(array, temp, low, midpoint);

        // Sort right half
        mergeSort(array, temp, midpoint + 1, high);

        // Merge sorted halves
        merge(array, temp, low, midpoint, high);
    }

    // Merges two sorted subarrays: array[low..mid] and array[mid+1..high]
    private void merge(int[] array, int[] temp, int low, int mid, int high) {
        int leftIndex = low;       // pointer for left subarray
        int rightIndex = mid + 1;  // pointer for right subarray
        int tempIndex = low;       // pointer in temp array

        // Merge elements until one side is exhausted
        while (leftIndex <= mid && rightIndex <= high) {
            if (array[leftIndex] <= array[rightIndex]) {
                temp[tempIndex++] = array[leftIndex++];
            } else {
                temp[tempIndex++] = array[rightIndex++];
            }
        }

        // Copy leftovers from left side
        System.arraycopy(array, leftIndex, temp, tempIndex, mid - leftIndex + 1);

        // Copy leftovers from right side
        System.arraycopy(array, rightIndex, temp, tempIndex, high - rightIndex + 1);

        // Copy merged result back into original array
        System.arraycopy(temp, low, array, low, high - low + 1);
    }

    public static void main(String[] args) {
        MergeSort obj = new MergeSort();
        int[] array = {10, 5, 7, 8, 1, 2, 6, 3, 4, 9};
        int[] temp = new int[array.length];
        obj.mergeSort(array, temp, 0, array.length - 1);
        Arrays.stream(array).forEach(e -> System.out.print(e + " "));
    }
}
