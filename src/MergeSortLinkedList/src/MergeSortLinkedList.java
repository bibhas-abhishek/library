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

    // Iterative merge of two sorted lists using a dummy head.
    private ListNode mergeLists(ListNode headA, ListNode headB) {
        // Dummy node simplifies head handling.
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        ListNode a = headA;
        ListNode b = headB;

        // Walk both lists and append the smaller node each time.
        while (a != null && b != null) {
            if (a.val <= b.val) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }

        // Attach whichever list still has nodes left.
        if (a != null) {
            tail.next = a;
        } else if (b != null) {
            tail.next = b;
        }

        // Return merged list skipping the dummy node.
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
