package practice.connectedistands;

public class ConnectedIslands {

    // Directions array for traversing up, down, left, right neighbors
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int numIslands(char[][] grid) {
        // Guard against empty input
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int islandCount = 0;

        // Scan every cell; when land is found, a new island is discovered
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    islandCount++;
                    // Sink the entire connected land mass so it isn't counted again
                    dfs(grid, r, c, rows, cols);
                }
            }
        }

        return islandCount;
    }

    private void dfs(char[][] grid, int r, int c, int rows, int cols) {
        // Base case: out of bounds or already water/visited — stop recursion
        if (r < 0 || r >= rows || c < 0 || c >= cols || grid[r][c] != '1') {
            return;
        }

        // Mark cell as visited by sinking it (mutate '1' → '0' in-place to avoid a visited array)
        grid[r][c] = '0';

        // Recurse into all 4 neighbors to sink the full connected component
        for (int[] dir : DIRECTIONS) {
            dfs(grid, r + dir[0], c + dir[1], rows, cols);
        }
    }

    public static void main(String[] args) {
        char[][] grid = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };
        ConnectedIslands driver = new ConnectedIslands();
        int result = driver.numIslands(grid);
        System.out.println("Number of Islands: " + result);
    }
}

/* ---------------------- TIME & SPACE COMPLEXITY ----------------------

Time Complexity:
    • Average Case: O(M × N)
    • Worst Case:   O(M × N)

    Explanation:
    Every cell in the M × N grid is visited at most once — the outer loop
    touches each cell, and DFS only re-enters cells that are still '1',
    which are immediately sunk to '0', preventing revisits.

Space Complexity:
    • O(M × N)

    Explanation:
    In the worst case (the entire grid is one island), the DFS call stack
    reaches a depth of M × N recursive frames before unwinding.

------------------------------------------------------------------------ */
