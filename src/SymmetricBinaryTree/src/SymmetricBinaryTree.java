import commons.TreeNode;


public class SymmetricBinaryTree {

    // Checks whether two subtrees are mirror images of each other.
    private static boolean isSymmetric(TreeNode nodeA, TreeNode nodeB) {
        // If both nodes are null, they mirror each other.
        if (nodeA == null && nodeB == null) {
            return true;
        }

        // If both nodes exist, compare their values and check mirrored children.
        if (nodeA != null && nodeB != null) {
            return nodeA.val == nodeB.val && isSymmetric(nodeA.left, nodeB.right)
                    && isSymmetric(nodeA.right, nodeB.left);
        }

        // If only one is null, they are not symmetric.
        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        System.out.print(isSymmetric(root, root));
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(n)
        • Worst Case:  O(n)

        Explanation:
        Each node of the tree is visited once during the mirrored DFS comparison,
        so the total work scales linearly with the number of nodes.

    Space Complexity:
        • O(h)

        Explanation:
        The recursion stack grows with the height of the tree. In the worst case
        (skewed tree), h = n; in the best/balanced case, h = log n.

    ------------------------------------------------------------------------ */
}
