// LC#79: Word Search

package practice.wordsearch;

public class WordSearch {

    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;

        // Try starting DFS from every cell that matches the first character
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (dfs(board, word, r, c, 0, rows, cols)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, String word, int r, int c, int index, int rows, int cols) {
        // Base case: all characters matched
        if (index == word.length()) {
            return true;
        }

        // Boundary check and character mismatch
        if (r < 0 || r >= rows || c < 0 || c >= cols || board[r][c] != word.charAt(index)) {
            return false;
        }

        // Mark cell as visited by replacing with '#' to avoid revisiting in current path
        char original = board[r][c];
        board[r][c] = '#';

        // Explore all 4 directions for the next character
        for (int[] dir : DIRECTIONS) {
            if (dfs(board, word, r + dir[0], c + dir[1], index + 1, rows, cols)) {
                return true;
            }
        }

        // Backtrack: restore original character since this path didn't work
        board[r][c] = original;
        return false;
    }

    public static void main(String[] args) {
        WordSearch driver = new WordSearch();

        // Test case 1: Word exists via adjacent path
        char[][] board = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        };
        System.out.println("Result: " + driver.exist(board, "ABCCED")); // Expected: true

        // Test case 2: Word does not exist
        // char[][] board2 = {
        //     {'A', 'B', 'C', 'E'},
        //     {'S', 'F', 'C', 'S'},
        //     {'A', 'D', 'E', 'E'}
        // };
        // System.out.println("Result: " + driver.exist(board2, "ABCB")); // Expected: false
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(m * n * 4^L)
        • Worst Case:   O(m * n * 4^L)

        Explanation:
        For each of the m*n cells, we potentially explore a DFS tree of depth L
        (word length) with up to 4 branches at each level (minus the direction
        we came from). Pruning via character mismatch reduces this significantly
        in practice.

    Space Complexity:
        • O(L)

        Explanation:
        The recursion stack goes at most L levels deep (one per character in the
        word). No additional data structures are used since visited cells are
        marked in-place.

    ------------------------------------------------------------------------ */
}
