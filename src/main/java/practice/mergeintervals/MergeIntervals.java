// LC#56: Merge Intervals

package practice.mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

    // Sort by start time so overlapping intervals are adjacent, then merge in one pass
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        // Sort by start time to bring overlapping intervals next to each other
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> merged = new ArrayList<>();
        // Seed with the first interval to compare against
        int[] current = intervals[0];
        merged.add(current);

        // Walk remaining intervals, extending current if overlapping, else starting a new one
        for (int i = 1; i < intervals.length; i++) {
            int[] next = intervals[i];
            // Overlap exists when next.start <= current.end (they share at least one point)
            if (next[0] <= current[1]) {
                // Extend current's end to cover next; take max in case next is fully contained
                current[1] = Math.max(current[1], next[1]);
            } else {
                // No overlap — promote next to be the new "current" and add to result
                current = next;
                merged.add(current);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(N log N)
        • Worst Case: O(N log N)

        Explanation:
        Sorting dominates at O(N log N). The single merge pass is O(N), making
        the overall complexity O(N log N) where N = number of intervals.

    Space Complexity:
        • O(N)

        Explanation:
        The result list holds up to N intervals in the worst case (no overlaps).
        Sorting uses O(log N) stack space for Timsort, dominated by output.

    ------------------------------------------------------------------------ */

    public static void main(String[] args) {
        // Test case 1: Multiple overlaps and a disjoint interval
        int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};

        MergeIntervals driver = new MergeIntervals();
        int[][] result1 = driver.merge(intervals1);
        System.out.println("Result: " + Arrays.deepToString(result1));
        // Expected: [[1, 6], [8, 10], [15, 18]]

        // Test case 2: Touching intervals (end == next start) should merge
        // int[][] intervals2 = {{1, 4}, {4, 5}};
        // int[][] result2 = driver.merge(intervals2);
        // System.out.println("Result: " + Arrays.deepToString(result2));
        // Expected: [[1, 5]]

        // Test case 3: One interval fully contains another
        // int[][] intervals3 = {{1, 10}, {2, 3}, {4, 8}};
        // int[][] result3 = driver.merge(intervals3);
        // System.out.println("Result: " + Arrays.deepToString(result3));
        // Expected: [[1, 10]]
    }
}
