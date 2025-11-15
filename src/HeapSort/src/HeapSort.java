import java.util.Arrays;

public class HeapSort {

    // Swap elements at indices a and b
    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    // Ensure the subtree rooted at index i is a max-heap for array[0..n-1]
    // n: size of the heap portion to consider (elements at index >= n are considered sorted)
    // i: current root index to heapify
    private void heapify(int[] array, int n, int i) {
        int largest = i;                   // Assume root is largest
        int left = 2 * i + 1;              // left child index
        int right = 2 * i + 2;             // right child index

        // If left child exists and is greater than current largest, update largest
        if (left < n && array[left] > array[largest]) {
            largest = left;
        }

        // If right child exists and is greater than current largest, update largest
        if (right < n && array[right] > array[largest]) {
            largest = right;
        }

        // If largest is not root, swap and continue heapifying affected subtree
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

        // Build max-heap: start from last internal node and heapify downwards
        for (int i = n / 2 - 1; i >= 0; i--) { // last internal node = n/2 - 1
            heapify(array, n, i);
        }

        // Extract elements from heap one by one
        for (int i = n - 1; i >= 0; i--) {
            // Move current largest (root) to the end (sorted position)
            swap(array, 0, i);
            // Restore max-heap property on the reduced heap
            heapify(array, i, 0); // heap size is now i
        }
    }

    public static void main(String[] args) {
        int[] array = {5, 1, 4, 2, 3, 6};
        new HeapSort().heapSort(array);
        Arrays.stream(array).mapToObj(i -> i + " ").forEach(System.out::print);
    }
}
