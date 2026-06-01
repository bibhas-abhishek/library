// LC#167: Two Sum II - Input Array Is Sorted

package practice.twosumii;

class TwoSumII {

    // Two-pointer approach leveraging sorted order to find the unique pair summing to target
    public int[] twoSum(int[] numbers, int target) {
        // Initialize pointers at both ends of the sorted array
        int left = 0;
        int right = numbers.length - 1;

        // Narrow the window until the pair is found
        while (left < right) {
            int sum = numbers[left] + numbers[right];

            if (sum == target) {
                // Return 1-indexed positions as required by the problem
                return new int[] {left + 1, right + 1};
            } else if (sum < target) {
                // Sum too small — move left pointer right to increase sum
                left++;
            } else {
                // Sum too large — move right pointer left to decrease sum
                right--;
            }
        }

        // Problem guarantees exactly one solution, so this is unreachable
        return new int[] {-1, -1};
    }

    public static void main(String[] args) {
        // Test case 1: Normal input
        int[] numbers = {2, 7, 11, 15};
        int target = 9;

        TwoSumII driver = new TwoSumII();
        int[] result = driver.twoSum(numbers, target);
        System.out.println("Result: [" + result[0] + ", " + result[1] + "]"); // Expected: [1, 2]

        // Test case 2: Target formed by middle elements
        // int[] numbers2 = {2, 3, 4};
        // int target2 = 6;
        // int[] result2 = driver.twoSum(numbers2, target2);
        // System.out.println("Result: [" + result2[0] + ", " + result2[1] + "]"); // Expected: [1,
        // 3]

        // Test case 3: Negative numbers
        // int[] numbers3 = {-1, 0};
        // int target3 = -1;
        // int[] result3 = driver.twoSum(numbers3, target3);
        // System.out.println("Result: [" + result3[0] + ", " + result3[1] + "]"); // Expected: [1,
        // 2]
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(n)
        • Worst Case: O(n)

        Explanation:
        Each iteration moves at least one pointer inward, so at most n iterations
        occur where n is the length of the input array.

    Space Complexity:
        • O(1)

        Explanation:
        Only a constant number of variables (two pointers and a sum) are used
        regardless of input size.

    ------------------------------------------------------------------------ */
}
