package practice.mergetwosortedlists;

import commons.ListNode;

class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Use a dummy head to simplify edge cases and avoid null checks on result head
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy; // Pointer to build the merged list

        // Traverse both lists, always appending the smaller node
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1; // Attach smaller node from list1
                list1 = list1.next; // Advance list1 pointer
            } else {
                current.next = list2; // Attach smaller node from list2
                list2 = list2.next; // Advance list2 pointer
            }
            current = current.next; // Advance merged list pointer
        }

        // Attach remaining nodes (at most one list still has elements)
        current.next = (list1 != null) ? list1 : list2;

        return dummy.next; // Skip dummy head, return actual merged list head

        /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

        Time Complexity:
            • Average Case: O(m + n)
            • Worst Case:   O(m + n)

            Explanation:
            Each node from both lists is visited exactly once during the while loop,
            where m and n are the lengths of list1 and list2 respectively.

        Space Complexity:
            • O(1)

            Explanation:
            Only a constant number of pointers (dummy, current) are used; nodes are
            relinked in-place with no auxiliary data structures or recursion stack.

        ------------------------------------------------------------------------ */
    }

    public static void main(String[] args) {
        MergeTwoSortedLists driver = new MergeTwoSortedLists();

        // Test case 1: Standard interleaved merge
        // list1 = [1, 2, 4], list2 = [1, 3, 4]
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode result = driver.mergeTwoLists(list1, list2);
        printList(result); // Expected: 1 -> 1 -> 2 -> 3 -> 4 -> 4

        // Test case 2: Both lists empty
        // ListNode result2 = driver.mergeTwoLists(null, null);
        // printList(result2); // Expected: (empty)

        // Test case 3: One list empty
        // ListNode list3 = new ListNode(0);
        // ListNode result3 = driver.mergeTwoLists(null, list3);
        // printList(result3); // Expected: 0
    }

    // Helper to print linked list nodes
    static void printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) {
                sb.append(" -> ");
            }
            head = head.next;
        }
        System.out.println(sb.toString().isEmpty() ? "(empty)" : sb.toString());
    }
}
