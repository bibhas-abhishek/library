// LC#207: Course Schedule

package practice.courseschedule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class CourseSchedule {

    // PRIMARY: BFS topological sort (Kahn's algorithm) to detect cycles in prerequisite graph
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Build adjacency list to represent directed graph of course dependencies
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // inDegree[i] = number of prerequisites required before taking course i
        int[] inDegree = new int[numCourses];

        // Populate graph: edge from prereq -> course means "prereq must come before course"
        for (int[] pair : prerequisites) {
            int course = pair[0];
            int prereq = pair[1];
            adjacencyList.get(prereq).add(course);
            inDegree[course]++;
        }

        // Seed queue with all courses that have no prerequisites (in-degree 0)
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Process courses in topological order, counting how many we can complete
        int completedCourses = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            completedCourses++;

            // Reduce in-degree for all courses that depend on current
            for (int neighbor : adjacencyList.get(current)) {
                inDegree[neighbor]--;
                // If all prerequisites satisfied, this course is now ready
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // If we completed all courses, no cycle exists
        return completedCourses == numCourses;
    }

    // SECONDARY: DFS cycle detection using two sets for the current path and visited nodes
    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        // Build adjacency list: edge from prereq -> course
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int[] pair : prerequisites) {
            adjacencyList.get(pair[1]).add(pair[0]);
        }

        // Tracks nodes confirmed to be cycle-free (fully explored)
        Set<Integer> visited = new HashSet<>();
        // Tracks nodes on the current DFS recursion path (ancestors of current node)
        Set<Integer> inPath = new HashSet<>();

        // Launch DFS from every unvisited node to handle disconnected components
        for (int i = 0; i < numCourses; i++) {
            if (!visited.contains(i)) {
                if (hasCycle(i, adjacencyList, visited, inPath)) {
                    return false;
                }
            }
        }

        return true;
    }

    // Returns true if a cycle is detected starting from the given node
    private boolean hasCycle(
            int node,
            List<List<Integer>> adjacencyList,
            Set<Integer> visited,
            Set<Integer> inPath) {
        // Add node to current path — marks it as an ancestor of subsequent DFS calls
        inPath.add(node);

        for (int neighbor : adjacencyList.get(node)) {
            // Back edge: neighbor is an ancestor on the current path → cycle found
            if (inPath.contains(neighbor)) {
                return true;
            }
            // Only explore nodes not yet confirmed cycle-free
            if (!visited.contains(neighbor)) {
                if (hasCycle(neighbor, adjacencyList, visited, inPath)) {
                    return true;
                }
            }
        }

        // Backtrack: remove from current path and mark as fully processed
        inPath.remove(node);
        visited.add(node);
        return false;
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(V + E)
        • Worst Case: O(V + E)

        Explanation:
        Both approaches visit each course (vertex) exactly once and examine each
        prerequisite (edge) exactly once, giving linear time in the size of the graph.

    Space Complexity:
        • O(V + E)

        Explanation:
        The adjacency list stores all edges O(E). Kahn's uses an in-degree array and
        queue O(V). DFS uses two sets O(V) and recursion stack up to O(V) depth.

    ------------------------------------------------------------------------ */

    public static void main(String[] args) {
        // Test case 1: Two courses with one prerequisite — completable
        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};

        CourseSchedule driver = new CourseSchedule();
        boolean result1 = driver.canFinish(numCourses1, prerequisites1);
        System.out.println("BFS Result: " + result1); // Expected: true
        System.out.println(
                "DFS Result: "
                        + driver.canFinishDFS(numCourses1, prerequisites1)); // Expected: true

        // Test case 2: Circular dependency — impossible to complete
        // int numCourses2 = 2;
        // int[][] prerequisites2 = {{1, 0}, {0, 1}};
        // boolean result2 = driver.canFinish(numCourses2, prerequisites2);
        // System.out.println("BFS Result: " + result2); // Expected: false
        // System.out.println("DFS Result: " + driver.canFinishDFS(numCourses2, prerequisites2)); //
        // Expected: false

        // Test case 3: Multiple courses with no cycle
        // int numCourses3 = 4;
        // int[][] prerequisites3 = {{1, 0}, {2, 1}, {3, 2}};
        // boolean result3 = driver.canFinish(numCourses3, prerequisites3);
        // System.out.println("BFS Result: " + result3); // Expected: true
        // System.out.println("DFS Result: " + driver.canFinishDFS(numCourses3, prerequisites3)); //
        // Expected: true
    }
}
