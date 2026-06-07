// LC#72: Edit Distance

package practice.editdistance;

public class EditDistance {

    // Bottom-up DP: build table of min operations to convert word1[0..i] to word2[0..j]
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // dp[i][j] = min operations to convert word1[0..i-1] into word2[0..j-1]
        int[][] dp = new int[m + 1][n + 1];

        // Base case: converting word1[0..i-1] to empty string requires i deletions
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        // Base case: converting empty string to word2[0..j-1] requires j insertions
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // Fill table: for each pair (i, j), choose the cheapest operation
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // Characters match — no operation needed, carry forward diagonal value
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Take minimum of three operations, each costs 1:
                    // Replace: dp[i-1][j-1] — change word1[i] to word2[j], both advance
                    // Delete:  dp[i-1][j]   — remove word1[i], word2 stays
                    // Insert:  dp[i][j-1]   — add word2[j] to word1, word1 stays
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }

        return dp[m][n];
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(M * N)
        • Worst Case: O(M * N)

        Explanation:
        The nested loops iterate over all M * N subproblem pairs, with O(1) work
        per cell (one comparison and a min of three values).

    Space Complexity:
        • O(M * N)

        Explanation:
        The 2D DP table stores (M+1) * (N+1) entries. Can be optimized to O(min(M, N))
        using a single rolling row since each cell only depends on the current and previous row.

    ------------------------------------------------------------------------ */

    public static void main(String[] args) {
        // Test case 1: Standard transformation
        String word1 = "horse";
        String word2 = "ros";

        EditDistance driver = new EditDistance();
        int result1 = driver.minDistance(word1, word2);
        System.out.println("Result: " + result1); // Expected: 3

        // Test case 2: Longer transformation
        // String w1 = "intention";
        // String w2 = "execution";
        // int result2 = driver.minDistance(w1, w2);
        // System.out.println("Result: " + result2); // Expected: 5

        // Test case 3: One empty string
        // String w3 = "";
        // String w4 = "abc";
        // int result3 = driver.minDistance(w3, w4);
        // System.out.println("Result: " + result3); // Expected: 3
    }
}
