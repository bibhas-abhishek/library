public class LongestPalindromicSubstring {

    public int longestPalindromicSubstring(String str) {
        int n = str.length();
        // dp[i][j] will be true if substring str[i..j] is a palindrome
        boolean[][] dp = new boolean[n][n];
        int start = 0; // starting index of longest palindromic substring found

        // every single character is a palindrome of length 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        int maxLength = 1; // at least one character is a palindrome
        for (int k = 2; k <= n; k++) { // k is the length of substring being checked
            for (int i = 0; i <= n - k; i++) { // i is the start index of substring
                int j = i + k - 1; // j is the end index of substring
                // characters at ends must match and inner substring must be palindrome (or length <= 2)
                if (str.charAt(i) == str.charAt(j) && (k <= 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true; // mark substring str[i..j] as palindrome
                    if (k > maxLength) { // update longest if current is longer
                        start = i;
                        maxLength = k;
                    }
                }
            }
        }
        System.out.println(str.substring(start, start + maxLength));
        return maxLength;
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring driver = new LongestPalindromicSubstring();
        System.out.println(driver.longestPalindromicSubstring("forgeeksskeegfor"));
    }
}

/* ---------------------- TIME & SPACE COMPLEXITY ----------------------

Time Complexity:
    • Average Case: O(n^2)
    • Worst Case: O(n^2)

    Explanation:
    The algorithm checks all substrings by length (1..n) and for each length
    iterates start indices; overall the nested loops examine O(n^2) substrings,
    and each check/update is O(1) using the dp table.

Space Complexity:
    • O(n^2)

    Explanation:
    A boolean table dp[n][n] is used to record palindrome status for every
    substring (i, j), so space grows proportional to n^2.

------------------------------------------------------------------------ */
