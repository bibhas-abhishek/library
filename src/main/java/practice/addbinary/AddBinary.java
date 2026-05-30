// LC#67: Add Binary

package practice.addbinary;

public class AddBinary {

    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        // Process both strings from right to left, simulating binary addition
        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;

            // Add current digit from a (if still has digits)
            if (i >= 0) {
                sum += a.charAt(i) - '0';
                i--;
            }

            // Add current digit from b (if still has digits)
            if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }

            // Append current bit (sum % 2) and compute carry (sum / 2)
            result.append(sum % 2);
            carry = sum / 2;
        }

        // Digits were appended in reverse order, so reverse to get final answer
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        AddBinary driver = new AddBinary();

        // Test case 1: Standard addition with carry propagation
        String result = driver.addBinary("1010", "1011");
        System.out.println("Result: " + result); // Expected: "10101"

        // Test case 2: Different lengths
        // String result2 = driver.addBinary("11", "1");
        // System.out.println("Result: " + result2); // Expected: "100"
    }

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------

    Time Complexity:
        • Average Case: O(max(m, n))
        • Worst Case:   O(max(m, n))

        Explanation:
        We iterate through both strings once from right to left, processing
        one digit per iteration until both are exhausted and no carry remains.

    Space Complexity:
        • O(max(m, n))

        Explanation:
        The StringBuilder stores the result which is at most max(m, n) + 1
        characters long (one extra digit possible from a final carry).

    ------------------------------------------------------------------------ */
}
