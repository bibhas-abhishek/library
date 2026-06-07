// LC#162: Find Peak Element

package practice.findpeakelement;

class FindPeakElement {

    // Binary search exploiting the guarantee that nums[-1] = nums[n] = -infinity
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        // Maintain invariant: a peak always exists within [left, right]
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[mid + 1]) {
                // Descending slope — peak lies at mid or to the left
                right = mid;
            } else {
                // Ascending slope — peak must lie strictly to the right
                left = mid + 1;
            }
        }

        // Convergence point is guaranteed to be a peak
        return left;
    }

    public static void main(String[] args) {
        // Test case 1: Peak in the middle
        int[] nums = {1, 2, 1, 3, 5, 6, 4};

        FindPeakElement driver = new FindPeakElement();
        int result = driver.findPeakElement(nums);
        System.out.println("Result: " + result); // Expected: 2

        // Test case 2: Multiple peaks — any valid index accepted
        // int[] nums2 = {1, 2, 1, 3, 5, 6, 4};
        // int result2 = driver.findPeakElement(nums2);
        // System.out.println("Result: " + result2); // Expected: 1 or 5

        // Test case 3: Single element
        // int[] nums3 = {1};
        // int result3 = driver.findPeakElement(nums3);
        // System.out.println("Result: " + result3); // Expected: 0
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(log n)
        • Worst Case: O(log n)

        Explanation:
        Each iteration halves the search space via binary search, so at most
        log2(n) iterations are performed where n is the array length.

    Space Complexity:
        • O(1)

        Explanation:
        Only a constant number of variables (left, right, mid) are used
        regardless of input size.

    ------------------------------------------------------------------------ */
}
