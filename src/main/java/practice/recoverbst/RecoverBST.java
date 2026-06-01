// LC#99: Recover Binary Search Tree

package practice.recoverbst;

import commons.TreeNode;

// Inorder traversal to find and swap the two misplaced nodes in a BST
class RecoverBST {

    // Track the two swapped nodes and the previous node during traversal
    private TreeNode first = null;
    private TreeNode second = null;
    private TreeNode prev = null;

    public void recoverTree(TreeNode root) {
        // Inorder traversal produces sorted order for a valid BST
        inorder(root);

        // Swap values of the two misplaced nodes to restore BST property
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    // Recursive inorder traversal to detect violations in sorted order
    private void inorder(TreeNode node) {
        if (node == null) {
            return;
        }

        inorder(node.left);

        // In valid BST, prev < current; violation means a swap occurred
        if (prev != null && prev.val > node.val) {
            if (first == null) {
                // First violation — prev is the first misplaced node (too large)
                first = prev;
            }
            // Always update second — handles both adjacent and non-adjacent swaps
            second = node;
        }
        prev = node;

        inorder(node.right);
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(n)
        • Worst Case: O(n)

        Explanation:
        Each node is visited exactly once during the inorder traversal.

    Space Complexity:
        • O(h) where h is the height of the tree

        Explanation:
        Recursion call stack depth equals the height of the tree — O(log n) for
        balanced trees, O(n) for skewed trees.

    ------------------------------------------------------------------------ */

    public static void main(String[] args) {
        // Test case 1: Non-adjacent swap [1,3,null,null,2] -> nodes 3 and 1 swapped
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(2);

        RecoverBST driver = new RecoverBST();
        driver.recoverTree(root);
        System.out.println(
                "Root: "
                        + root.val
                        + ", Left: "
                        + root.left.val
                        + ", Left.Right: "
                        + root.left.right.val);
        // Expected: Root: 3, Left: 1, Left.Right: 2

        // Test case 2: Adjacent swap [3,1,4,null,null,2]
        // TreeNode root2 = new TreeNode(3);
        // root2.left = new TreeNode(1);
        // root2.right = new TreeNode(4);
        // root2.right.left = new TreeNode(2);
        // RecoverBST driver2 = new RecoverBST();
        // driver2.recoverTree(root2);
        // System.out.println("Root: " + root2.val + ", Right: " + root2.right.val);
        // Expected: Root: 3, Right: 4 (nodes 2 and 4 were swapped back)
    }
}
