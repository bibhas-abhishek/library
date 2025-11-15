import commons.ListNode;

public class MergeSortLinkedList {

    // Print the list values using "->" between nodes.
    public void printList(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + (temp.next != null ? "->" : ""));
            temp = temp.next;
        }
        System.out.println();
    }

    // Append a new node with 'data' to the end. Return the (possibly new) head.
    public ListNode insert(ListNode head, int data) {
        if (head == null)
            return new ListNode(data);         // empty list: new node becomes head

        ListNode temp = head;
        while (temp.next != null)
            temp = temp.next;                  // traverse to last node

        temp.next = new ListNode(data);        // attach new node at the tail
        return head;
    }

    // Find and return the middle node using slow/fast pointers.
    // For even-length lists this returns the first of the two middle nodes.
    private ListNode findMiddle(ListNode head) {
        if (head == null) return head;

        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Recursively merge two sorted lists and return merged head.
    private ListNode mergeLists(ListNode headA, ListNode headB) {
        if (headA == null) return headB;
        if (headB == null) return headA;

        if (headA.val <= headB.val) {
            headA.next = mergeLists(headA.next, headB);
            return headA;
        } else {
            headB.next = mergeLists(headB.next, headA);
            return headB;
        }
    }

    // Top-down merge sort for linked list: split, sort halves, then merge.
    public ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) return head; // base case

        ListNode middle = findMiddle(head);
        ListNode middleNext = middle.next;
        middle.next = null;                    // split the list into two halves

        ListNode left = mergeSort(head);       // sort left half
        ListNode right = mergeSort(middleNext);// sort right half

        return mergeLists(left, right);        // merge sorted halves
    }

    public static void main(String[] args) {
        MergeSortLinkedList obj = new MergeSortLinkedList();
        ListNode head = null;
        head = obj.insert(head, 4);
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
