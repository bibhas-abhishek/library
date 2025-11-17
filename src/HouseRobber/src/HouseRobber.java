public class HouseRobber {

    public int rob(int[] arr) {
        if (arr.length == 0) {
            return 0; // no houses to rob
        } else if (arr.length == 1) {
            return arr[0]; // only one house, rob it
        }

        int[] dp = new int[arr.length]; // dp[i] stores max money that can be robbed up to index i
        dp[0] = arr[0]; // first house value
        dp[1] = Math.max(arr[0], arr[1]); // best of robbing first or second house

        for (int i = 2; i < arr.length; i++) {
            // choose max of:
            // (rob current + dp[i-2]) or (skip current and take dp[i-1])
            dp[i] = Math.max(arr[i] + dp[i - 2], dp[i - 1]);
        }

        return dp[arr.length - 1]; // final maximum amount
    }

    public static void main(String[] args) {
        int[] arr = {2, 7, 9, 3, 1};
        HouseRobber driver = new HouseRobber();
        System.out.println(driver.rob(arr));
    }
}

/* ---------------------- TIME & SPACE COMPLEXITY ----------------------

Time Complexity:
    • Average Case: O(n)
    • Worst Case:  O(n)

    Explanation:
    The loop processes each house exactly once, and each iteration does only
    constant-time comparisons, giving linear time overall.

Space Complexity:
    • O(n)

    Explanation:
    The dp array stores one computed value per house, requiring linear
    auxiliary space in proportion to the input size.

------------------------------------------------------------------------ */
