public class QuickSort {

    // Sorts the array using QuickSort between indices 'start' and 'end'
    public void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            // Partition the array and get the pivot's correct position
            int partitionIndex = partition(arr, start, end);

            // Recursively sort the left portion
            quickSort(arr, start, partitionIndex - 1);

            // Recursively sort the right portion
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    // Partitions the array around the pivot (last element)
    // Elements <= pivot move to the left section
    private int partition(int[] arr, int start, int end) {
        int pIndex = start;      // Marks the boundary of elements <= pivot
        int pivot = arr[end];    // Choose last element as pivot

        // Scan through array and move smaller elements to the left section
        for (int i = start; i < end; i++) {
            if (arr[i] <= pivot) {
                swap(arr, i, pIndex);
                pIndex += 1;
            }
        }

        // Place pivot in its correct sorted position
        swap(arr, pIndex, end);

        return pIndex;
    }

    // Swaps two elements in the array
    private void swap(int[] arr, int a, int b) {
        int temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {7, 4, 5, 8, 1, 2, 0, 9};
        QuickSort driver = new QuickSort();
        driver.quickSort(arr, 0, arr.length - 1);
        for (int c : arr) {
            System.out.print(c + " ");
        }
    }
}

/*
---------------------- OVERALL TIME & SPACE COMPLEXITY ----------------------

Overall Time Complexity:
    • Average Case: O(n log n)
    • Worst Case:   O(n²)
      (Occurs when pivot selection repeatedly gives highly unbalanced partitions)

Overall Space Complexity:
    • O(log n) on average due to recursive call stack
    • O(n) in worst case (highly unbalanced recursion depth)

------------------------------------------------------------------------
*/
