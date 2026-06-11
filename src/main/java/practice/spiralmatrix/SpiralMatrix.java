// LC#54: Spiral Matrix

package practice.spiralmatrix;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    // Shrink boundaries layer by layer: traverse right, down, left, up, then tighten
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            // Traverse right along the top row
            for (int col = left; col <= right; col++) {
                result.add(matrix[top][col]);
            }
            top++;

            // Traverse down along the right column
            for (int row = top; row <= bottom; row++) {
                result.add(matrix[row][right]);
            }
            right--;

            // Traverse left along the bottom row (if rows remain)
            if (top <= bottom) {
                for (int col = right; col >= left; col--) {
                    result.add(matrix[bottom][col]);
                }
                bottom--;
            }

            // Traverse up along the left column (if columns remain)
            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    result.add(matrix[row][left]);
                }
                left++;
            }
        }

        return result;
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(M * N)
        • Worst Case: O(M * N)

        Explanation:
        Every element in the M×N matrix is visited exactly once during the
        spiral traversal, with O(1) work per element.

    Space Complexity:
        • O(1) (excluding output)

        Explanation:
        Only four boundary variables are used. The output list holds M*N elements
        but is required by the problem and not counted as auxiliary space.

    ------------------------------------------------------------------------ */

    public static void main(String[] args) {
        // Test case 1: 3x3 matrix
        int[][] matrix1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        SpiralMatrix driver = new SpiralMatrix();
        List<Integer> result1 = driver.spiralOrder(matrix1);
        System.out.println("Result: " + result1);
        // Expected: [1, 2, 3, 6, 9, 8, 7, 4, 5]

        // Test case 2: Non-square matrix (3x4)
        // int[][] matrix2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        // List<Integer> result2 = driver.spiralOrder(matrix2);
        // System.out.println("Result: " + result2);
        // Expected: [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]

        // Test case 3: Single row
        // int[][] matrix3 = {{1, 2, 3, 4}};
        // List<Integer> result3 = driver.spiralOrder(matrix3);
        // System.out.println("Result: " + result3);
        // Expected: [1, 2, 3, 4]
    }
}
