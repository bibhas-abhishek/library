package practice.search2dmatrix;

public class Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        // Treat the m×n matrix as a flat sorted array of length m*n
        int m = matrix.length;
        int n = matrix[0].length;

        int lo = 0;
        int hi = m * n - 1; // last valid virtual index

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            // Map 1D virtual index back to 2D coordinates
            int row = mid / n; // which row the mid index falls in
            int col = mid % n; // which column within that row

            int val = matrix[row][col];

            if (val == target) {
                return true;
            } else if (val < target) {
                lo = mid + 1; // target is in the right half
            } else {
                hi = mid - 1; // target is in the left half
            }
        }

        return false; // target not found
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(log(m * n))
        • Worst Case:   O(log(m * n))

        Explanation:
        Binary search runs over a virtual 1D array of size m*n, halving the
        search space each iteration — identical to standard binary search on
        a sorted array of that length.

    Space Complexity:
        • O(1)

        Explanation:
        Only a constant number of variables (lo, hi, mid, row, col) are used;
        no auxiliary data structures are allocated.

    ------------------------------------------------------------------------ */

    public static void main(String[] args) {
        // Test case 1: target present in the matrix
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int target = 3;

        Search2DMatrix driver = new Search2DMatrix();
        boolean result = driver.searchMatrix(matrix, target);
        System.out.println("Result: " + result); // Expected: true

        // Test case 2: target absent from the matrix
        // int[][] matrix2 = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        // int target2 = 13;
        // boolean result2 = driver.searchMatrix(matrix2, target2);
        // System.out.println("Result: " + result2); // Expected: false
    }
}
