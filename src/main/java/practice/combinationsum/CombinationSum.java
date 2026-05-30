// LC#39: Combination Sum

package practice.combinationsum;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // Start backtracking from index 0 with empty current combination
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(
            int[] candidates,
            int remaining,
            int start,
            List<Integer> current,
            List<List<Integer>> result) {
        // Base case: exact target reached — add a copy of current combination
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Explore each candidate from 'start' onwards to avoid duplicate combinations
        for (int i = start; i < candidates.length; i++) {
            // Skip if candidate exceeds remaining target
            if (candidates[i] > remaining) {
                continue;
            }

            current.add(candidates[i]); // choose
            // Pass 'i' (not i+1) because same element can be reused unlimited times
            backtrack(candidates, remaining - candidates[i], i, current, result);
            current.remove(current.size() - 1); // backtrack
        }
    }

    public static void main(String[] args) {
        CombinationSum driver = new CombinationSum();

        // Test case 1: Multiple combinations with reuse
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        System.out.println("Result: " + driver.combinationSum(candidates, target));
        // Expected: [[2,2,3],[7]]

        // Test case 2: Single element repeated
        // int[] candidates2 = {2, 3, 5};
        // int target2 = 8;
        // System.out.println("Result: " + driver.combinationSum(candidates2, target2));
        // Expected: [[2,2,2,2],[2,3,3],[3,5]]
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(n^(T/M))
        • Worst Case:   O(n^(T/M))

        Explanation:
        n = number of candidates, T = target, M = smallest candidate value.
        The recursion tree has at most T/M levels (max times we can pick the
        smallest element), and at each level we branch into up to n choices.

    Space Complexity:
        • O(T/M)

        Explanation:
        The recursion stack and current combination list grow to at most T/M
        depth (the longest possible combination using the smallest candidate).

    ------------------------------------------------------------------------ */
}
