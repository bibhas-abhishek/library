import java.util.HashMap;
import java.util.Map;


public class Knapsack01 {

    // -------------------- Top-down (memoized) approach --------------------
    // values         : array of item values
    // weight         : array of item weights
    // remainingWeight: remaining capacity available in knapsack
    // currentItem    : index of the current item being considered
    // memo           : memoization map storing computed subproblems
    private int knapsack01TopDown(int[] values, int[] weight, int remainingWeight, int currentItem,
                                  Map<String, Integer> memo) {
        if (currentItem >= values.length || remainingWeight <= 0) {
            // No items left or no capacity left -> zero value.
            return 0;
        }

        // Use (remainingWeight, currentItem) as the memo key for uniqueness.
        String key = remainingWeight + "-" + currentItem;
        if (memo.containsKey(key)) {
            // Return previously computed result for this subproblem.
            return memo.get(key);
        }

        int maxValue;
        if (remainingWeight < weight[currentItem]) {
            // Current item can't fit -> skip it.
            maxValue = knapsack01TopDown(values, weight, remainingWeight, currentItem + 1, memo);
        } else {
            // Consider both taking and skipping the current item; choose the better.
            int take = values[currentItem] + knapsack01TopDown(
                    values, weight, remainingWeight - weight[currentItem], currentItem + 1, memo);
            int skip = knapsack01TopDown(values, weight, remainingWeight, currentItem + 1, memo);
            maxValue = Math.max(take, skip);
        }

        // Store computed result for this subproblem.
        memo.put(key, maxValue);
        return maxValue;
    }

    // Public wrapper for top-down approach.
    public int knapsack01TopDown(int[] values, int[] weight, int maxWeight) {
        return knapsack01TopDown(values, weight, maxWeight, 0, new HashMap<>());
    }

    // -------------------- Bottom-up (iterative) approach --------------------
    // Standard DP table: dp[i][w] = max value using first i items with capacity w.
    public int knapsack01BottomUp(int[] values, int[] weight, int maxWeight) {
        int n = values.length;
        // dp dimensions (n+1) x (maxWeight+1)
        int[][] dp = new int[n + 1][maxWeight + 1];

        // Build table: for i = 1..n, for w = 0..maxWeight
        for (int i = 1; i <= n; i++) {
            int val = values[i - 1];
            int wt = weight[i - 1];
            for (int w = 0; w <= maxWeight; w++) {
                if (wt > w) {
                    // can't take item i-1
                    dp[i][w] = dp[i - 1][w];
                } else {
                    // max of taking or skipping the item
                    dp[i][w] = Math.max(dp[i - 1][w], val + dp[i - 1][w - wt]);
                }
            }
        }
        return dp[n][maxWeight];
    }

    public static void main(String[] args) {
        int[] value = {22, 20, 15, 30, 24, 54, 21, 32, 18, 25};
        int[] weight = {4, 2, 3, 5, 5, 6, 9, 7, 8, 10};
        int maxCapacity = 30;

        Knapsack01 solver = new Knapsack01();
        int resultTopDown = solver.knapsack01TopDown(value, weight, maxCapacity);
        int resultBottomUp = solver.knapsack01BottomUp(value, weight, maxCapacity);

        System.out.println("Top-down (memoized) result:    " + resultTopDown);
        System.out.println("Bottom-up (iterative) result:  " + resultBottomUp);
    }
}

/* ---------------------- COMPLEXITY (Top-down & Bottom-up) ----------------------

Top-down (memoized):
    Time Complexity : O(n * W)
    Space Complexity: O(n * W) for memoization + O(n) recursion stack (overall O(n * W))

Bottom-up (iterative):
    Time Complexity : O(n * W)  -- two nested loops over items and capacity
    Space Complexity: O(n * W)  -- dp table of size (n+1) x (W+1)
    (Can be reduced to O(W) by using a 1D array and iterating weights backward for each item.)

------------------------------------------------------------------------------- */
