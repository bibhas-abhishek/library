import commons.TreeNode;

import java.util.Stack;


public class KSmallestInBST {

    /* ---------------- Recursive approach helpers ---------------- */

    // Wrapper for recursive approach. Returns -1 if k is invalid or not found.
    public static int kthSmallestRecursive(TreeNode root, int k) {
        if (root == null || k <= 0) {
            return -1;
        }
        // Use a single-element array to simulate mutable counters/return value.
        int[] counter = new int[]{k};
        int[] result = new int[]{-1};
        inorderRecursive(root, counter, result);
        return result[0];
    }

    // In-order traversal (left, node, right). Decrements counter when visiting a node.
    // When counter reaches 0, record the node value in result[0].
    private static void inorderRecursive(TreeNode node, int[] counter, int[] result) {
        if (node == null) {
            return;
        }
        // Traverse left subtree first.
        inorderRecursive(node.left, counter, result);
        // If already found, skip further work.
        if (counter[0] == 0) {
            return;
        }
        // Visit current node.
        counter[0]--;
        if (counter[0] == 0) {
            result[0] = node.val;
            return;
        }
        // Traverse right subtree.
        inorderRecursive(node.right, counter, result);
    }

    /* ---------------- Iterative approach ---------------- */

    // Iterative in-order using an explicit stack. Returns -1 if k is invalid or not found.
    public static int kthSmallestIterative(TreeNode root, int k) {
        if (root == null || k <= 0) {
            return -1;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        int count = 0;

        // Standard in-order iteration: push left nodes, then process node, then go right.
        while (current != null || !stack.isEmpty()) {
            // Reach the left-most node of the current subtree.
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            // current is null here, pop the most recent node.
            current = stack.pop();
            count++;
            // If we've reached the k-th visited node, return its value.
            if (count == k) {
                return current.val;
            }
            // Move to the right subtree.
            current = current.right;
        }
        // If k is larger than number of nodes, return -1.
        return -1;
    }

    /* ---------------------- Example usage ---------------------- */
    public static void main(String[] args) {
        // Build a sample BST:
        //        5
        //       / \
        //      3   7
        //     / \ / \
        //    2  4 6  8
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(8);

        int k = 3;
        System.out.println("Recursive k-th smallest (k=" + k + "): " + kthSmallestRecursive(root, k));
        System.out.println("Iterative k-th smallest (k=" + k + "): " + kthSmallestIterative(root, k));
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(h + k)
        • Worst Case: O(n)

        Explanation:
        The algorithm performs an in-order traversal and visits nodes in ascending order.
        On average it descends the tree height h to the left-most area and then visits k nodes,
        which costs O(h + k). In the degenerate case (skewed tree or k ≈ n) it visits all n nodes.

    Space Complexity:
        • O(h)

        Explanation:
        The main extra space is the recursion stack (recursive solution) or explicit stack
        (iterative solution), which at most holds nodes along the current path — proportional to tree height h.
        In the worst-case skewed tree h = n.

    ------------------------------------------------------------------------ */
}
