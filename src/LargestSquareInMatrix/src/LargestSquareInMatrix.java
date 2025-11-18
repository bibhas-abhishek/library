public class LargestSquareInMatrix {

    /**
     * Compute the side length of the largest square containing only 1s.
     * Uses dynamic programming where dp[i][j] stores the side length of the largest
     * square whose bottom-right corner is at matrix[i-1][j-1].
     */
    public static int largestSquare(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        // dp has one extra row and column to avoid boundary checks
        int[][] dp = new int[m + 1][n + 1];
        int maxSide = 0;

        // fill dp table row by row
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // only consider if the original cell is 1
                if (matrix[i - 1][j - 1] == 1) {
                    // current cell contributes to square limited by left, top and top-left
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                            Math.min(dp[i - 1][j], dp[i][j - 1]));
                    if (dp[i][j] > maxSide) {
                        maxSide = dp[i][j];
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        return maxSide;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 0, 1, 0, 0},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0}
        };

        int maxSide = largestSquare(matrix);
        System.out.println("Largest square side length: " + maxSide);
        System.out.println("Largest square area: " + (maxSide * maxSide));
    }
}

/* ---------------------- TIME & SPACE COMPLEXITY ----------------------

Time Complexity:
    • Average Case: O(m * n)
    • Worst Case: O(m * n)

    Explanation:
    We visit each cell once and do O(1) work per cell computing the dp value,
    so the runtime is proportional to the number of cells (m rows × n columns).

Space Complexity:
    • O(m * n)

    Explanation:
    We allocate a dp table of size (m+1) × (n+1) which is Θ(m * n) extra space.

------------------------------------------------------------------------ */
