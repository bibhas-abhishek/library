// LC#172: Factorial Trailing Zeroes

package practice.trailingzeroes;

public class TrailingZeroes {

    // Count trailing zeroes in n! by counting factors of 5
    // Each trailing zero comes from a factor of 10 = 2 * 5, and factors of 2 are always abundant
    public int trailingZeroes(int n) {
        int count = 0;

        // Divide by increasing powers of 5 to count multiples of 5, 25, 125, etc.
        // n/5 gives multiples of 5, n/25 gives extra 5s from multiples of 25, and so on
        while (n >= 5) {
            n /= 5;
            count += n;
        }

        return count;
    }

    public static void main(String[] args) {
        // Test case 1: n=10, two multiples of 5 (5 and 10)
        TrailingZeroes driver = new TrailingZeroes();
        int result = driver.trailingZeroes(150);
        System.out.println("Result: " + result); // Expected: 2

        // Test case 2: n=5, one factor of 5
        // int result2 = driver.trailingZeroes(5);
        // System.out.println("Result: " + result2); // Expected: 1

        // Test case 3: n=30, includes multiples of 5 and 25
        // int result3 = driver.trailingZeroes(30);
        // System.out.println("Result: " + result3); // Expected: 7
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(log5(n))
        • Worst Case: O(log5(n))

        Explanation:
        The loop divides n by 5 repeatedly until it reaches 0, running log base 5 of n times.

    Space Complexity:
        • O(1)

        Explanation:
        Only a constant number of variables (count and n) are used regardless of input size.

    ------------------------------------------------------------------------ */
}
