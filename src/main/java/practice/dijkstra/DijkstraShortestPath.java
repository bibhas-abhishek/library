// Dijkstra's Algorithm: Single-Source Shortest Path

package practice.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraShortestPath {

    // Returns shortest distances from source to all other vertices in a weighted graph
    public int[] dijkstra(int numVertices, List<List<int[]>> adjacencyList, int source) {
        // dist[i] = shortest known distance from source to vertex i
        int[] dist = new int[numVertices];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        // Min-heap ordered by distance; entries are [distance, vertex]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        minHeap.offer(new int[] {0, source});

        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int currentDist = current[0];
            int node = current[1];

            // Skip stale entries: a shorter path to this node was already processed
            if (currentDist > dist[node]) {
                continue;
            }

            // Relax all outgoing edges from current node
            for (int[] edge : adjacencyList.get(node)) {
                int neighbor = edge[0];
                int weight = edge[1];
                int newDist = currentDist + weight;

                // Found a shorter path to neighbor — update and enqueue
                if (newDist < dist[neighbor]) {
                    dist[neighbor] = newDist;
                    minHeap.offer(new int[] {newDist, neighbor});
                }
            }
        }

        return dist;
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O((V + E) log V)
        • Worst Case: O((V + E) log V)

        Explanation:
        Each vertex is extracted from the heap at most once O(V log V), and each edge
        relaxation may insert into the heap O(E log V), giving O((V + E) log V) total.

    Space Complexity:
        • O(V + E)

        Explanation:
        The distance array and heap hold up to O(V) entries each, and the adjacency
        list stores all edges O(E).

    ------------------------------------------------------------------------ */

    public static void main(String[] args) {
        // Test case 1: Simple graph with 5 vertices
        //     0 --4-- 1 --1-- 3
        //     |       |       |
        //     2       8       2
        //     |       |       |
        //     2 --7-- 4 ------+
        int numVertices = 5;
        List<List<int[]>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Undirected edges: add both directions
        addEdge(adjacencyList, 0, 1, 4);
        addEdge(adjacencyList, 0, 2, 2);
        addEdge(adjacencyList, 1, 3, 1);
        addEdge(adjacencyList, 1, 4, 8);
        addEdge(adjacencyList, 2, 4, 7);
        addEdge(adjacencyList, 3, 4, 2);

        DijkstraShortestPath driver = new DijkstraShortestPath();
        int[] result1 = driver.dijkstra(numVertices, adjacencyList, 0);
        System.out.println("Result: " + Arrays.toString(result1));
        // Expected: [0, 4, 2, 5, 7] (source=0 → 0:0, 1:4, 2:2, 3:5, 4:7)

        // Test case 2: Disconnected vertex
        // int numVertices2 = 4;
        // List<List<int[]>> adj2 = new ArrayList<>();
        // for (int i = 0; i < numVertices2; i++) adj2.add(new ArrayList<>());
        // addEdge(adj2, 0, 1, 3);
        // addEdge(adj2, 0, 2, 1);
        // // vertex 3 is disconnected
        // int[] result2 = driver.dijkstra(numVertices2, adj2, 0);
        // System.out.println("Result: " + Arrays.toString(result2));
        // Expected: [0, 3, 1, 2147483647] (vertex 3 unreachable)

        // Test case 3: Single vertex
        // List<List<int[]>> adj3 = new ArrayList<>();
        // adj3.add(new ArrayList<>());
        // int[] result3 = driver.dijkstra(1, adj3, 0);
        // System.out.println("Result: " + Arrays.toString(result3));
        // Expected: [0]
    }

    // Helper to add an undirected weighted edge
    private static void addEdge(List<List<int[]>> adj, int u, int v, int weight) {
        adj.get(u).add(new int[] {v, weight});
        adj.get(v).add(new int[] {u, weight});
    }
}
