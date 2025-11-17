import java.util.HashMap;
import java.util.Map;


public class CoinChange {

    // Public entry for the top-down (memoized recursion) approach.
    // Starts recursion at coin index 0 and with an empty memo map.
    public int coinChangeTopDown(int[] coins, int amount) {
        return coinChangeTopDown(coins, amount, 0, new HashMap<>());
    }

    // Recursive helper for top-down approach.
    // remAmt: remaining amount to form.
    // index: current coin index being considered.
    // memo: maps "remAmt-index" -> number of ways to form remAmt using coins[index..end].
    private int coinChangeTopDown(int[] coins, int remAmt, int index, Map<String, Integer> memo) {
        // If exact amount formed, this is one valid way.
        if (remAmt == 0) {
            return 1;
        }

        // If we've considered all coins and still have remaining amount, no way.
        if (index >= coins.length) {
            return 0;
        }

        // Use a combined key of remaining amount and index to memoize subproblems.
        String key = remAmt + "-" + index;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int include = 0;
        // Option 1: include current coin (stay at same index because coins can be reused).
        if (coins[index] <= remAmt) {
            include = coinChangeTopDown(coins, remAmt - coins[index], index, memo);
        }

        // Option 2: skip current coin and move to next.
        int exclude = coinChangeTopDown(coins, remAmt, index + 1, memo);

        // Total ways = ways including this coin + ways excluding it.
        int ways = include + exclude;
        memo.put(key, ways);
        return ways;
    }

    // Bottom-up dynamic programming approach.
    // denominations: available coin values.
    // total: the target amount.
    public int coinChangeBottomUp(int[] denominations, int total) {
        int n = denominations.length;
        // dp[i][t] will hold number of ways to make amount t using coins[0..i].
        int[][] dp = new int[n][total + 1];

        // There is exactly 1 way to make amount 0 (choose no coins) for any coin set.
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        // Fill the table row by row for each coin.
        for (int i = 0; i < n; i++) {
            for (int t = 1; t <= total; t++) {
                // Ways without using current coin (inherit from previous row).
                if (i > 0) {
                    dp[i][t] = dp[i - 1][t];
                }
                // Ways that include current coin at least once: use current row at (t - coin).
                if (t >= denominations[i]) {
                    dp[i][t] += dp[i][t - denominations[i]];
                }
            }
        }
        // Answer: number of ways to form 'total' using all coins.
        return dp[n - 1][total];
    }

    public static void main(String[] args) {
        int[] coins = new int[] { 1, 2, 3 };
        CoinChange driver = new CoinChange();
        System.out.println(driver.coinChangeTopDown(coins, 5));
    }

    /*
     * Overall Time and Space Complexity (for the implementation as a whole)
     *
     * Time complexity: O(n * amount)
     *   - In the worst case the number of distinct subproblems (or DP states) is
     *     proportional to n * amount, where n is the number of coin types and
     *     amount (total) is the target value. Both the top-down memoized approach
     *     and the bottom-up DP approach have worst-case time proportional to
     *     that product.
     *
     * Space complexity: O(n * amount)
     *   - The bottom-up implementation explicitly allocates an n x (amount+1)
     *     table. The top-down memoization may also store up to O(n * amount)
     *     distinct entries in the memo. Thus overall auxiliary space is
     *     proportional to n * amount.
     */
}
