public class MaximumSubarraySum {

    // Classic Kadane's Algorithm (handles negatives correctly)
    public int maximumSubarraySum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0; // return 0 for null or empty array
        }

        int maxSum = nums[0];     // tracks best sum found so far
        int currentSum = nums[0]; // tracks best sum ending at current index

        for (int num : nums) {
            // either extend previous subarray or start a new one at this number
            currentSum = Math.max(currentSum + num, num);
            // update global maximum
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    // DP version: dp[i] holds the maximum subarray sum ending at index i
    public int maximumSubarraySumDP(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0; // return 0 for null or empty array
        }

        int n = nums.length;
        int[] dp = new int[n];             // dp array to store best sums at each index
        dp[0] = nums[0];                    // base case: first element forms the first subarray
        int maxSum = dp[0];                 // initialize global best sum

        for (int i = 1; i < n; i++) {
            // extend previous subarray or start new
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            // update global maximum
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = {-2, -3, 4, -1, -2, 1, 5, -3};

        MaximumSubarraySum driver = new MaximumSubarraySum();
        System.out.println(driver.maximumSubarraySum(nums));
        System.out.println(driver.maximumSubarraySumDP(nums));
    }
}

/* ---------------------- TIME & SPACE COMPLEXITY ----------------------

Time Complexity:
    • Average Case: O(n)
    • Worst Case: O(n)

    Explanation:
    Both methods scan the entire array once. Each iteration performs constant-time
    operations, so the total running time increases linearly with array size.

Space Complexity:
    • O(n)

    Explanation:
    The DP implementation allocates an auxiliary dp[] array proportional to the
    input array size, resulting in linear additional space usage.

------------------------------------------------------------------------ */
