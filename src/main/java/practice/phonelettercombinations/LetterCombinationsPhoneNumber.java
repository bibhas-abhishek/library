// LC#17: Letter Combinations of a Phone Number

package practice.phonelettercombinations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class LetterCombinationsPhoneNumber {
    // Map each digit to its corresponding phone keypad letters
    private static final Map<Character, String> PHONE_MAP =
            Map.of(
                    '2', "abc", '3', "def", '4', "ghi", '5', "jkl", '6', "mno", '7', "pqrs", '8',
                    "tuv", '9', "wxyz");

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        // Guard: return empty list for empty input (no valid combinations exist)
        if (digits == null || digits.isEmpty()) {
            return result;
        }

        // Start backtracking from index 0 with an empty current combination
        backtrack(digits, 0, new StringBuilder(), result);
        return result;
    }

    private void backtrack(String digits, int index, StringBuilder current, List<String> result) {
        // Base case: a complete combination is formed when all digits are processed
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        // Retrieve the letters mapped to the current digit
        String letters = PHONE_MAP.get(digits.charAt(index));

        // Try each letter for the current digit and recurse for remaining digits
        for (char letter : letters.toCharArray()) {
            current.append(letter); // Choose: add letter to current path
            backtrack(digits, index + 1, current, result); // Explore: recurse to next digit
            current.deleteCharAt(current.length() - 1); // Undo: remove last letter (backtrack)
        }
    }

    public static void main(String[] args) {
        LetterCombinationsPhoneNumber driver = new LetterCombinationsPhoneNumber();

        // Test case 1: Standard two-digit input
        String digits1 = "23";
        List<String> result1 = driver.letterCombinations(digits1);
        System.out.println("Input: \"23\" → " + result1);
        // Expected: [ad, ae, af, bd, be, bf, cd, ce, cf]

        // Test case 2: Empty string input (edge case)
        // String digits2 = "";
        // List<String> result2 = driver.letterCombinations(digits2);
        // System.out.println("Input: \"\" → " + result2);
        // Expected: []

        // Test case 3: Single digit
        // String digits3 = "2";
        // List<String> result3 = driver.letterCombinations(digits3);
        // System.out.println("Input: \"2\" → " + result3);
        // Expected: [a, b, c]

        // Test case 4: Digit with 4 letters ('7' → pqrs)
        // String digits4 = "79";
        // List<String> result4 = driver.letterCombinations(digits4);
        // System.out.println("Input: \"79\" → " + result4);
        // Expected: [pw, px, py, pz, qw, qx, qy, qz, rw, rx, ry, rz, sw, sx, sy, sz]
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(4^n * n)
        • Worst Case:   O(4^n * n)

        Explanation:
        At each of the n digits, we branch into at most 4 letters (e.g., '7' → "pqrs").
        Building and storing each complete combination costs O(n), giving O(4^n * n) overall.

    Space Complexity:
        • O(n)

        Explanation:
        The recursion call stack goes n levels deep (one per digit), and the StringBuilder
        holds at most n characters at any point — output list storage excluded.

    ------------------------------------------------------------------------ */
}
