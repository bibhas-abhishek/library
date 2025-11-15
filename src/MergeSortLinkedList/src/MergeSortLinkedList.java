import commons.ListNode;

public class MergeSortLinkedList {

    // Print the list values using "->" between nodes.
    // Iterates through the list and prints each node's value.
    public void printList(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + (temp.next != null ? "->" : ""));
            temp = temp.next;
        }
        System.out.println();
    }

    // Append a new node with 'data' to the end. Return the (possibly new) head.
    // If the list is empty, create and return a new head node.
    public ListNode insert(ListNode head, int data) {
        if (head == null) {
            return new ListNode(data);
        }

        // Otherwise traverse to the last node...
        ListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }

        // ...and attach the new node at the tail.
        temp.next = new ListNode(data);
        return head;
    }

    // Find and return the middle node using slow/fast pointers.
    // For even-length lists this returns the first of the two middle nodes.
    private ListNode findMiddle(ListNode head) {
        if (head == null) {
            return head;
        }

        // slow moves one step, fast moves two steps.
        // When fast reaches end, slow is at middle.
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Recursively merge two sorted lists and return merged head.
    // Chooses the smaller head and recurses on the remainder.
    private ListNode mergeLists(ListNode headA, ListNode headB) {
        if (headA == null) {
            return headB;
        }
        if (headB == null) {
            return headA;
        }

        if (headA.val <= headB.val) {
            // headA is smaller (or equal): headA.next becomes merge of remainder.
            headA.next = mergeLists(headA.next, headB);
            return headA;
        } else {
            // headB is smaller: headB.next becomes merge of remainder.
            headB.next = mergeLists(headB.next, headA);
            return headB;
        }
    }

    // Top-down merge sort for linked list: split, sort halves, then merge.
    public ListNode mergeSort(ListNode head) {
        // Base case: empty list or single node is already sorted.
        if (head == null || head.next == null) {
            return head;
        }

        // Find middle and split list into two halves.
        ListNode middle = findMiddle(head);
        ListNode middleNext = middle.next;
        middle.next = null; // break the list into two parts

        // Recursively sort each half.
        ListNode left = mergeSort(head);
        ListNode right = mergeSort(middleNext);

        // Merge the two sorted halves and return.
        return mergeLists(left, right);
    }

    public static void main(String[] args) {
        MergeSortLinkedList obj = new MergeSortLinkedList();
        ListNode head = new ListNode(4);
        head = obj.insert(head, 7);
        head = obj.insert(head, 0);
        head = obj.insert(head, 1);
        head = obj.insert(head, 3);
        head = obj.insert(head, 2);

        obj.printList(head);
        head = obj.mergeSort(head);
        obj.printList(head);
    }
}
