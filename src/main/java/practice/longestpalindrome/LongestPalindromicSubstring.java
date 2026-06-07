// LC#5: Longest Palindromic Substring

package practice.longestpalindrome;

public class LongestPalindromicSubstring {

    // Dynamic programming approach: build a table of palindrome statuses bottom-up
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        int n = s.length();
        // dp[i][j] = true means substring s[i..j] is a palindrome
        boolean[][] dp = new boolean[n][n];
        int start = 0;
        int maxLen = 1;

        // Base case: every single character is a palindrome
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        // Base case: check all substrings of length 2
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                maxLen = 2;
            }
        }

        // Fill table for substrings of length 3 and above
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                // j is the ending index of substring of length 'len' starting at i
                int j = i + len - 1;

                // s[i..j] is palindrome if s[i+1..j-1] is palindrome AND outer chars match
                if (dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                    // Update longest palindrome tracking
                    if (len > maxLen) {
                        start = i;
                        maxLen = len;
                    }
                }
            }
        }

        return s.substring(start, start + maxLen);
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(n^2)
        • Worst Case: O(n^2)

        Explanation:
        The nested loops iterate over all O(n^2) substrings by length and start index,
        with each palindrome check being O(1) via the precomputed dp table.

    Space Complexity:
        • O(n^2)

        Explanation:
        A 2D boolean table dp[n][n] stores palindrome status for every substring pair (i, j).

    ------------------------------------------------------------------------ */

    public static void main(String[] args) {
        // Test case 1: Standard palindrome in the middle
        String s1 = "babad";

        LongestPalindromicSubstring driver = new LongestPalindromicSubstring();
        String result1 = driver.longestPalindrome(s1);
        System.out.println("Result: " + result1); // Expected: "bab" or "aba"

        // Test case 2: Even-length palindrome
        // String s2 = "cbbd";
        // String result2 = driver.longestPalindrome(s2);
        // System.out.println("Result: " + result2); // Expected: "bb"

        // Test case 3: Single character
        // String s3 = "a";
        // String result3 = driver.longestPalindrome(s3);
        // System.out.println("Result: " + result3); // Expected: "a"
    }
}
