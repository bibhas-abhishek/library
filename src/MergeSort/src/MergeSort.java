import java.util.Arrays;


public class MergeSort {

    public void mergeSort(int[] array, int[] temp, int low, int high) {
        if (low >= high)
            return;

        int midpoint = (low + high) / 2;

        mergeSort(array, temp, low, midpoint);         // left half
        mergeSort(array, temp, midpoint + 1, high);    // right half
        merge(array, temp, low, midpoint, high);
    }

    private void merge(int[] array, int[] temp, int low, int mid, int high) {
        int leftIndex = low;
        int rightIndex = mid + 1;
        int tempIndex = low;

        // Merge elements into temp
        while (leftIndex <= mid && rightIndex <= high) {
            if (array[leftIndex] <= array[rightIndex]) {
                temp[tempIndex++] = array[leftIndex++];
            } else {
                temp[tempIndex++] = array[rightIndex++];
            }
        }

        // Copy leftover left side
        System.arraycopy(array, leftIndex, temp, tempIndex, mid - leftIndex + 1);

        // Copy leftover right side
        System.arraycopy(array, rightIndex, temp, tempIndex, high - rightIndex + 1);

        // Copy merged range back to original array
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
