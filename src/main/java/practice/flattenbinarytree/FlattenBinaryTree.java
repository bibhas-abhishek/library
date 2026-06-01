// LC#114: Flatten Binary Tree to Linked List

package practice.flattenbinarytree;

import commons.TreeNode;

public class FlattenBinaryTree {

    // Track the previously visited node in reverse postorder (right -> left -> root)
    private TreeNode prev = null;

    // Flatten tree in-place to a right-skewed linked list following preorder traversal
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        // Process right subtree first so it's already flattened when we attach it
        flatten(root.right);
        // Process left subtree next
        flatten(root.left);

        // Point current node's right to the previously processed node (reverse preorder linking)
        root.right = prev;
        // Nullify left pointer since the result is a right-only linked list
        root.left = null;
        // Update prev to current node for the next iteration up the call stack
        prev = root;
    }

    public static void main(String[] args) {
        // Test case 1: Standard tree [1,2,5,3,4,null,6]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);

        FlattenBinaryTree driver = new FlattenBinaryTree();
        driver.flatten(root);

        // Print flattened list
        TreeNode curr = root;
        StringBuilder sb = new StringBuilder();
        while (curr != null) {
            sb.append(curr.val);
            if (curr.right != null) {
                sb.append(" -> ");
            }
            curr = curr.right;
        }
        System.out.println("Result: " + sb); // Expected: 1 -> 2 -> 3 -> 4 -> 5 -> 6

        // Test case 2: Empty tree
        // driver = new FlattenBinaryTree();
        // driver.flatten(null);
        // System.out.println("Result: null"); // Expected: null (no output)

        // Test case 3: Single node
        // TreeNode single = new TreeNode(0);
        // driver = new FlattenBinaryTree();
        // driver.flatten(single);
        // System.out.println("Result: " + single.val); // Expected: 0
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(n)
        • Worst Case: O(n)

        Explanation:
        Each node is visited exactly once during the reverse postorder traversal,
        where n is the total number of nodes in the tree.

    Space Complexity:
        • O(h) where h is the height of the tree

        Explanation:
        The recursion call stack grows proportional to the tree height — O(log n) for
        balanced trees, O(n) for skewed trees in the worst case.

    ------------------------------------------------------------------------ */
}
