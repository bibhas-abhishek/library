// LC#12: Integer to Roman

package practice.integertoroman;

public class IntegerToRoman {
    public String intToRoman(int num) {
        // Map values descending so greedy subtraction always picks the largest valid symbol
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();

        // Greedily subtract the largest symbol value that fits, appending its symbol each time
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                sb.append(symbols[i]);
                num -= values[i];
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        IntegerToRoman driver = new IntegerToRoman();

        // Test case 1: Standard multi-symbol number
        System.out.println(driver.intToRoman(3749)); // Expected: "MMMDCCXLIX"

        // Test case 2: Subtractive notation (CM, XC, IV)
        // System.out.println(driver.intToRoman(1994)); // Expected: "MCMXCIV"

        // Test case 3: Power of 10
        // System.out.println(driver.intToRoman(58)); // Expected: "LVIII"
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(1)
        • Worst Case:   O(1)

        Explanation:
        The value and symbol arrays have a fixed size of 13, and num is bounded by 3999,
        so both the outer loop and inner while loop execute at most a constant number of times.

    Space Complexity:
        • O(1)

        Explanation:
        Only the fixed-size lookup arrays and a StringBuilder of bounded output length
        (at most ~15 characters for 3888 = "MMMDCCCLXXXVIII") are used — no input-scaled storage.

    ------------------------------------------------------------------------ */
}
