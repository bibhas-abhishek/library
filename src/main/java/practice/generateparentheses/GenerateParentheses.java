// LC#22: Generate Parentheses

package practice.generateparentheses;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        // Start backtracking with empty string and 0 open/close counts
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder current, int open, int close, int n) {
        // Base case: valid combination formed when length reaches 2*n
        if (current.length() == 2 * n) {
            result.add(current.toString());
            return;
        }

        // Add open paren if we haven't used all n openers yet
        if (open < n) {
            current.append('(');
            backtrack(result, current, open + 1, close, n);
            current.deleteCharAt(current.length() - 1); // backtrack
        }

        // Add close paren only if it won't create an invalid sequence (close < open)
        if (close < open) {
            current.append(')');
            backtrack(result, current, open, close + 1, n);
            current.deleteCharAt(current.length() - 1); // backtrack
        }
    }

    public static void main(String[] args) {
        GenerateParentheses driver = new GenerateParentheses();

        // Test case 1: n = 3
        List<String> result = driver.generateParenthesis(3);
        System.out.println("Result: " + result);
        // Expected: ["((()))","(()())","(())()","()(())","()()()"]

        // Test case 2: n = 1
        // List<String> result2 = driver.generateParenthesis(1);
        // System.out.println("Result: " + result2); // Expected: ["()"]
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(4^n / √n)
        • Worst Case:   O(4^n / √n)

        Explanation:
        The number of valid parentheses combinations is the nth Catalan number,
        C(n) = (2n)! / ((n+1)! * n!), which is bounded by O(4^n / √n). Each
        combination takes O(n) to build.

    Space Complexity:
        • O(n)

        Explanation:
        The recursion stack goes at most 2n levels deep, and the StringBuilder
        holds at most 2n characters at any point — output list storage excluded.

    ------------------------------------------------------------------------ */
}
