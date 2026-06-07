// LC#208: Implement Trie (Prefix Tree)

package practice.trie;

import commons.TrieNode;

public class Trie {

    // Each node uses a map for flexible character-to-child mapping
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word by creating nodes along the path, marking the last node as end-of-word
    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            // Create child node if this character path doesn't exist yet
            current.children.putIfAbsent(ch, new TrieNode());
            current = current.children.get(ch);
        }
        // Mark the final node as a complete word
        current.isEnd = true;
    }

    // Returns true if the exact word exists in the trie (must end at a marked node)
    public boolean search(String word) {
        TrieNode node = traverse(word);
        return node != null && node.isEnd;
    }

    // Returns true if any word in the trie starts with the given prefix
    public boolean startsWith(String prefix) {
        return traverse(prefix) != null;
    }

    // Follows the character path and returns the final node, or null if path breaks
    private TrieNode traverse(String str) {
        TrieNode current = root;
        for (char ch : str.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                return null;
            }
            current = current.children.get(ch);
        }
        return current;
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • insert: O(L) where L = word length
        • search: O(L)
        • startsWith: O(L)

        Explanation:
        Each operation traverses at most L nodes (one per character in the input),
        with O(1) amortized work per node (HashMap get/put).

    Space Complexity:
        • O(N * L) where N = number of words, L = average word length

        Explanation:
        Each node only allocates map entries for characters that actually exist,
        avoiding the fixed 26-slot overhead. Space is proportional to total characters stored.

    ------------------------------------------------------------------------ */

    public static void main(String[] args) {
        // Test case 1: Standard operations
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println("search apple: " + trie.search("apple")); // Expected: true
        System.out.println("search app: " + trie.search("app")); // Expected: false
        System.out.println("startsWith app: " + trie.startsWith("app")); // Expected: true
        trie.insert("app");
        System.out.println("search app: " + trie.search("app")); // Expected: true

        // Test case 2: Non-existent words
        // System.out.println("search orange: " + trie.search("orange")); // Expected: false
        // System.out.println("startsWith or: " + trie.startsWith("or")); // Expected: false

        // Test case 3: Multiple words with shared prefix
        // trie.insert("application");
        // trie.insert("apply");
        // System.out.println("search apply: " + trie.search("apply")); // Expected: true
        // System.out.println("startsWith appl: " + trie.startsWith("appl")); // Expected: true
    }
}
