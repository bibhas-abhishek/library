import java.util.Stack;


public class LongestCommonSubsequence {

    // Compute length table for LCS using dynamic programming.
    // lcs[i][j] will hold the length of LCS for s1[0..i-1] and s2[0..j-1].
    public int longestCommonSubsequence(String s1, String s2) {
        int[][] lcs = new int[s1.length() + 1][s2.length() + 1];
        int row = 0, col = 0;
        for (row = 1; row <= s1.length(); row++) {
            for (col = 1; col <= s2.length(); col++) {
                // If characters match, extend the previous diagonal LCS by 1.
                if (s1.charAt(row - 1) == s2.charAt(col - 1)) {
                    lcs[row][col] = lcs[row - 1][col - 1] + 1;
                }
                // Otherwise take the maximum LCS possible by skipping one char from either string.
                else {
                    lcs[row][col] = Math.max(lcs[row - 1][col], lcs[row][col - 1]);
                }
            }
        }
        // Print the DP matrix (for debugging/visualization).
        // printLCSMatrix(lcs);
        // Reconstruct and print one LCS using the completed table.
        // printLCSequence(lcs, s1, s2);
        // Return the LCS length stored in the bottom-right cell.
        return lcs[row - 1][col - 1];
    }

    // Walk backwards through the DP table to reconstruct one LCS.
    // Uses a stack to reverse the sequence found during backtracking.
    public void printLCSequence(int[][] lcs, String s1, String s2) {
        int row = lcs.length - 1;
        int col = lcs[0].length - 1;
        Stack<Character> stack = new Stack<>();
        // Backtrack until we reach the top-left of the DP table.
        while (row > 0 && col > 0) {
            // If current cell came from a matching diagonal, that character is part of LCS.
            if (lcs[row][col] == lcs[row - 1][col - 1] + 1 && s1.charAt(row - 1) == s2.charAt(col - 1)) {
                stack.push(s1.charAt(row - 1));
                row--;
                col--;
            }
            // If current value equals left cell, move left (skip char in s2).
            else if (lcs[row][col] == lcs[row][col - 1]) {
                col--;
            }
            // Otherwise move up (skip char in s1).
            else if (lcs[row][col] == lcs[row - 1][col]) {
                row--;
            }
        }

        // Pop stack to print LCS in correct order.
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }

    // Print the DP matrix values row by row.
    private static void printLCSMatrix(int[][] lcs) {
        for (int i = 0; i < lcs.length; i++) {
            for (int j = 0; j < lcs[0].length; j++) {
                System.out.print(lcs[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        LongestCommonSubsequence driver = new LongestCommonSubsequence();
        int result = driver.longestCommonSubsequence("ABDCGHCD", "ABGD");
        System.out.println(result);
    }
}

/* ---------------------- TIME & SPACE COMPLEXITY ----------------------

Time Complexity:
    • Average Case: O(m * n)
    • Worst Case:  O(m * n)

    Explanation:
    The algorithm fills an (m+1) by (n+1) DP table once, performing O(1) work per cell,
    so total time is proportional to the number of cells, m * n (where m = s1.length(), n = s2.length()).

Space Complexity:
    • O(m * n)

    Explanation:
    The algorithm stores the full DP table of size (m+1) × (n+1). Additional space for the stack
    during backtracking is at most O(min(m, n)), which is dominated by the O(m * n) table.

------------------------------------------------------------------------ */
