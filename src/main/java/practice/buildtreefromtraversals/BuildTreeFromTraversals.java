// LC#105: Construct Binary Tree from Preorder and Inorder Traversal

package practice.buildtreefromtraversals;

import commons.TreeNode;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BuildTreeFromTraversals {

    // Index tracker for the current position in preorder array
    private int preorderIndex;

    // Map inorder values to their indices for O(1) root position lookup
    private Map<Integer, Integer> inorderMap;

    // Build binary tree using preorder (root-left-right) and inorder (left-root-right) arrays
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preorderIndex = 0;
        // Store inorder indices in hash map to avoid linear search for root position
        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return build(preorder, 0, inorder.length - 1);
    }

    // Recursively construct subtrees using inorder bounds to partition left/right children
    private TreeNode build(int[] preorder, int left, int right) {
        // Base case: no elements in this subtree range
        if (left > right) {
            return null;
        }

        // Preorder's next element is always the current subtree's root
        int rootVal = preorder[preorderIndex];
        preorderIndex++;
        TreeNode root = new TreeNode(rootVal);

        // Find root's position in inorder to split into left and right subtrees
        int inorderRootIndex = inorderMap.get(rootVal);

        // Build left subtree first (matches preorder's left-before-right ordering)
        root.left = build(preorder, left, inorderRootIndex - 1);
        // Build right subtree from elements after root in inorder
        root.right = build(preorder, inorderRootIndex + 1, right);

        return root;
    }

    public static void main(String[] args) {
        // Test case 1: preorder=[3,9,20,15,7], inorder=[9,3,15,20,7]
        BuildTreeFromTraversals driver = new BuildTreeFromTraversals();
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode result = driver.buildTree(preorder, inorder);
        System.out.println("Level order: " + levelOrder(result)); // Expected: [3, 9, 20, 15, 7]

        // Test case 2: single node
        // int[] preorder2 = {-1};
        // int[] inorder2 = {-1};
        // TreeNode result2 = driver.buildTree(preorder2, inorder2);
        // System.out.println("Level order: " + levelOrder(result2)); // Expected: [-1]

        // Test case 3: left-skewed tree
        // int[] preorder3 = {3, 2, 1};
        // int[] inorder3 = {1, 2, 3};
        // TreeNode result3 = driver.buildTree(preorder3, inorder3);
        // System.out.println("Level order: " + levelOrder(result3)); // Expected: [3, 2, 1]
    }

    // Helper to print tree in level order for verification
    private static String levelOrder(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (sb.length() > 1) {
                sb.append(", ");
            }
            sb.append(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(n)
        • Worst Case: O(n)

        Explanation:
        Each node is visited exactly once during recursion, and the hash map provides O(1) lookup
        for the root's inorder index, resulting in linear time for n nodes.

    Space Complexity:
        • O(n)

        Explanation:
        The hash map stores n entries for inorder indices. The recursion stack uses O(h) space
        where h is the tree height (O(n) in the worst case for a skewed tree).

    ------------------------------------------------------------------------ */
}
