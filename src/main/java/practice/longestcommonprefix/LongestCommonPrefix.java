package practice.longestcommonprefix;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        // Use first string as initial candidate prefix
        String prefix = strs[0];

        // Compare prefix against each subsequent string, shrinking it until it matches
        for (int i = 1; i < strs.length; i++) {
            // indexOf returns -1 if prefix is not at position 0; keep trimming last char
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);

                // No common prefix exists
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }

        return prefix;
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(S) where S = total characters across all strings
        • Worst Case:   O(S) — all strings are identical; every character is compared

        Explanation:
        In the worst case, indexOf scans the full prefix against each string, which
        across all strings totals at most S character comparisons. Each shrink step
        reduces work in subsequent iterations, so O(S) is the tight bound.

    Space Complexity:
        • O(m) where m = length of the first string

        Explanation:
        The only extra storage is the prefix string, which starts at length m and only
        shrinks; no auxiliary data structures are used.

    ------------------------------------------------------------------------ */

    public static void main(String[] args) {
        // Test case 1: Normal input with shared prefix
        String[] strs = {"flower", "flow", "flight"};

        LongestCommonPrefix driver = new LongestCommonPrefix();
        String result = driver.longestCommonPrefix(strs);
        System.out.println("Result: " + result); // Expected: "fl"

        // Test case 2: No common prefix
        // String[] strs2 = {"dog", "racecar", "car"};
        // String result2 = driver.longestCommonPrefix(strs2);
        // System.out.println("Result: " + result2); // Expected: ""

        // Test case 3: Single character strings with match
        // String[] strs3 = {"a"};
        // String result3 = driver.longestCommonPrefix(strs3);
        // System.out.println("Result: " + result3); // Expected: "a"
    }
}
