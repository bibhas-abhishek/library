import commons.ListNode;

import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedLists {

    // Merge K sorted linked lists using a min-heap.
    // Push the head of each list into the heap. Repeatedly extract the
    // smallest node, append it to the result, and if that node has a next,
    // push the next node into the heap.
    public ListNode mergeKListsHeap(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        Queue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode tail = dummy;

        // Insert non-null list heads into the heap
        for (ListNode head : lists) {
            if (head != null) {
                minHeap.offer(head);
            }
        }

        // Extract-min and build the merged list
        while (!minHeap.isEmpty()) {
            ListNode smallestNode = minHeap.poll();
            tail.next = smallestNode;
            tail = tail.next;

            if (smallestNode.next != null) {
                minHeap.offer(smallestNode.next);
            }
        }

        return dummy.next;
    }

    // Merge two sorted linked lists using recursion.
    private ListNode merge2Lists(ListNode headA, ListNode headB) {
        if (headA == null) {
            return headB;
        }

        if (headB == null) {
            return headA;
        }

        if (headA.val <= headB.val) {
            headA.next = merge2Lists(headA.next, headB);
            return headA;
        } else {
            headB.next = merge2Lists(headB.next, headA);
            return headB;
        }
    }

    // Divide-and-conquer: split K lists into two halves,
    // recursively merge each half, then merge the two halves.
    private ListNode mergeKListsRecursive(ListNode[] lists, int start, int end) {
        if (start > end) {
            return null;
        }

        if (start == end) {
            return lists[start];
        }

        int mid = start + (end - start) / 2;

        ListNode leftMerged = mergeKListsRecursive(lists, start, mid);
        ListNode rightMerged = mergeKListsRecursive(lists, mid + 1, end);

        return merge2Lists(leftMerged, rightMerged);
    }

    // -------------------- DRIVER CODE --------------------
    public static void main(String[] args) {
        // Create sample lists for demonstration:
        // List 1: 1 -> 4 -> 5
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);

        // List 2: 1 -> 3 -> 4
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        // List 3: 2 -> 6
        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);

        ListNode[] lists = new ListNode[]{l1, l2, l3};

        MergeKSortedLists solver = new MergeKSortedLists();
        ListNode merged = solver.mergeKListsHeap(lists);

        // Print the merged output
        ListNode temp = merged;
        while (temp != null) {
            System.out.print(temp.val);
            if (temp.next != null) {
                System.out.print(" -> ");
            }
            temp = temp.next;
        }
        System.out.println();
    }

    // -------------------- OVERALL TIME & SPACE COMPLEXITY --------------------
    /*
     * Overall Time Complexity:
     *    O(N log K)
     * Where:
     *    K = number of sorted lists
     *    N = total number of nodes across all lists
     *
     * Explanation:
     *    The heap-based merge inserts each node once and each heap operation
     *    (push/pop) costs O(log K). Thus processing all N nodes yields O(N log K).
     *
     * Overall Space Complexity:
     *    O(K)
     * Explanation:
     *    The heap stores at most one node from each of the K lists. The output
     *    list reuses existing nodes, so no extra list storage is added.
     */
}
