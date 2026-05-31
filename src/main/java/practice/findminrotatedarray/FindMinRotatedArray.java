// LC#153: Find Minimum in Rotated Sorted Array

package practice.findminrotatedarray;

public class FindMinRotatedArray {

    // Binary search to find the minimum element in a rotated sorted array
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        // Binary search: narrow the window until left == right (the minimum)
        while (left < right) {
            int mid = left + (right - left) / 2;

            // If mid > right, the min must be in the right half (rotation point is to the right)
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                // Mid could be the min, so include it in the search window
                right = mid;
            }
        }

        // left == right, pointing to the smallest element
        return nums[left];
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(log n)
        • Worst Case: O(log n)

        Explanation:
        Each iteration halves the search space by comparing the midpoint to the
        rightmost element, resulting in logarithmic time regardless of rotation.

    Space Complexity:
        • O(1)

        Explanation:
        Only a constant number of variables (left, right, mid) are used.

    ------------------------------------------------------------------------ */

    public static void main(String[] args) {
        // Test case 1: Standard rotation
        int[] nums = {3, 4, 5, 1, 2};

        FindMinRotatedArray driver = new FindMinRotatedArray();
        int result = driver.findMin(nums);
        System.out.println("Result: " + result); // Expected: 1

        // Test case 2: Rotated to original position (no rotation)
        // int[] nums2 = {1, 2, 3, 4, 5};
        // int result2 = driver.findMin(nums2);
        // System.out.println("Result: " + result2); // Expected: 1

        // Test case 3: Two elements
        // int[] nums3 = {2, 1};
        // int result3 = driver.findMin(nums3);
        // System.out.println("Result: " + result3); // Expected: 1

        // Test case 4: Single element
        // int[] nums4 = {1};
        // int result4 = driver.findMin(nums4);
        // System.out.println("Result: " + result4); // Expected: 1
    }
}
