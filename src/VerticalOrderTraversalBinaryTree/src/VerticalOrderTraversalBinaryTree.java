import commons.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class VerticalOrderTraversalBinaryTree {

    // Wrapper method that initializes the TreeMap and triggers vertical traversal.
    // The TreeMap keeps horizontal distances (HD) sorted automatically.
    private static void printVerticalOrder(TreeNode root) {
        Map<Integer, List<Integer>> treeMap = new TreeMap<>();
        printVerticalOrder(root, treeMap, 0);
        for (Map.Entry<Integer, List<Integer>> entry : treeMap.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    // Recursively traverses the tree and groups nodes by their horizontal distance (HD).
    // HD decreases when going left and increases when going right.
    private static void printVerticalOrder(TreeNode root, Map<Integer, List<Integer>> treeMap, int hd) {
        if (root == null) {
            return;
        }

        // Retrieve list for the current HD; create a new list if none exists.
        List<Integer> keys = treeMap.get(hd);
        if (keys == null) {
            keys = new ArrayList<>();
        }

        // Add the current node's value under its horizontal distance.
        keys.add(root.val);

        // Store updated list back into the map.
        treeMap.put(hd, keys);

        // Recurse for left and right children with updated horizontal distances.
        printVerticalOrder(root.left, treeMap, hd - 1);
        printVerticalOrder(root.right, treeMap, hd + 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(9);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(1);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(7);
        printVerticalOrder(root);
        System.out.println();
    }
}

/* ---------------------- TIME & SPACE COMPLEXITY ----------------------

Time Complexity:
    • Average Case: O(N log N)
    • Worst Case:   O(N log N)

    Explanation:
    Each of the N nodes is visited once, and inserting each node's value into
    the TreeMap costs O(log N) due to balanced-tree operations. Hence the total
    time is O(N log N).

Space Complexity:
    • O(N)

    Explanation:
    The TreeMap stores all N node values, and the recursion stack in the worst
    case can add additional nodes but still remains within O(N) space.

------------------------------------------------------------------------ */
