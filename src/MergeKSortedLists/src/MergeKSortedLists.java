import commons.ListNode;

import java.util.PriorityQueue;
import java.util.Queue;


public class MergeKSortedLists {

    // Merge using a min-heap (priority queue).
    // Repeatedly extracts the smallest current node among the k lists,
    // appends it to the result, and if that node had a next node, pushes it into the heap.
    // This builds the merged list iteratively in ascending order.
    public ListNode mergeKListsHeap(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // Min-heap ordered by node value to always take the smallest head among the lists.
        Queue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
        // Dummy node simplifies list assembly; tail points to last node in result.
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode tail = dummy;
        // Seed heap with the head of each non-empty list.
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }

        // While there are nodes available from the k lists, take the smallest and append.
        while (!minHeap.isEmpty()) {
            tail.next = minHeap.poll();
            tail = tail.next;
            // If the appended node has a successor, push that successor to the heap.
            if (tail.next != null) {
                minHeap.offer(tail.next);
            }
        }
        return dummy.next;
    }

    // Merge two sorted lists using recursion.
    // Chooses the smaller head, links it to the result of merging the remainder, and returns the chosen node.
    private ListNode merge2Lists(ListNode headA, ListNode headB) {
        if (headA == null) {
            return headB;
        }

        if (headB == null) {
            return headA;
        }

        if (headA.val <= headB.val) {
            // headA is smaller or equal: attach merged remainder after headA.
            headA.next = merge2Lists(headA.next, headB);
            return headA;
        } else {
            // headB is smaller: attach merged remainder after headB.
            headB.next = merge2Lists(headB.next, headA);
            return headB;
        }
    }

    // Divide-and-conquer helper: splits the array of lists into halves and merges each half.
    // Recursively reduces the k-way merge to repeated 2-way merges.
    private ListNode mergeKListsRec(ListNode[] lists, int start, int end) {
        if (start > end) {
            return null;
        }

        if (start == end) {
            return lists[start];
        }

        int mid = start + (end - start) / 2;
        // Merge left half and right half independently, then merge the two results.
        ListNode headA = mergeKListsRec(lists, start, mid);
        ListNode headB = mergeKListsRec(lists, mid + 1, end);
        return merge2Lists(headA, headB);
    }

    // Public wrapper for the divide-and-conquer approach.
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeKListsRec(lists, 0, lists.length - 1);
    }

    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + (head.next != null ? "->" : ""));
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MergeKSortedLists driver = new MergeKSortedLists();

        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(4);
        ListNode a3 = new ListNode(5);
        a1.next = a2;
        a2.next = a3;

        ListNode b1 = new ListNode(0);
        ListNode b2 = new ListNode(3);
        ListNode b3 = new ListNode(4);
        b1.next = b2;
        b2.next = b3;

        ListNode c1 = new ListNode(2);
        ListNode c2 = new ListNode(6);
        c1.next = c2;

        ListNode[] lists = {a1, b1, c1};

        ListNode result = driver.mergeKListsHeap(lists);
        printList(result);
    }
}

/*
Overall time & space complexity (entire implementation)

Overall (worst-case) time complexity: O(N log k)
- N = total number of nodes across all input lists.
- k = number of input lists.

Overall (worst-case) auxiliary space complexity: O(k)
- This reflects the maximum extra space used by the implementations in this file (heap storage and pointer overhead).
- Note: recursive divide-and-conquer also uses recursion stack space (O(log k)), but the worst-case auxiliary space across the provided approaches is O(k).
*/
