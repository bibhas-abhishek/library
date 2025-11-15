import java.util.Random;

public class KLargestNumber {

    // Return the k-th largest element using Quickselect.
    public int kthLargestQuickselect(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    // Quickselect finds the (size - k)th the smallest element, which is the k-th largest.
    public int quickSelect(int[] nums, int left, int right, int k) {
        int size = nums.length;

        if (left >= right) {
            return nums[left];
        }

        int targetIndex = size - k;

        // Select a random pivot and move it to the end.
        Random rand = new Random();
        int randomIndex = rand.nextInt(right - left + 1) + left;

        int temp = nums[randomIndex];
        nums[randomIndex] = nums[right];
        nums[right] = temp;

        // Partition and obtain final pivot position.
        int pivotIndex = partition(nums, left, right);

        if (pivotIndex < targetIndex) {
            return quickSelect(nums, pivotIndex + 1, right, k);
        } else if (pivotIndex > targetIndex) {
            return quickSelect(nums, left, pivotIndex - 1, k);
        } else {
            return nums[pivotIndex];
        }
    }

    // Partition using Lomuto scheme, placing smaller elements before the pivot.
    public int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int pIndex = left;   // pIndex marks where the next smaller element goes.

        for (int i = left; i < right; i++) {
            if (nums[i] < pivot) {
                int temp = nums[pIndex];
                nums[pIndex] = nums[i];
                nums[i] = temp;
                pIndex++;
            }
        }

        int temp = nums[pIndex];
        nums[pIndex] = nums[right];
        nums[right] = temp;

        return pIndex;
    }

    public static void main(String[] args) {
        int[] nums = {7, 10, 4, 3, 20, 15};
        int k = 2;

        KLargestNumber driver = new KLargestNumber();
        int result = driver.kthLargestQuickselect(nums, k);
        System.out.println(result);
    }

    /*
     * Overall Algorithm Complexity:
     *
     * Time Complexity:
     *   - Average: O(n)
     *   - Worst case: O(n^2)
     *
     * Space Complexity:
     *   - O(1) extra space (in-place), ignoring recursion stack.
     *
     * Notes:
     *   - Quickselect partitions the array repeatedly until the k-th largest is found.
     *   - Random pivot selection keeps the expected running time linear.
     */
}
