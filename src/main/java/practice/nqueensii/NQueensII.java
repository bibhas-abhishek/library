// LC#52: N-Queens II

package practice.nqueensii;

import java.util.HashSet;
import java.util.Set;

public class NQueensII {

    private int count;

    // Backtracking: place queens row by row, pruning via column and diagonal conflict sets
    public int totalNQueens(int n) {
        count = 0;
        // Track occupied columns and diagonals to detect conflicts in O(1)
        Set<Integer> columns = new HashSet<>();
        Set<Integer> mainDiagonals = new HashSet<>(); // row - col is constant along \ diagonals
        Set<Integer> antiDiagonals = new HashSet<>(); // row + col is constant along / diagonals

        backtrack(0, n, columns, mainDiagonals, antiDiagonals);
        return count;
    }

    private void backtrack(
            int row,
            int n,
            Set<Integer> columns,
            Set<Integer> mainDiagonals,
            Set<Integer> antiDiagonals) {
        // All queens placed successfully — found a valid arrangement
        if (row == n) {
            count++;
            return;
        }

        // Try placing a queen in each column of the current row
        for (int col = 0; col < n; col++) {
            int mainDiag = row - col;
            int antiDiag = row + col;

            // Skip if this column or either diagonal is already under attack
            if (columns.contains(col)
                    || mainDiagonals.contains(mainDiag)
                    || antiDiagonals.contains(antiDiag)) {
                continue;
            }

            // Place queen: mark column and both diagonals as occupied
            columns.add(col);
            mainDiagonals.add(mainDiag);
            antiDiagonals.add(antiDiag);

            // Recurse to next row
            backtrack(row + 1, n, columns, mainDiagonals, antiDiagonals);

            // Backtrack: remove queen and free column/diagonals for next attempt
            columns.remove(col);
            mainDiagonals.remove(mainDiag);
            antiDiagonals.remove(antiDiag);
        }
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(N!)
        • Worst Case: O(N!)

        Explanation:
        In the first row we try N columns, in the second row at most N-1 (one column
        blocked), and so on. The pruning via diagonal checks reduces this further in
        practice, but the upper bound remains O(N!).

    Space Complexity:
        • O(N)

        Explanation:
        The recursion stack goes N levels deep (one per row), and the three sets each
        hold at most N entries, giving O(N) total auxiliary space.

    ------------------------------------------------------------------------ */

    public static void main(String[] args) {
        // Test case 1: Classic 4-queens problem
        int n1 = 4;

        NQueensII driver = new NQueensII();
        int result1 = driver.totalNQueens(n1);
        System.out.println("Result: " + result1); // Expected: 2

        // Test case 2: Single queen on 1x1 board
        // int n2 = 1;
        // int result2 = driver.totalNQueens(n2);
        // System.out.println("Result: " + result2); // Expected: 1

        // Test case 3: 8-queens classic puzzle
        // int n3 = 8;
        // int result3 = driver.totalNQueens(n3);
        // System.out.println("Result: " + result3); // Expected: 92
    }
}
