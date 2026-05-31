// LC#230: Kth Smallest Element in a BST

package practice.ksmallestinbst;

import commons.TreeNode;
import java.util.Stack;

public class KSmallestInBST {

    // Iterative inorder traversal using explicit stack to find kth smallest element
    // Inorder of BST yields sorted order, so kth visited node is the answer
    public int kthSmallest(TreeNode root, int k) {
        // Use stack to simulate recursive inorder traversal iteratively
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        // Standard iterative inorder: push all left children, pop, then go right
        while (current != null || !stack.isEmpty()) {
            // Drill down to the leftmost node of current subtree
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // Pop the next smallest element from the stack
            current = stack.pop();
            k--;

            // When k reaches 0, we've found the kth smallest element
            if (k == 0) {
                return current.val;
            }

            // Move to right subtree to continue inorder sequence
            current = current.right;
        }

        return -1;
    }

    public static void main(String[] args) {
        // Test case 1: k=1 on standard BST
        //        3
        //       / \
        //      1   4
        //       \
        //        2
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);

        KSmallestInBST driver = new KSmallestInBST();
        int result = driver.kthSmallest(root, 1);
        System.out.println("Result: " + result); // Expected: 1

        // Test case 2: k=3 on larger BST
        //        5
        //       / \
        //      3   6
        //     / \
        //    2   4
        //   /
        //  1
        // TreeNode root2 = new TreeNode(5);
        // root2.left = new TreeNode(3);
        // root2.right = new TreeNode(6);
        // root2.left.left = new TreeNode(2);
        // root2.left.right = new TreeNode(4);
        // root2.left.left.left = new TreeNode(1);
        // int result2 = driver.kthSmallest(root2, 3);
        // System.out.println("Result: " + result2); // Expected: 3

        // Test case 3: k equals total nodes (find maximum)
        // TreeNode root3 = new TreeNode(2);
        // root3.left = new TreeNode(1);
        // root3.right = new TreeNode(3);
        // int result3 = driver.kthSmallest(root3, 3);
        // System.out.println("Result: " + result3); // Expected: 3
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(H + k)
        • Worst Case: O(n)

        Explanation:
        The algorithm descends to the leftmost node (height H) then visits k nodes in order.
        In the worst case (skewed tree or k ≈ n), it visits all n nodes.

    Space Complexity:
        • O(H)

        Explanation:
        The explicit stack holds at most H nodes (the current path from root to deepest left node).
        In the worst case of a skewed tree, H = n.

    ------------------------------------------------------------------------ */
}
