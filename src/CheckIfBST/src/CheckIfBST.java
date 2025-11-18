import commons.TreeNode;


public class CheckIfBST {

    // Performs an inorder traversal and prints node values.
    // Inorder traversal visits left subtree, node, then right subtree.
    public void inorderDFS(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderDFS(root.left);
        System.out.print(root.val + " ");
        inorderDFS(root.right);
    }

    // Public wrapper to initiate BST check with full integer range.
    // Uses helper checkBST with min/max bounds.
    public boolean checkBST(TreeNode root) {
        return checkBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // Recursively verifies that each node's value lies strictly between min and max.
    // For left child, upper bound becomes current node's value.
    // For right child, lower bound becomes current node's value.
    // Returns false immediately if a node violates the BST invariant.
    private boolean checkBST(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }

        if (root.val >= max || root.val <= min) {
            return false;
        }
        return checkBST(root.left, min, root.val) && checkBST(root.right, root.val, max);
    }

    public static void main(String[] args) {
        CheckIfBST driver = new CheckIfBST();

        // Constructing a valid BST:
        //         4
        //       /   \
        //      2     6
        //     / \   / \
        //    1   3 5   7
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        // Should print: 1 2 3 4 5 6 7
        driver.inorderDFS(root);
        System.out.println();

        // Should print: true
        System.out.println(driver.checkBST(root));
    }
}

/* ---------------------- TIME & SPACE COMPLEXITY ----------------------

Time Complexity:
    • Average Case: O(n)
    • Worst Case: O(n)

    Explanation:
    The algorithm visits each node exactly once in the recursive traversal to validate BST properties,
    so time grows linearly with the number of nodes n.

Space Complexity:
    • O(h)

    Explanation:
    Space is dominated by the recursion stack of height h (tree height). In the worst case (skewed tree)
    h = n, giving O(n) auxiliary space; for a balanced tree h = O(log n).

------------------------------------------------------------------------ */
