# Library

A Java practice codebase for algorithm and data structure problems. Each solution lives in its own package with inline comments, complexity analysis, and a runnable driver function.

## Tech Stack

- **Java 25** (managed via jenv)
- **Maven** for build and dependency management
- **Google Java Format** (AOSP style, 4-space indent) via Spotless
- **Checkstyle** for lint enforcement (140-char lines, no star imports, mandatory braces)

## Project Structure

```
src/main/java/
├── commons/          # Shared data structures (TreeNode, TrieNode, etc.)
└── practice/         # One package per problem
    ├── coinchange/
    ├── courseschedule/
    ├── dijkstra/
    └── ...
```

Each solution file follows a consistent format:
- Problem header (`// LC#207: Course Schedule`)
- Imports (specific, no wildcards)
- Commented solution with complexity block
- `main()` with test cases

## Build

```bash
# Format code
mvn spotless:apply

# Compile and verify
mvn clean install
```

## Conventions

See `CLAUDE.md` for the full coding rules enforced in this project.
