## Input Handling
- If the user provides **code**: refactor and comment the existing code following all rules below (add comments, fix formatting, add complexity block, add problem header if applicable).
- If the user provides **a question or problem** (including a LeetCode URL): write and comment an **optimal solution** (best time/space complexity possible for the problem).
- **Default programming language**: Java (unless the user specifies otherwise).
- **IDE mode** — When working in an IDE (e.g., IntelliJ), create a new package under `src/main/java/practice/<problemname>/` with a single Java file containing the solution. The package name should be all-lowercase, concise, and descriptive (e.g., `generateparentheses`). Follow all rules below for the file content.

## Rules for All Inputs

1. **Add explanatory comments** — Comments should explain:
    - **Syntax**: What the code does (e.g., "Initialize hash map")
    - **Logic**: Why it's done that way (e.g., "Use hash map for O(1) lookups to avoid nested loops")

   Do not comment obvious statements like `int x = 5;`. Example of a good comment:
   ```
   // Use hash map (syntax) for O(1) lookups (logic) to avoid nested loop
   ```

2. **Comments must be concise** — 1 line per comment block; sufficient to understand intent without over-explaining.

3. **No JavaDoc style comments** — Use `//` for inline comments only. Do not use `/** ... */` documentation blocks.

4. **Add complexity analysis** — Below the solution code (in the same code snippet), add a comment section with the exact structure below:

```
/* ---------------------- TIME & SPACE COMPLEXITY ----------------------

Time Complexity:
    • Average Case: O(...)
    • Worst Case: O(...)

    Explanation:
    <1-2 sentences: state what drives the complexity (e.g., single pass through n elements, binary search recursion depth)>

Space Complexity:
    • O(...)

    Explanation:
    <1-2 sentences: state what drives the space complexity (e.g., recursion call stack, hash table storage)>

------------------------------------------------------------------------ */
```

**Notes:**
- Analyze the **entire solution** as one unit — do not describe individual helper methods separately.
- For O(1) space, write: `• O(1)` with a brief explanation (e.g., "Only a constant number of variables").
- If multiple algorithms are present, focus on the primary/dominant one unless the question asks for comparison.

5. **Problem definition header** — For LeetCode problems, add a single-line comment before the package declaration with the problem number and title:
    ```java
    // LC#322: Coin Change
    ```

6. **Class naming** — Name the class using descriptive PascalCase matching the problem (e.g., `CoinChange`, `FlattenBinaryTree`, `GenerateParentheses`). Never use generic names like `Solution`.

7. **Imports and package declarations** — Include necessary `import` statements after the problem definition header. Always import specific classes (e.g., `import java.util.HashMap;`), never use wildcard imports (e.g., `import java.util.*;`). Do **not** include `package` declarations (the user handles those separately).

8. **Driver function for questions** — Provide a separate code snippet with `public static void main(String[] args)` that:
    - Does **not** include import statements (imports are already handled in the main solution code)
    - Tests the solution with test cases from the problem source (LeetCode, etc.); if the problem has no standard source, create 2–3 representative cases covering normal input, edge cases, and boundary conditions
    - The first test case should be **uncommented** and executable
    - Any additional test cases beyond the first should be **commented out**
    - Prints the results with expected outputs as comments
    - Example:
      ```java
      public static void main(String[] args) {
          // Test case 1: Normal input
          int[] nums = {2, 7, 11, 15};
          int target = 9;
          
          <ClassName> driver = new <ClassName>();
          int[] result = driver.solve(nums, target);
          System.out.println("Result: " + Arrays.toString(result)); // Expected: [0, 1]
          
          // Test case 2: Unordered array
          // int[] nums2 = {3, 2, 4};
          // int target2 = 6;
          // int[] result2 = driver.solve(nums2, target2);
          // System.out.println("Result: " + Arrays.toString(result2)); // Expected: [1, 2]
      }
      ```

9. **Code formatting rules** — When writing code for solutions:
    - Always use braces `{}` for single-statement blocks (if, else, for, while, etc.)
    - Example:
      ```java
      if (root == null) {
          return 0;
      }
      ```
    - Never write: `if (root == null) return 0;`

10. **Follow all rules strictly** — No exceptions or deviations.

11. **Post-generation build steps** — After generating code in IDE mode, always run:
    ```bash
    mvn spotless:apply
    mvn clean install
    ```
    This ensures consistent formatting and verifies the project compiles successfully.

## Output Structure for Questions

When solving a question, provide output in this exact order:

**1. Solution with Complexity**
```java
// LC#<number>: Problem Heading

// Package declaration
// Imports
// Solution class with inline comments and complexity analysis at the bottom
class <ProblemName> {
    // ... solution code with comments

    /* ---------------------- TIME & SPACE COMPLEXITY ----------------------
    ... complexity details ...
    ------------------------------------------------------------------------ */
}
```

**2. Driver Function**
```java
public static void main(String[] args) {
    // ... test cases (first uncommented, rest commented)
}
```

**3. Code Walkthrough**
Provide a detailed step-by-step explanation as regular text (not code comments):
- Explain key sections in logical order (initialization, main loop/recursion, return logic)
- Clarify how data flows through the algorithm
- Highlight critical decision points and transformations
- Provide sufficient depth for a senior engineer to understand the algorithm without running it
- Include why specific decisions were made (e.g., why use this data structure, why this order of operations)
- Use code snippets where helpful to illustrate key concepts or state changes
