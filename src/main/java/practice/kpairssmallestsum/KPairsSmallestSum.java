package practice.kpairssmallestsum;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KPairsSmallestSum {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();

        // Min-heap stores [sum, i, j] — sorted by sum for greedy extraction of smallest pair
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Seed heap with (nums1[i], nums2[0]) for each i — best partner for every nums1 element
        // starts at j=0
        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            minHeap.offer(new int[] {nums1[i] + nums2[0], i, 0});
        }

        // Greedily extract smallest pair and push next candidate along the nums2 axis
        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int i = curr[1];
            int j = curr[2];

            List<Integer> pair = new ArrayList<>();
            pair.add(nums1[i]);
            pair.add(nums2[j]);
            result.add(pair);

            // Advance j by 1 — next best partner for nums1[i] is nums2[j+1]
            if (j + 1 < nums2.length) {
                minHeap.offer(new int[] {nums1[i] + nums2[j + 1], i, j + 1});
            }
        }

        return result;
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(k log k)
        • Worst Case:   O(k log k)

        Explanation:
        We seed the heap with at most min(n, k) entries and then perform k
        poll+offer cycles, each costing O(log k) since the heap size is bounded
        by k at all times.

    Space Complexity:
        • O(k)

        Explanation:
        The min-heap holds at most min(n, k) entries initially and stays bounded
        by k throughout; the result list grows to exactly k pairs.

    ------------------------------------------------------------------------ */

    public static void main(String[] args) {
        // Test case 1: Standard — first 3 pairs from sorted candidate sequence
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        int k = 3;

        KPairsSmallestSum driver = new KPairsSmallestSum();
        List<List<Integer>> result = driver.kSmallestPairs(nums1, nums2, k);
        System.out.println("Result: " + result); // Expected: [[1,2],[1,4],[1,6]]

        // Test case 2: Duplicate values — both [1,1] pairs must appear
        // int[] nums1b = {1, 1, 2};
        // int[] nums2b = {1, 2, 3};
        // int kb = 2;
        // List<List<Integer>> result2 = driver.kSmallestPairs(nums1b, nums2b, kb);
        // System.out.println("Result: " + result2); // Expected: [[1,1],[1,1]]

        // Test case 3: k exceeds total pairs — return all possible pairs
        // int[] nums1c = {1, 2};
        // int[] nums2c = {3};
        // int kc = 3;
        // List<List<Integer>> result3 = driver.kSmallestPairs(nums1c, nums2c, kc);
        // System.out.println("Result: " + result3); // Expected: [[1,3],[2,3]]
    }
}
