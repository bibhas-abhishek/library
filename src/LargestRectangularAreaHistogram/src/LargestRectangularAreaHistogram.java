import java.util.Stack;


public class LargestRectangularAreaHistogram {

    public int getMaxArea(int[] hist, int n) {
        Stack<Integer> stack = new Stack<>(); // stack stores indices of histogram bars
        int maxArea = 0, top, topArea, i = 0;

        // Traverse all bars of the histogram
        while (i < n) {
            // If stack is empty or current bar is taller/equal than bar at stack top,
            // push its index to the stack to extend possible rectangles to the right.
            if (stack.isEmpty() || hist[i] >= hist[stack.peek()]) {
                stack.push(i++);
            } else {
                // Current bar is lower than stack top -> compute area for bar at top
                top = stack.pop();
                // If stack becomes empty, width extends from 0 to i-1 (i bars)
                if (stack.isEmpty()) {
                    topArea = hist[top] * i;
                } else {
                    // Otherwise width is between stack.peek()+1 and i-1
                    topArea = hist[top] * (i - stack.peek() - 1);
                }
                // Update maximum area found so far
                maxArea = Math.max(maxArea, topArea);
            }
        }

        // Process remaining bars in stack
        while (!stack.isEmpty()) {
            top = stack.pop();
            // If stack empty, width extends from 0 to n-1 (i == n here)
            if (stack.isEmpty()) {
                topArea = hist[top] * i;
            } else {
                // Otherwise width is between stack.peek()+1 and n-1
                topArea = hist[top] * (i - stack.peek() - 1);
            }
            maxArea = Math.max(maxArea, topArea);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        LargestRectangularAreaHistogram driver = new LargestRectangularAreaHistogram();
        int[] hist = {6, 2, 5, 4, 5, 1, 6};
        System.out.println(driver.getMaxArea(hist, hist.length));
    }

    // Overall complexity:
    // Time complexity: O(n) — each bar index is pushed and popped at most once.
    // Space complexity: O(n) — stack may hold up to n indices in the worst case.
}
