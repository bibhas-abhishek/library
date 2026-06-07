// LC#57: Insert Interval

package practice.insertinterval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertInterval {

    // Three-phase linear scan: add all before, merge overlapping, add all after
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // Phase 1: Add all intervals that end before newInterval starts (no overlap)
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // Phase 2: Merge all intervals that overlap with newInterval
        while (i < n && intervals[i][0] <= newInterval[1]) {
            // Expand newInterval to absorb the overlapping interval
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        // Add the fully merged interval
        result.add(newInterval);

        // Phase 3: Add all intervals that start after newInterval ends (no overlap)
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(N)
        • Worst Case: O(N)

        Explanation:
        A single pass through all N intervals with O(1) work per interval.
        No sorting needed since intervals are already sorted.

    Space Complexity:
        • O(N)

        Explanation:
        The result list holds up to N+1 intervals (all original plus the new one
        if it doesn't merge with anything).

    ------------------------------------------------------------------------ */

    public static void main(String[] args) {
        // Test case 1: New interval merges with middle ones
        int[][] intervals1 = {{1, 3}, {6, 9}};
        int[] newInterval1 = {2, 5};

        InsertInterval driver = new InsertInterval();
        int[][] result1 = driver.insert(intervals1, newInterval1);
        System.out.println("Result: " + Arrays.deepToString(result1));
        // Expected: [[1, 5], [6, 9]]

        // Test case 2: New interval overlaps multiple existing intervals
        // int[][] intervals2 = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        // int[] newInterval2 = {4, 8};
        // int[][] result2 = driver.insert(intervals2, newInterval2);
        // System.out.println("Result: " + Arrays.deepToString(result2));
        // Expected: [[1, 2], [3, 10], [12, 16]]

        // Test case 3: New interval before all existing
        // int[][] intervals3 = {{3, 5}, {8, 10}};
        // int[] newInterval3 = {1, 2};
        // int[][] result3 = driver.insert(intervals3, newInterval3);
        // System.out.println("Result: " + Arrays.deepToString(result3));
        // Expected: [[1, 2], [3, 5], [8, 10]]
    }
}
