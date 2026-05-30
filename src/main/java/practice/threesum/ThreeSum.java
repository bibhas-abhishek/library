// LC#15: 3Sum

package practice.threesum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // Sort so duplicates are adjacent and two-pointer technique applies
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicate values for the first element to avoid duplicate triplets
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // Two-pointer sweep for the remaining two elements
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Advance left past duplicates to skip equivalent triplets
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // Retreat right past duplicates similarly
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++; // Sum too small; move left pointer right to increase it
                } else {
                    right--; // Sum too large; move right pointer left to decrease it
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ThreeSum driver = new ThreeSum();

        // Test case 1: Standard input with multiple valid triplets
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.println(driver.threeSum(nums1));
        // Expected: [[-1,-1,2],[-1,0,1]]

        // Test case 2: All zeros — one triplet
        // int[] nums2 = {0, 0, 0};
        // System.out.println(driver.threeSum(nums2));
        // Expected: [[0,0,0]]

        // Test case 3: No valid triplet
        // int[] nums3 = {0, 1, 1};
        // System.out.println(driver.threeSum(nums3));
        // Expected: []
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(n²)
        • Worst Case:   O(n²)

        Explanation:
        Sorting costs O(n log n); the outer loop runs O(n) times and each
        iteration drives a two-pointer pass that is O(n), yielding O(n²) overall,
        which dominates.

    Space Complexity:
        • O(1)

        Explanation:
        The sorting step uses O(log n) stack space (in-place); no auxiliary data
        structures are allocated beyond the output list itself.

    ------------------------------------------------------------------------ */
}
