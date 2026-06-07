// LC#295: Find Median from Data Stream

package practice.findmedianfromdatastream;

import java.util.Collections;
import java.util.PriorityQueue;

class FindMedianFromDataStream {

    // Max-heap to store the smaller half of numbers
    private PriorityQueue<Integer> smallHalf;
    // Min-heap to store the larger half of numbers
    private PriorityQueue<Integer> largeHalf;

    public FindMedianFromDataStream() {
        // Max-heap keeps track of the largest element in the lower half
        smallHalf = new PriorityQueue<>(Collections.reverseOrder());
        // Min-heap keeps track of the smallest element in the upper half
        largeHalf = new PriorityQueue<>();
    }

    public void addNum(int num) {
        // Route num into the left half first
        smallHalf.offer(num);

        // Enforce heap boundary: smallHalf's top must never exceed largeHalf's top
        if (!largeHalf.isEmpty() && smallHalf.peek() > largeHalf.peek()) {
            largeHalf.offer(smallHalf.poll());
        }

        // Rebalance sizes: smallHalf is allowed to hold at most 1 extra element
        if (smallHalf.size() > largeHalf.size() + 1) {
            largeHalf.offer(smallHalf.poll());
        } else if (largeHalf.size() > smallHalf.size()) {
            smallHalf.offer(largeHalf.poll());
        }
    }

    public double findMedian() {
        // If odd total, median is top of smallHalf (the larger heap)
        if (smallHalf.size() > largeHalf.size()) {
            return smallHalf.peek();
        }
        // If even total, median is average of both heap tops
        return (smallHalf.peek() + largeHalf.peek()) / 2.0;
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(log n) per addNum, O(1) per findMedian
        • Worst Case: O(log n) per addNum, O(1) per findMedian

        Explanation:
        Each addNum performs at most 3 heap insertions/removals, each costing O(log n).
        findMedian only peeks at heap tops which is O(1).

    Space Complexity:
        • O(n)

        Explanation:
        Both heaps together store all n elements that have been added to the stream.

    ------------------------------------------------------------------------ */

    public static void main(String[] args) {
        // Test case 1: Example from problem
        FindMedianFromDataStream medianFinder = new FindMedianFromDataStream();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println("Median after [1,2]: " + medianFinder.findMedian()); // Expected: 1.5
        medianFinder.addNum(3);
        System.out.println("Median after [1,2,3]: " + medianFinder.findMedian()); // Expected: 2.0

        // Test case 2: Single element
        // FindMedianFromDataStream mf2 = new FindMedianFromDataStream();
        // mf2.addNum(5);
        // System.out.println("Median after [5]: " + mf2.findMedian()); // Expected: 5.0

        // Test case 3: Negative numbers and duplicates
        // FindMedianFromDataStream mf3 = new FindMedianFromDataStream();
        // mf3.addNum(-1);
        // mf3.addNum(-2);
        // mf3.addNum(-3);
        // mf3.addNum(-4);
        // System.out.println("Median after [-1,-2,-3,-4]: " + mf3.findMedian()); // Expected: -2.5
    }
}
