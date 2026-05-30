// LC#92: Reverse Linked List II

package practice.reverselinkedlist2;

import commons.ListNode;

public class ReverseLinkedList2 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // Dummy node to uniformly handle the case where left = 1 (no real predecessor)
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Walk prevLeft to the node just before position `left`
        ListNode prevLeft = dummy;
        for (int i = 1; i < left; i++) {
            prevLeft = prevLeft.next;
        }

        // `anchor` stays fixed as the tail of the reversed segment throughout
        ListNode anchor = prevLeft.next;

        // Repeatedly pluck the node right after `anchor` and insert it at the front
        // of the reversed segment (just after prevLeft) — (right - left) times
        for (int i = 0; i < right - left; i++) {
            ListNode nodeToMove = anchor.next; // node to relocate to the front
            anchor.next = nodeToMove.next; // detach nodeToMove from its current position
            nodeToMove.next = prevLeft.next; // point nodeToMove ahead to current front
            prevLeft.next = nodeToMove; // prevLeft now leads into nodeToMove
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ReverseLinkedList2 driver = new ReverseLinkedList2();

        // Test case 1: Reverse middle segment — [1,2,3,4,5], left=2, right=4
        ListNode head1 =
                new ListNode(
                        1,
                        new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        ListNode result1 = driver.reverseBetween(head1, 2, 4);
        printList(result1); // Expected: [1, 4, 3, 2, 5]

        // Test case 2: Single node list — [5], left=1, right=1
        // ListNode head2 = new ListNode(5, null);
        // ListNode result2 = driver.reverseBetween(head2, 1, 1);
        // printList(result2); // Expected: [5]

        // Test case 3: Reverse entire list — [1,2,3], left=1, right=3
        // ListNode head3 = new ListNode(1,
        //                 new ListNode(2,
        //                 new ListNode(3, null)));
        // ListNode result3 = driver.reverseBetween(head3, 1, 3);
        // printList(result3); // Expected: [3, 2, 1]
    }

    static void printList(ListNode node) {
        StringBuilder sb = new StringBuilder("[");
        while (node != null) {
            sb.append(node.val);
            if (node.next != null) {
                sb.append(", ");
            }
            node = node.next;
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(n)
        • Worst Case:   O(n)

        Explanation:
        We make one pass of up to (left - 1) steps to reach prevLeft, then at most
        (right - left) steps for the reversal loop — both bounded by n in total.

    Space Complexity:
        • O(1)

        Explanation:
        Only a constant number of pointer variables are used; no auxiliary data
        structures are allocated regardless of input size.

    ------------------------------------------------------------------------ */
}
