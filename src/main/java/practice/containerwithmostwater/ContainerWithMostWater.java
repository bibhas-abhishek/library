// LC#11: Container With Most Water

package practice.containerwithmostwater;

class ContainerWithMostWater {

    // Two-pointer approach: start at the widest container and greedily move inward
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxWater = 0;

        // Shrink the window while tracking the best area seen so far
        while (left < right) {
            // Area is bounded by the shorter line; width is the index distance
            int currentHeight = Math.min(height[left], height[right]);
            int currentArea = currentHeight * (right - left);
            maxWater = Math.max(maxWater, currentArea);

            // Move the pointer at the shorter line inward — moving the taller one
            // can never increase the area (width shrinks, height still capped by shorter)
            if (height[left] < height[right]) {
                left++;
            } else if (height[left] > height[right]) {
                right--;
            } else {
                // Tie: neither end can form a better pair — safe to discard both
                left++;
                right--;
            }
        }

        return maxWater;
    }

    public static void main(String[] args) {
        // Test case 1: Standard input
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};

        ContainerWithMostWater driver = new ContainerWithMostWater();
        int result = driver.maxArea(height);
        System.out.println("Result: " + result); // Expected: 49

        // Test case 2: Minimum length
        // int[] height2 = {1, 1};
        // int result2 = driver.maxArea(height2);
        // System.out.println("Result: " + result2); // Expected: 1

        // Test case 3: Strictly increasing
        // int[] height3 = {1, 2, 4, 3};
        // int result3 = driver.maxArea(height3);
        // System.out.println("Result: " + result3); // Expected: 4
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(n)
        • Worst Case: O(n)

        Explanation:
        Each iteration moves exactly one pointer inward, so the two pointers
        collectively traverse the array at most once.

    Space Complexity:
        • O(1)

        Explanation:
        Only a constant number of variables (two pointers, current area, max)
        are used regardless of input size.

    ------------------------------------------------------------------------ */
}
