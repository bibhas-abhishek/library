// LC#33: Search in Rotated Sorted Array

package practice.searchrotatedarray;

public class SearchRotatedArray {

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // Determine which half is sorted by comparing mid with left boundary
            if (nums[left] <= nums[mid]) {
                // Left half [left..mid] is sorted
                if (target >= nums[left] && target < nums[mid]) {
                    // Target lies within sorted left half — narrow right
                    right = mid - 1;
                } else {
                    // Target must be in unsorted right half
                    left = mid + 1;
                }
            } else {
                // Right half [mid..right] is sorted
                if (target > nums[mid] && target <= nums[right]) {
                    // Target lies within sorted right half — narrow left
                    left = mid + 1;
                } else {
                    // Target must be in unsorted left half
                    right = mid - 1;
                }
            }
        }

        // Target not found
        return -1;
    }

    public static void main(String[] args) {
        SearchRotatedArray driver = new SearchRotatedArray();

        // Test case 1: Target in rotated portion
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        System.out.println("Result: " + driver.search(nums, target)); // Expected: 4

        // Test case 2: Target not present
        // int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        // int target2 = 3;
        // System.out.println("Result: " + driver.search(nums2, target2)); // Expected: -1

        // Test case 3: Single element
        // int[] nums3 = {1};
        // int target3 = 0;
        // System.out.println("Result: " + driver.search(nums3, target3)); // Expected: -1
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(log n)
        • Worst Case:   O(log n)

        Explanation:
        Each iteration eliminates half the search space by determining which
        half is sorted and whether the target falls within that sorted range.

    Space Complexity:
        • O(1)

        Explanation:
        Only a constant number of pointer variables (left, right, mid) are used.

    ------------------------------------------------------------------------ */
}
