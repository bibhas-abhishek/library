// LC#547: Number of Provinces

package practice.numberofprovinces;

public class NumberOfProvinces {

    private int[] parent;
    private int[] rank;

    // Union-Find: group connected cities and count distinct components
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        // Initialize each city as its own parent (self-loop = root of its own set)
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int provinces = n;

        // For each pair of connected cities, union their sets
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    // If they belong to different sets, merge them and reduce province count
                    if (union(i, j)) {
                        provinces--;
                    }
                }
            }
        }

        return provinces;
    }

    // Find with path compression: flattens tree so future lookups are near O(1)
    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // Union by rank: attaches smaller tree under larger tree's root to keep height minimal
    private boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        // Already in the same set — no merge needed
        if (rootX == rootY) {
            return false;
        }

        // Attach smaller-rank tree under higher-rank tree
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            // Equal rank — pick one as root and increment its rank
            parent[rootY] = rootX;
            rank[rootX]++;
        }

        return true;
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(N^2 * α(N))
        • Worst Case: O(N^2 * α(N))

        Explanation:
        We iterate over the upper triangle of the N×N matrix O(N^2), and each
        union/find operation is nearly O(1) amortized due to path compression
        and union by rank (α is the inverse Ackermann function, effectively constant).

    Space Complexity:
        • O(N)

        Explanation:
        The parent and rank arrays each store N entries for N cities.

    ------------------------------------------------------------------------ */

    public static void main(String[] args) {
        // Test case 1: Two provinces — cities 0,1 connected; city 2 isolated
        int[][] isConnected1 = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};

        NumberOfProvinces driver = new NumberOfProvinces();
        int result1 = driver.findCircleNum(isConnected1);
        System.out.println("Result: " + result1); // Expected: 2

        // Test case 2: Three provinces — no connections
        // int[][] isConnected2 = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        // int result2 = driver.findCircleNum(isConnected2);
        // System.out.println("Result: " + result2); // Expected: 3

        // Test case 3: One province — all connected
        // int[][] isConnected3 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        // int result3 = driver.findCircleNum(isConnected3);
        // System.out.println("Result: " + result3); // Expected: 1
    }
}
