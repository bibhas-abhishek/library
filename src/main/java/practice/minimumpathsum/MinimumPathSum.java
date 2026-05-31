// LC#64: Minimum Path Sum

package practice.minimumpathsum;

public class MinimumPathSum {

    // DP table approach: build a separate table to store minimum path sums without modifying input
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Create dp table where dp[i][j] = minimum path sum to reach cell (i, j)
        int[][] dp = new int[m][n];

        // Base case: top-left cell
        dp[0][0] = grid[0][0];

        // Fill first row: can only arrive from the left
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        // Fill first column: can only arrive from above
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        // Fill rest of the table: min of top or left neighbor plus current cell value
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        // Bottom-right cell contains the minimum path sum from top-left
        return dp[m - 1][n - 1];
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(m * n)
        • Worst Case: O(m * n)

        Explanation:
        Every cell in the grid is visited exactly once to compute its minimum
        path sum from the top-left corner.

    Space Complexity:
        • O(m * n)

        Explanation:
        A separate 2D dp table of the same dimensions as the input grid is used
        to store cumulative minimum path sums.

    ------------------------------------------------------------------------ */

    public static void main(String[] args) {
        // Test case 1: 3x3 grid
        int[][] grid = {
            {1, 3, 1},
            {1, 5, 1},
            {4, 2, 1}
        };

        MinimumPathSum driver = new MinimumPathSum();
        int result = driver.minPathSum(grid);
        System.out.println("Result: " + result); // Expected: 7

        // Test case 2: 2x3 grid
        // int[][] grid2 = {
        //     {1, 2, 3},
        //     {4, 5, 6}
        // };
        // int result2 = driver.minPathSum(grid2);
        // System.out.println("Result: " + result2); // Expected: 12

        // Test case 3: Single cell
        // int[][] grid3 = {{5}};
        // int result3 = driver.minPathSum(grid3);
        // System.out.println("Result: " + result3); // Expected: 5
    }
}
