// LC#63: Unique Paths II

package practice.uniquepathsii;

public class UniquePathsII {

    // DP table approach: count paths avoiding obstacles (1 = obstacle, 0 = free)
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // If start or end is blocked, no valid path exists
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }

        // dp[i][j] = number of unique paths to reach cell (i, j)
        int[][] dp = new int[m][n];

        // Base case: one way to be at the start
        dp[0][0] = 1;

        // Fill first row: path exists only if no obstacle blocks the way from the left
        for (int j = 1; j < n; j++) {
            dp[0][j] = (obstacleGrid[0][j] == 1) ? 0 : dp[0][j - 1];
        }

        // Fill first column: path exists only if no obstacle blocks the way from above
        for (int i = 1; i < m; i++) {
            dp[i][0] = (obstacleGrid[i][0] == 1) ? 0 : dp[i - 1][0];
        }

        // Fill rest: sum of paths from top and left, but 0 if current cell is an obstacle
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(m * n)
        • Worst Case: O(m * n)

        Explanation:
        Every cell in the grid is visited exactly once to compute the number
        of unique paths reaching it.

    Space Complexity:
        • O(m * n)

        Explanation:
        A separate 2D dp table of the same dimensions as the input grid is
        used to store path counts.

    ------------------------------------------------------------------------ */

    public static void main(String[] args) {
        // Test case 1: 3x3 grid with one obstacle in the center
        int[][] grid = {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };

        UniquePathsII driver = new UniquePathsII();
        int result = driver.uniquePathsWithObstacles(grid);
        System.out.println("Result: " + result); // Expected: 2

        // Test case 2: 2x2 grid with obstacle blocking the only alternate path
        // int[][] grid2 = {
        //     {0, 1},
        //     {0, 0}
        // };
        // int result2 = driver.uniquePathsWithObstacles(grid2);
        // System.out.println("Result: " + result2); // Expected: 1

        // Test case 3: Start cell is an obstacle
        // int[][] grid3 = {{1, 0}};
        // int result3 = driver.uniquePathsWithObstacles(grid3);
        // System.out.println("Result: " + result3); // Expected: 0

        // Test case 4: No obstacles, 3x3 grid
        // int[][] grid4 = {
        //     {0, 0, 0},
        //     {0, 0, 0},
        //     {0, 0, 0}
        // };
        // int result4 = driver.uniquePathsWithObstacles(grid4);
        // System.out.println("Result: " + result4); // Expected: 6
    }
}
