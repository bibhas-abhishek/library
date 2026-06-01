// LC#6: Zigzag Conversion

package practice.zigzagconversion;

public class ZigzagConversion {

    // Simulate the zigzag pattern using a StringBuilder array for each row.
    // Characters are placed row by row following a bouncing direction.
    public String convert(String s, int numRows) {
        // Edge case: if only 1 row or string fits in one column, return as-is
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }

        // Create a StringBuilder for each row to collect characters
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        // Track current row and direction (down or up)
        int currentRow = 0;
        boolean goingDown = false;

        // Iterate through each character, appending to the appropriate row
        for (char c : s.toCharArray()) {
            rows[currentRow].append(c);
            // Reverse direction when hitting the top or bottom row
            if (currentRow == 0 || currentRow == numRows - 1) {
                goingDown = !goingDown;
            }
            // Move to next row based on current direction
            currentRow += goingDown ? 1 : -1;
        }

        // Concatenate all rows to form the final zigzag string
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        // Test case 1: Standard zigzag with 3 rows
        String s = "PAYPALISHIRING";
        int numRows = 3;

        ZigzagConversion driver = new ZigzagConversion();
        String result = driver.convert(s, numRows);
        System.out.println("Result: " + result); // Expected: "PAHNAPLSIIGYIR"

        // Test case 2: 4 rows
        // String s2 = "PAYPALISHIRING";
        // int numRows2 = 4;
        // String result2 = driver.convert(s2, numRows2);
        // System.out.println("Result: " + result2); // Expected: "PINALSIGYAHRPI"

        // Test case 3: Single row (no zigzag)
        // String s3 = "AB";
        // int numRows3 = 1;
        // String result3 = driver.convert(s3, numRows3);
        // System.out.println("Result: " + result3); // Expected: "AB"
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(n)
        • Worst Case: O(n)

        Explanation:
        Single pass through all n characters to distribute them into rows,
        then a linear pass to concatenate all row builders into the result.

    Space Complexity:
        • O(n)

        Explanation:
        The StringBuilder array collectively stores all n characters of the input,
        plus the final result StringBuilder also holds n characters.

    ------------------------------------------------------------------------ */
}
