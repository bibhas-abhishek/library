// LC#51: N-Queens

package practice.nqueens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NQueens {

    // Backtracking: place queens row by row, track conflicts via sets, build board on success
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        // board[row] = column index where the queen is placed in that row
        int[] queens = new int[n];
        Arrays.fill(queens, -1);

        // Track occupied columns and diagonals for O(1) conflict detection
        Set<Integer> columns = new HashSet<>();
        Set<Integer> mainDiagonals = new HashSet<>(); // row - col constant along \ diagonals
        Set<Integer> antiDiagonals = new HashSet<>(); // row + col constant along / diagonals

        backtrack(0, n, queens, columns, mainDiagonals, antiDiagonals, results);
        return results;
    }

    private void backtrack(
            int row,
            int n,
            int[] queens,
            Set<Integer> columns,
            Set<Integer> mainDiagonals,
            Set<Integer> antiDiagonals,
            List<List<String>> results) {
        // All queens placed successfully — convert queen positions to board representation
        if (row == n) {
            results.add(buildBoard(queens, n));
            return;
        }

        // Try placing a queen in each column of the current row
        for (int col = 0; col < n; col++) {
            int mainDiag = row - col;
            int antiDiag = row + col;

            // Skip if column or either diagonal is already under attack
            if (columns.contains(col)
                    || mainDiagonals.contains(mainDiag)
                    || antiDiagonals.contains(antiDiag)) {
                continue;
            }

            // Place queen: record position and mark conflicts
            queens[row] = col;
            columns.add(col);
            mainDiagonals.add(mainDiag);
            antiDiagonals.add(antiDiag);

            // Recurse to next row
            backtrack(row + 1, n, queens, columns, mainDiagonals, antiDiagonals, results);

            // Backtrack: remove queen and free column/diagonals
            queens[row] = -1;
            columns.remove(col);
            mainDiagonals.remove(mainDiag);
            antiDiagonals.remove(antiDiag);
        }
    }

    // Converts queen column positions into the required List<String> board format
    private List<String> buildBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int row = 0; row < n; row++) {
            char[] rowChars = new char[n];
            Arrays.fill(rowChars, '.');
            rowChars[queens[row]] = 'Q';
            board.add(new String(rowChars));
        }
        return board;
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(N!)
        • Worst Case: O(N!)

        Explanation:
        We try at most N columns in row 0, at most N-1 in row 1 (one column blocked),
        and so on. Each valid solution also takes O(N^2) to build the board string, but
        this is dominated by the branching factor.

    Space Complexity:
        • O(N^2)

        Explanation:
        The recursion stack is O(N) deep, the three conflict sets hold O(N) entries each,
        and storing all valid board configurations takes O(N^2) per solution.

    ------------------------------------------------------------------------ */

    public static void main(String[] args) {
        // Test case 1: Classic 4-queens problem
        int n1 = 4;

        NQueens driver = new NQueens();
        List<List<String>> result1 = driver.solveNQueens(n1);
        System.out.println("Result (" + result1.size() + " solutions):");
        for (List<String> board : result1) {
            for (String row : board) {
                System.out.println(row);
            }
            System.out.println();
        }
        // Expected: 2 solutions
        // .Q..    ..Q.
        // ...Q    Q...
        // Q...    ...Q
        // ..Q.    .Q..

        // Test case 2: Single queen on 1x1 board
        // int n2 = 1;
        // List<List<String>> result2 = driver.solveNQueens(n2);
        // System.out.println("Result: " + result2); // Expected: [["Q"]]

        // Test case 3: No solution exists for 2x2
        // int n3 = 2;
        // List<List<String>> result3 = driver.solveNQueens(n3);
        // System.out.println("Result: " + result3); // Expected: [] (empty)
    }
}
