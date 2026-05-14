package practice.bubblesort;

public class BubbleSort {

    // Method to perform bubble sort on an integer array
    static void bubbleSort(int[] arr) {
        int n = arr.length;

        // Outer loop controls number of passes
        for (int i = 0; i < n - 1; i++) {

            // Flag to check if any swap happens in this pass
            boolean swapped = false;

            // Inner loop compares adjacent elements
            for (int j = 0; j < n - 1 - i; j++) {

                // Swap if elements are in wrong order
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swapped = true; // mark that a swap occurred
                }
            }

            // If no swaps occurred, array is already sorted
            if (!swapped) {
                break;
            }
        }
    }

    // Method to print the array
    static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {5, 1, 4, 2, 8};

        System.out.println("Original array:");
        printArray(arr);

        bubbleSort(arr);

        System.out.println("Sorted array:");
        printArray(arr);
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(n^2)
        • Worst Case: O(n^2)

        Explanation:
        Bubble sort repeatedly compares adjacent elements in nested loops,
        leading to quadratic number of comparisons and swaps.

    Space Complexity:
        • O(1)

        Explanation:
        Sorting is done in-place without using extra memory apart from a few variables.

    ------------------------------------------------------------------------ */
}
