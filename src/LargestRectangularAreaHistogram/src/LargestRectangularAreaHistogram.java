import java.util.Stack;


public class LargestRectangularAreaHistogram {

    public int getMaxArea(int[] hist, int n) {
        // stack stores indices of bars in non-decreasing height order
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int index = 0;

        // Traverse all bars of the histogram
        while (index < n) {
            // If stack is empty OR current bar's height is >= height at stack top,
            // push current index to stack (we can extend rectangles to the right).
            if (stack.isEmpty() || hist[index] >= hist[stack.peek()]) {
                // push index and then advance index
                stack.push(index);
                index++;
            } else {
                // Current bar is lower than bar at stack top, so calculate area
                // with the bar at top of stack as the smallest (limiting) bar.
                int topIndex = stack.pop();
                int areaWithTop;

                if (stack.isEmpty()) {
                    // If stack is empty, the popped bar extends from 0 to current index-1
                    areaWithTop = hist[topIndex] * index;
                } else {
                    // If stack is not empty, width is between stack.peek()+1 and index-1
                    areaWithTop = hist[topIndex] * (index - stack.peek() - 1);
                }

                // update maximum area found so far
                if (areaWithTop > maxArea) {
                    maxArea = areaWithTop;
                }
            }
        }

        // Now pop remaining bars from stack and calculate area with each popped bar
        while (!stack.isEmpty()) {
            int topIndex = stack.pop();
            int areaWithTop;

            if (stack.isEmpty()) {
                // If stack empty, the popped bar spans entire histogram (0..n-1)
                areaWithTop = hist[topIndex] * index;
            } else {
                // Otherwise it spans from stack.peek()+1 to n-1
                areaWithTop = hist[topIndex] * (index - stack.peek() - 1);
            }

            if (areaWithTop > maxArea) {
                maxArea = areaWithTop;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        LargestRectangularAreaHistogram driver = new LargestRectangularAreaHistogram();
        int[] hist = {6, 2, 5, 4, 5, 1, 6};
        System.out.println(driver.getMaxArea(hist, hist.length));
    }

    /*
     * Overall complexity of the implementation:
     *
     * Time complexity: O(n)
     *   - Each bar index is pushed onto the stack at most once and popped at most once.
     *   - All operations inside the loops are O(1), so the entire algorithm is linear
     *     in the number of bars.
     *
     * Space complexity: O(n)
     *   - In the worst case (strictly increasing heights) the stack can hold all n indices,
     *     so additional space used is proportional to n.
     */
}
