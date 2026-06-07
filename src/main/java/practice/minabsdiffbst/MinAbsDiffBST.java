// LC#530: Minimum Absolute Difference in BST

package practice.minabsdiffbst;

import commons.TreeNode;

public class MinAbsDiffBST {

    // Tracks the previously visited node during in-order traversal
    private TreeNode prev = null;

    // Stores the minimum absolute difference found so far
    private int minDiff = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return minDiff;
    }

    // In-order visits nodes in ascending order; min diff must be between adjacent sorted values
    private void inorder(TreeNode node) {
        if (node == null) {
            return;
        }

        // Traverse left subtree (smaller values)
        inorder(node.left);

        // Process current node: compare with previous in-order value
        if (prev != null) {
            minDiff = Math.min(minDiff, node.val - prev.val);
        }
        // Update prev to current node for the next comparison
        prev = node;

        // Traverse right subtree (larger values)
        inorder(node.right);
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(N)
        • Worst Case: O(N)

        Explanation:
        Each node is visited exactly once during the in-order traversal, with O(1)
        work at each node (one comparison and one assignment).

    Space Complexity:
        • O(H) where H = height of the tree

        Explanation:
        The recursion call stack reaches a depth equal to the tree height: O(log N)
        for a balanced BST, O(N) for a completely skewed tree.

    ------------------------------------------------------------------------ */

    public static void main(String[] args) {
        // Test case 1: Balanced BST
        //       4
        //      / \
        //     2   6
        //    / \
        //   1   3
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(6);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(3);

        MinAbsDiffBST driver = new MinAbsDiffBST();
        int result1 = driver.getMinimumDifference(root1);
        System.out.println("Result: " + result1); // Expected: 1

        // Test case 2: Simple BST
        //     1
        //      \
        //       3
        //      /
        //     2
        // TreeNode root2 = new TreeNode(1);
        // root2.right = new TreeNode(3);
        // root2.right.left = new TreeNode(2);
        // int result2 = driver.getMinimumDifference(root2);
        // System.out.println("Result: " + result2); // Expected: 1

        // Test case 3: Large gap BST
        //     1
        //      \
        //      48
        //      / \
        //    12   49
        // TreeNode root3 = new TreeNode(1);
        // root3.right = new TreeNode(48);
        // root3.right.left = new TreeNode(12);
        // root3.right.right = new TreeNode(49);
        // int result3 = driver.getMinimumDifference(root3);
        // System.out.println("Result: " + result3); // Expected: 1
    }
}
