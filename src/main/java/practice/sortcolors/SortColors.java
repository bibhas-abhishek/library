// LC#75: Sort Colors

package practice.sortcolors;

import java.util.Arrays;

public class SortColors {

    // Dutch National Flag algorithm: three-way partition using low, mid, high pointers
    public void sortColors(int[] nums) {
        int low = 0; // boundary: everything before low is 0
        int mid = 0; // current element being examined
        int high = nums.length - 1; // boundary: everything after high is 2

        while (mid <= high) {
            if (nums[mid] == 0) {
                // Move 0 to the front: swap with low boundary and advance both
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 2) {
                // Move 2 to the back: swap with high boundary and shrink high
                // Don't advance mid — swapped element hasn't been examined yet
                swap(nums, mid, high);
                high--;
            } else {
                // Element is 1 — already in the correct middle zone, just advance
                mid++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(N)
        • Worst Case: O(N)

        Explanation:
        Each iteration either advances mid or shrinks high, and mid never exceeds
        high, so the loop runs at most N times for an array of length N.

    Space Complexity:
        • O(1)

        Explanation:
        Only three pointer variables are used; sorting is done in-place with swaps.

    ------------------------------------------------------------------------ */

    public static void main(String[] args) {
        // Test case 1: Mixed colors
        int[] nums1 = {2, 0, 2, 1, 1, 0};

        SortColors driver = new SortColors();
        driver.sortColors(nums1);
        System.out.println("Result: " + Arrays.toString(nums1));
        // Expected: [0, 0, 1, 1, 2, 2]

        // Test case 2: Already sorted
        // int[] nums2 = {0, 0, 1, 1, 2, 2};
        // driver.sortColors(nums2);
        // System.out.println("Result: " + Arrays.toString(nums2));
        // Expected: [0, 0, 1, 1, 2, 2]

        // Test case 3: All same color
        // int[] nums3 = {2, 2, 2};
        // driver.sortColors(nums3);
        // System.out.println("Result: " + Arrays.toString(nums3));
        // Expected: [2, 2, 2]
    }
}
