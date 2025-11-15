import commons.ListNode;

public class MergeSortLinkedList {

    // Print the list values using "->" between nodes.
    // Iterates through the list and prints each node's value.
    // This method is purely for display/debugging.
    public void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            // Print the current node value and a separator if there is a next node.
            if (current.next != null) {
                System.out.print(current.val + "->");
            } else {
                System.out.print(current.val);
            }
            current = current.next;
        }
        System.out.println();
    }

    // Append a new node with 'data' to the end. Return the (possibly new) head.
    // If the list is empty, create and return a new head node.
    // Traverses to the tail and attaches a new node there.
    public ListNode insert(ListNode head, int data) {
        if (head == null) {
            // Empty list: new node becomes the head.
            return new ListNode(data);
        }

        // Otherwise traverse to the last node and attach the new node.
        ListNode current = head;
        while (current.next != null) {
            current = current.next;
        }

        current.next = new ListNode(data);
        return head;
    }

    // Find and return the middle node using slow/fast pointers.
    // For even-length lists this returns the first of the two middle nodes.
    // Uses the classic slow/fast pointer technique so we can split the list.
    private ListNode findMiddle(ListNode head) {
        if (head == null) {
            return head;
        }

        // slow moves one step, fast moves two steps.
        // When fast reaches end, slow is at (first) middle.
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Iterative merge of two sorted lists using a dummy head.
    // Walk both lists and attach the smaller node each iteration.
    // Returns the head of the merged, sorted list.
    private ListNode mergeLists(ListNode headLeft, ListNode headRight) {
        // Dummy node simplifies head handling and avoids special-casing the first node.
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        ListNode leftPtr = headLeft;
        ListNode rightPtr = headRight;

        // Merge by choosing the smaller current node from either list.
        while (leftPtr != null && rightPtr != null) {
            if (leftPtr.val <= rightPtr.val) {
                tail.next = leftPtr;
                leftPtr = leftPtr.next;
            } else {
                tail.next = rightPtr;
                rightPtr = rightPtr.next;
            }
            tail = tail.next;
        }

        // Attach the remainder of the list that still has nodes.
        if (leftPtr != null) {
            tail.next = leftPtr;
        } else if (rightPtr != null) {
            tail.next = rightPtr;
        }

        // Return the merged list, skipping the dummy node.
        return dummy.next;
    }

    // Top-down merge sort for linked list: split, sort halves, then merge.
    public ListNode mergeSort(ListNode head) {
        // Base case: empty list or single node is already sorted.
        if (head == null || head.next == null) {
            return head;
        }

        // Find middle and split list into two halves.
        ListNode middle = findMiddle(head);
        ListNode rightHead = middle.next;
        middle.next = null; // break the list into two parts

        // Recursively sort each half.
        ListNode leftSorted = mergeSort(head);
        ListNode rightSorted = mergeSort(rightHead);

        // Merge the two sorted halves and return.
        return mergeLists(leftSorted, rightSorted);
    }

    public static void main(String[] args) {
        MergeSortLinkedList driver = new MergeSortLinkedList();
        ListNode head = new ListNode(4);
        head = driver.insert(head, 7);
        head = driver.insert(head, 0);
        head = driver.insert(head, 1);
        head = driver.insert(head, 3);
        head = driver.insert(head, 2);

        driver.printList(head);
        head = driver.mergeSort(head);
        driver.printList(head);
    }

    /*
     * Complexity (overall for the entire algorithm)
     *
     * Time complexity:  O(n log n)
     *   - n is the number of nodes in the linked list.
     *   - The algorithm repeatedly splits the list and merges halves; split and merge
     *     across all levels cost O(n) per level and there are O(log n) levels.
     *
     * Space complexity: O(log n)
     *   - The implementation performs top-down recursive merge sort, which uses
     *     recursion stack space proportional to the height of the recursion tree.
     *   - In-place node re-linking is used for merging, so additional heap allocation
     *     is O(1) beyond the recursion stack.
     */
}
