import java.util.Arrays;

public class HeapSort {

    // Swap elements at indices a and b
    // Simple utility to exchange two elements in the array.
    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    // Ensure the subtree rooted at index i is a max-heap for array[0..n-1]
    // n: size of the heap portion to consider (elements at index >= n are considered sorted)
    // i: current root index to heapify
    private void heapify(int[] array, int n, int i) {
        // Start by assuming the root (i) is the largest
        int largest = i;

        // Compute indices of left and right children
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // If left child exists within heap and is greater than current largest,
        // update largest to be the left child.
        if (left < n && array[left] > array[largest]) {
            largest = left;
        }

        // If right child exists within heap and is greater than current largest,
        // update largest to be the right child.
        if (right < n && array[right] > array[largest]) {
            largest = right;
        }

        // If the largest element is not the root, swap root with largest
        // and continue heapifying the affected subtree to restore heap property.
        if (largest != i) {
            swap(array, i, largest);
            heapify(array, n, largest);
        }
    }

    // In-place heapsort:
    // 1) Build a max-heap from the array.
    // 2) Repeatedly swap the heap root (max element) with the last element
    //    of the heap, reduce heap size by one, and restore heap property.
    public void heapSort(int[] array) {
        int n = array.length;

        // Build max-heap:
        // Start from the last internal node (n/2 - 1) and heapify each node downwards.
        for (int i = n / 2 - 1; i >= 0; i--) {
            // Heapify subtree rooted at i to ensure max-heap property for array[0..n-1]
            heapify(array, n, i);
        }

        // Extract elements from heap one by one (move current max to sorted region)
        for (int i = n - 1; i >= 0; i--) {
            // Move current largest (root of the heap) to the end of the array.
            swap(array, 0, i);

            // After moving the max element, the heap size is reduced by one (i).
            // Restore max-heap property for the reduced heap.
            heapify(array, i, 0);
        }
    }

    public static void main(String[] args) {
        int[] array = {5, 1, 4, 2, 3, 6};
        HeapSort driver = new HeapSort();
        driver.heapSort(array);
        Arrays.stream(array).mapToObj(i -> i + " ").forEach(System.out::print);
    }
}
