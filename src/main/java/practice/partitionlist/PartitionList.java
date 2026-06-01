// LC#86: Partition List

package practice.partitionlist;

import commons.ListNode;

class PartitionList {

    // Two-dummy-head approach: collect nodes < x into one list, nodes >= x into another, then join
    public ListNode partition(ListNode head, int x) {
        // Dummy heads to simplify edge cases (no null checks for first insertion)
        ListNode lessHead = new ListNode(0);
        ListNode greaterHead = new ListNode(0);

        // Tail pointers for appending to each partition
        ListNode less = lessHead;
        ListNode greater = greaterHead;

        // Traverse the original list and distribute nodes by value
        ListNode current = head;
        while (current != null) {
            if (current.val < x) {
                // Append to the "less than x" partition
                less.next = current;
                less = less.next;
            } else {
                // Append to the "greater than or equal to x" partition
                greater.next = current;
                greater = greater.next;
            }
            current = current.next;
        }

        // Terminate the greater list to avoid cycles
        greater.next = null;

        // Connect the two partitions: all < x nodes followed by all >= x nodes
        less.next = greaterHead.next;

        // Skip the dummy head of the less-than partition
        return lessHead.next;
    }

    public static void main(String[] args) {
        // Test case 1: Mixed values around partition x=3
        // Input: 1 -> 4 -> 3 -> 2 -> 5 -> 2
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);

        PartitionList driver = new PartitionList();
        ListNode result = driver.partition(head, 3);

        System.out.print("Result: ");
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        System.out.println(); // Expected: 1 2 2 4 3 5

        // Test case 2: Two elements
        // ListNode head2 = new ListNode(2);
        // head2.next = new ListNode(1);
        // ListNode result2 = driver.partition(head2, 2);
        // System.out.print("Result: ");
        // while (result2 != null) {
        //     System.out.print(result2.val + " ");
        //     result2 = result2.next;
        // }
        // System.out.println(); // Expected: 1 2

        // Test case 3: Empty list
        // ListNode result3 = driver.partition(null, 0);
        // System.out.println("Result: " + result3); // Expected: null
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(n)
        • Worst Case: O(n)

        Explanation:
        Single pass through all n nodes, performing O(1) work per node
        (pointer reassignment and comparison).

    Space Complexity:
        • O(1)

        Explanation:
        Only a constant number of pointers (dummy heads and tail trackers) are
        used. No new nodes are allocated — existing nodes are re-linked in place.

    ------------------------------------------------------------------------ */
}
